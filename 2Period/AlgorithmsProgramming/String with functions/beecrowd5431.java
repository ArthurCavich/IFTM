import java.util.Scanner;

public class beecrowd5431 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String formula = sc.nextLine();

        //divide a formula com split
        String[] partes = formula.split(" ");

        //se a formula não tiver 3 partes, retorna invalido
        if (partes.length != 3) {
            System.out.println("Formula invalida.");
        } else {
            try {
                //se não, converte as partes em variáveis que calculam (utilizei o parseDouble para converter o tipo da variável)
                double num1 = Double.parseDouble(partes[0]); //utiliza o índice do vetor 0
                String operador = partes[1];//utiliza o índice do vetor 1
                double num2 = Double.parseDouble(partes[2]);//utiliza o índice do vetor 2

                double resultado;
                //switch que utiliza o operador como 
                switch (operador) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        resultado = num1 / num2;
                        break;
                    default:
                        System.out.println("Formula invalida.");
                        return;
                }

                System.out.println(resultado);

            } catch (NumberFormatException e) {
                System.out.println("Formula invalida.");
            }
        }

        sc.close();
    }
}