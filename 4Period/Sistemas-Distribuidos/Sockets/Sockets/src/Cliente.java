import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PORTA = 12222;
    private static final String FIM = "<fim>";

    public static void main(String[] args) {
        try (Socket conexao = new Socket(HOST, PORTA);
                DataInputStream entrada = new DataInputStream(conexao.getInputStream());
                DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {
            String mensagem;
            do {
                System.out.print("> ");
                mensagem = teclado.readLine();

                if (mensagem == null) {
                    mensagem = FIM;
                }

                saida.writeUTF(mensagem);
                saida.flush();

                String resposta = entrada.readUTF();
                System.out.println("Resposta do servidor: " + resposta);
            } while (!FIM.equalsIgnoreCase(mensagem.trim()));

            System.out.println("Conexao encerrada.");
        } catch (EOFException e) {
            System.out.println("O servidor encerrou a conexao inesperadamente.");
        } catch (IOException e) {
            System.out.println("Erro de comunicacao: " + e.getMessage());
        }
    }
}