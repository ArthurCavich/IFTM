import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PORTA = 12222;
    private static final String FIM = "<fim>";

    public static void main(String[] args) throws IOException {
        try (ServerSocket servidor = new ServerSocket(PORTA)) {
            System.out.println("Servidor aguardando conexoes na porta " + PORTA + "...");

            while (true) {
                try (Socket conexao = servidor.accept();
                        DataInputStream entrada = new DataInputStream(conexao.getInputStream());
                        DataOutputStream saida = new DataOutputStream(conexao.getOutputStream())) {

                    System.out.println("Cliente conectado: "
                            + conexao.getInetAddress().getHostAddress());
                    atenderCliente(entrada, saida);
                } catch (EOFException e) {
                    System.out.println("Cliente desconectado.");
                }
            }
        }
    }

    private static void atenderCliente(DataInputStream entrada, DataOutputStream saida)
            throws IOException {
        String mensagem;
        do {
            mensagem = entrada.readUTF();
            String resposta = "Mensagem recebida: " + mensagem;

            System.out.println(resposta);
            saida.writeUTF(resposta);
            saida.flush();
        } while (!FIM.equalsIgnoreCase(mensagem.trim()));

        System.out.println("Conexao encerrada.");
    }
}
