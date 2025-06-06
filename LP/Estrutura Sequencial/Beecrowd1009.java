import java.io.IOException;
import java.util.Scanner;

public class Beecrowd1009 {
 
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(System.in);
        
        String nome = s.nextLine();
        double salario = s.nextDouble();
        double vendas = s.nextDouble();
        double comissao = vendas * 0.15;
        double salarioFinal = salario + comissao;

        System.out.printf("TOTAL = R$ %.2f%n", salarioFinal);

    }
 
}
