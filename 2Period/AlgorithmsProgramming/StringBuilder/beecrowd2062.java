import java.util.Scanner;

public class beecrowd2062 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numero = scanner.nextInt();
            scanner.nextLine(); // consumir a quebra de linha após o número
            String linha = scanner.nextLine();

            String[] palavras = linha.split(" "); // separo as palavras da linha
            
            StringBuilder resultado = new StringBuilder(); // declaro o resultado como uma string
            
            for (int i = 0; i < palavras.length; i++) { // faço um for para percorrer as palavras
                String palavra = palavras[i];
                
                // Verifica se a palavra tem exatamente 3 letras
                if (palavra.length() == 3) {
                    // Verifica se começa com "OB"
                    if (palavra.startsWith("OB")) {
                        palavra = "OBI";
                    }
                    // Verifica se começa com "UR"
                    else if (palavra.startsWith("UR")) {
                        palavra = "URI";
                    }
                }
                
                resultado.append(palavra); // adiciono a palavra ao resultado
                
                // Adiciona espaço entre palavras (exceto na última)
                if (i < palavras.length - 1) {
                    resultado.append(" ");
                }
            }
            
            System.out.println(resultado.toString()); // imprimo o resultado
        }
    }
}
