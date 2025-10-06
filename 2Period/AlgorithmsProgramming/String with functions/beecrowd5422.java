import java.util.Scanner;

public class beecrowd5422 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cpf = sc.nextLine();

        //utilizei o mesmo método do exercício anterior, com método .length.
        if (cpf.length() == 11) {
            System.out.println("valido");
        } else {
            System.out.println("invalido");
        }

        sc.close();
    }
}