import java.util.Scanner;

public class beecrowd6531 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String s = scanner.nextLine();
            int k = scanner.nextInt();
            
            StringBuilder resultado = new StringBuilder(s); // declaro o resultado como uma string
            
            // Guarda a string original S para usar em cada iteração
            String original = s;
            
            for (int i = 0; i < k; i++) {
                // Inverte a string atual
                resultado.reverse();
                // Adiciona a string original S ao final
                resultado.append(original);
            }
            
            System.out.println(resultado.toString());
        }
    }
}
