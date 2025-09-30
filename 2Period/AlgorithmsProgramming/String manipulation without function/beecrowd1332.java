import java.util.Scanner;

public class beecrowd1332 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int t = 0; t < n; t++) {
            String palavra = sc.nextLine();

            if (palavra.length() == 5) {
                System.out.println(3);
            } else if (palavra.length() == 3) {
                // verifica letras de "one"
                if ((palavra.charAt(0) == 'o' && palavra.charAt(1) == 'n') ||
                    (palavra.charAt(0) == 'o' && palavra.charAt(2) == 'e') ||
                    (palavra.charAt(1) == 'n' && palavra.charAt(2) == 'e')) {
                    System.out.println(1);
                } else {
                    System.out.println(2);
                }
            }
        }

        sc.close();
    }
}
