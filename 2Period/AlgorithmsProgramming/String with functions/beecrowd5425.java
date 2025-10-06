import java.util.Scanner;

public class beecrowd5425 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();

        // aqui eu utilizei o método .substring que percorre o tamanho da palavra que eu
        // quiser, no caso
        // ele começa em zero e vai até o índice 2, trazendo as posicoes 0 e 1.
        String d = data.substring(0, 2);

        System.out.println("Estamos no dia " + d + " do mês.");

        sc.close();

    }
}
