import java.util.Scanner;

public class beecrowd5489 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String telefone = sc.nextLine();
        
        // Validação para celular
        if (telefone.matches("\\([0-9]{2}\\) 9[0-9]{4}-[0-9]{4}")) {
            System.out.println("Celular");
        }
        // Validação para fixo
        else if (telefone.matches("\\([0-9]{2}\\) [0-9]{4}-[0-9]{4}")) {
            System.out.println("Fixo");
        }
       
        else {
            System.out.println("inválido");
        }
        
        sc.close();
    }
}

