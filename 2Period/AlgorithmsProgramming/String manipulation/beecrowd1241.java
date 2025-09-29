import java.util.Scanner;

public class beecrowd1241 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int t = 0; t < n; t++) {
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

            // pega os últimos caracteres de A do tamanho de B
            boolean encaixa = true;
            int lenA = A.length();
            int lenB = B.length();

            if (lenB > lenA) {
                encaixa = false;
            } else {
                for (int i = 0; i < lenB; i++) {
                    if (A.charAt(lenA - lenB + i) != B.charAt(i)) {
                        encaixa = false;
                        break;
                    }
                }
            }

            if (encaixa) {
                System.out.println("encaixa");
            } else {
                System.out.println("nao encaixa");
            }
        }

        sc.close();
    }
}
