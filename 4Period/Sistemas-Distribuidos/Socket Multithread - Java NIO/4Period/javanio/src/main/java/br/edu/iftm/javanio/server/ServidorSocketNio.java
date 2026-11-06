package br.edu.iftm.javanio.server;

import br.edu.iftm.javanio.service.ServicoTransacao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@Component
public class ServidorSocketNio {

    private final ServicoTransacao service;
    private final int port;
    private volatile boolean running;
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private Thread serverThread;

    public ServidorSocketNio(ServicoTransacao service,
                           @Value("${transaction.server.nio-port:9092}") int port) {
        this.service = service;
        this.port = port;
    }

    @PostConstruct
    void start() {
        serverThread = new Thread(this::run, "iso8583-nio-server");
        serverThread.start();
    }

    private void run() {
        try (Selector currentSelector = Selector.open();
             ServerSocketChannel currentServer = ServerSocketChannel.open()) {
            selector = currentSelector;
            serverChannel = currentServer;
            currentServer.configureBlocking(false);
            currentServer.bind(new InetSocketAddress(port));
            currentServer.register(currentSelector, SelectionKey.OP_ACCEPT);
            running = true;
            while (running) {
                currentSelector.select();
                Iterator<SelectionKey> keys = currentSelector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (key.isValid() && key.isAcceptable()) {
                        accept(currentSelector);
                    } else if (key.isValid() && key.isReadable()) {
                        read(key);
                    }
                }
            }
        } catch (IOException exception) {
            if (running) {
                throw new IllegalStateException("Falha no servidor Java NIO", exception);
            }
        }
    }

    private void accept(Selector currentSelector) throws IOException {
        SocketChannel client = serverChannel.accept();
        if (client != null) {
            client.configureBlocking(false);
            client.register(currentSelector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        int read = client.read(buffer);
        if (read == -1) {
            key.cancel();
            client.close();
            return;
        }
        String input = new String(buffer.array(), 0, buffer.position(), StandardCharsets.UTF_8);
        int lineEnd = input.indexOf('\n');
        if (lineEnd >= 0) {
            String response = ProcessadorTransacao.process(input.substring(0, lineEnd), service) + "\n";
            client.write(StandardCharsets.UTF_8.encode(response));
            buffer.clear();
        }
    }

    @PreDestroy
    void stop() {
        running = false;
        if (selector != null) {
            selector.wakeup();
        }
        if (serverThread != null) {
            try {
                serverThread.join(1000);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
