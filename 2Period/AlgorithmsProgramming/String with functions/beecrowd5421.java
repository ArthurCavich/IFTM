import java.util.Scanner;

public class beecrowd5421 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();

        // aqui eu utilizei o método length para analisar o tamanho total dos
        // caracteres.
        if (texto.length() <= 10) {
            System.out.println("pequeno");
        } else {
            System.out.println("grande");
        }

        sc.close();
    }
}
