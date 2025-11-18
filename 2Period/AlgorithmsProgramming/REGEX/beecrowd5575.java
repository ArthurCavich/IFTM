import java.util.Scanner;

public class beecrowd5575 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String cpf = sc.nextLine();
        
        // Validação de CPF
        if (cpf.matches("([0-9]{3}\\.){2}[0-9]{3}-[0-9]{2}|[0-9]{11}")) {
            System.out.println("CPF válido");
        } else {
            System.out.println("CPF inválido");
        }
        
        sc.close();
    }
}

