import java.io.IOException;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(System.in);
        
        double codigoPeca1 = s.nextDouble();
        double qtdPeca1 = s.nextDouble();
        double valorPeca1 = s.nextDouble();
        
        double codigoPeca2 = s.nextDouble();
        double qtdPeca2 = s.nextDouble();
        double valorPeca2 = s.nextDouble();

        double custoTotal = (qtdPeca1 * valorPeca1) + (qtdPeca2 * valorPeca2);

        System.out.printf("VALOR A PAGAR: R$ %.2f%n", custoTotal);
        
    }
 
}
