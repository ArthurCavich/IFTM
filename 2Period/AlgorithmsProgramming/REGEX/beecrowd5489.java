import java.util.Scanner;

public class beecrowd5489 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String telefone = sc.nextLine();
        
        // Validação para celular: (DD) 9NNNN-NNNN (9 dígitos, começando com 9)
        if (telefone.matches("\\([0-9]{2}\\) 9[0-9]{4}-[0-9]{4}")) {
            System.out.println("Celular");
        }
        // Validação para fixo: (DD) NNNN-NNNN (8 dígitos)
        else if (telefone.matches("\\([0-9]{2}\\) [0-9]{4}-[0-9]{4}")) {
            System.out.println("Fixo");
        }
        // Formato inválido
        else {
            System.out.println("inválido");
        }
        
        sc.close();
    }
}

