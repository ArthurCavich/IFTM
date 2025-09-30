import java.util.Scanner;

public class beecrowd1607 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = Integer.parseInt(sc.nextLine());

        for (int t = 0; t < T; t++) {
            String linha = sc.nextLine();

            // separar A e B manualmente
            int espaco = -1;
            for (int i = 0; i < linha.length(); i++) {
                if (linha.charAt(i) == ' ') {
                    espaco = i;
                    break;
                }
            }

            String A = "";
            String B = "";
            for (int i = 0; i < espaco; i++) {
                A += linha.charAt(i);
            }
            for (int i = espaco + 1; i < linha.length(); i++) {
                B += linha.charAt(i);
            }

            int total = 0;

            for (int i = 0; i < A.length(); i++) {
                char a = A.charAt(i);
                char b = B.charAt(i);
                int diff;

                if (b >= a) {
                    diff = b - a;
                } else {
                    diff = 26 - (a - b);
                }

                total += diff;
            }

            System.out.println(total);
        }

        sc.close();
    }
}
