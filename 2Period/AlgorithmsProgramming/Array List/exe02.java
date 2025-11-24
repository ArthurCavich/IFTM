import java.util.ArrayList;
import java.util.Scanner;

public class exe02 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<String> nomes = new ArrayList<>();
            
            String nome;
            
            // Lê nomes até receber "FIM"
            while (true) {
                nome = scanner.nextLine();
                
                // Se o nome for "FIM", para o loop
                if (nome.equals("FIM")) {
                    break;
                }
                
                // Adiciona o nome ao ArrayList
                nomes.add(nome);
            }
            
            // Imprime os nomes em ordem inversa (do último para o primeiro)
            for (int i = nomes.size() - 1; i >= 0; i--) {
                System.out.println(nomes.get(i));
            }
        }
    }
}

