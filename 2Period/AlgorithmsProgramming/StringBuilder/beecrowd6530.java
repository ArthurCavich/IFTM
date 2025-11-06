import java.util.Scanner;

public class beecrowd6530 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String s = scanner.nextLine();
            int n = scanner.nextInt();
            
            StringBuilder resultado = new StringBuilder(s); // declaro o resultado como uma string
            
            for (int i = 0; i < n; i++) { // faço um for para percorrer as n entradas
                int inicio = scanner.nextInt(); // declaro o inicio como um inteiro
                int fim = scanner.nextInt(); // declaro o fim como um inteiro
                
                // Verifica se o intervalo é válido
                if (inicio >= 0 && fim < resultado.length() && inicio <= fim) { // verifica se o intervalo é válido
                    // Remove os caracteres do intervalo usando delete()
                    resultado.delete(inicio, fim + 1); // remove os caracteres do intervalo
                }
            }
            
            System.out.println(resultado.toString());
        }
    }
}
