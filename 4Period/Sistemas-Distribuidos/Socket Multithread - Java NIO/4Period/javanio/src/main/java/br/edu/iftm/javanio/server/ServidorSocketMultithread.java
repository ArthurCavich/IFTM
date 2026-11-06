package br.edu.iftm.javanio.server;

import br.edu.iftm.javanio.service.ServicoTransacao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ServidorSocketMultithread {

    private final ServicoTransacao service;
    private final int port;
    private final ExecutorService workers = Executors.newCachedThreadPool();
    private volatile boolean running;
    private ServerSocket serverSocket;

    public ServidorSocketMultithread(ServicoTransacao service,
                                   @Value("${transaction.server.multithread-port:9091}") int port) {
        this.service = service;
        this.port = port;
    }

    @PostConstruct
    void start() {
        workers.submit(() -> {
            try (ServerSocket socket = new ServerSocket(port)) {
                serverSocket = socket;
                running = true;
                while (running) {
                    Socket client = socket.accept();
                    workers.submit(() -> handle(client));
                }
            } catch (IOException exception) {
                if (running) {
                    throw new IllegalStateException("Falha no servidor multithread", exception);
                }
            }
        });
    }

    private void handle(Socket client) {
        try (client;
             BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter writer = new PrintWriter(client.getOutputStream(), true)) {
            String request;
            while ((request = reader.readLine()) != null) {
                writer.println(ProcessadorTransacao.process(request, service));
            }
        } catch (IOException ignored) {
            // A desconexão do cliente encerra somente a conexão atual.
        }
    }

    @PreDestroy
    void stop() {
        running = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Falha ao encerrar servidor multithread", exception);
        } finally {
            workers.shutdownNow();
        }
    }
}
