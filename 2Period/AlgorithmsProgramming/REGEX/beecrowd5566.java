import java.util.Scanner;

public class beecrowd5566 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String data = sc.nextLine();
        
        // Validação de data
        if (data.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}")) {
            System.out.println("Data válida");
        } else {
            System.out.println("Data inválida");
        }
        
        sc.close();
    }
}