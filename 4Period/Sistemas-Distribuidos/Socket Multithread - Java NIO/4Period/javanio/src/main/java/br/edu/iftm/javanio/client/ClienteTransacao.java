package br.edu.iftm.javanio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class ClienteTransacao {

    private ClienteTransacao() {
    }

    public static String send(String host, int port, String message) throws IOException {
        try (Socket socket = new Socket(host, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            writer.println(message);
            return reader.readLine();
        }
    }
}
