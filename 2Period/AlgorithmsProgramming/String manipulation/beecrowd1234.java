import java.util.Scanner;

public class beecrowd1234 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            char[] letras = linha.toCharArray();
            boolean maiuscula = true;

            for (int i = 0; i < letras.length; i++) {
                char c = letras[i];

                if (c != ' ') {
                    if (maiuscula) {
                        if (c >= 'a' && c <= 'z') {
                            c = (char) (c - ('a' - 'A'));
                        }
                    } else {
                        if (c >= 'A' && c <= 'Z') {
                            c = (char) (c + ('a' - 'A'));
                        }
                    }
                    letras[i] = c;
                    maiuscula = !maiuscula;
                }
            }
            System.out.println(new String(letras));
        }

        sc.close();
    }
}
