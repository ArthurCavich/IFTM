import java.io.IOException;
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(System.in);
        
        int numero = s.nextInt();
        int horas = s.nextInt();
        double ganho = s.nextDouble();

        double salario = ganho * horas;
        System.out.println("NUMBER = " + numero);
        System.out.printf("SALARY = U$ %.2f%n", salario);
 
    }
 
}
