import java.util.Scanner;

public class beecrowd5420 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double custo = sc.nextDouble();
         sc.nextLine(); //cusmo de linha
        String cidade = sc.nextLine();

        //aqui eu utilizei o 'equals' para validar se a entrada cidade é igual.
        if (cidade.equals("Uberlandia")) {
            System.out.printf("O custo de vida em Uberlandia e R$ %.2f%n", custo);
        } else if (cidade.equals("Brasilia")) {
            custo = custo * 2;
            System.out.printf("O custo de vida em Brasilia e R$ %.2f%n", custo);
        } else {
            custo = custo / 2;
            System.out.printf("O custo de vida em %s e R$ %.2f%n", cidade, custo);
        }

        sc.close();
    }
}