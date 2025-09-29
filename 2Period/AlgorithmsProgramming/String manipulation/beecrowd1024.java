import java.util.Scanner;

public class beecrowd1024 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //número de linhas convertido em int
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String linha = sc.nextLine();

            // Primeira passada: desloca letras em +3
            char[] primeira = linha.toCharArray();
            for (int j = 0; j < primeira.length; j++) {
                char c = primeira[j];
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    primeira[j] = (char) (c + 3);
                }
            }

            // Segunda passada: inverter
            char[] segunda = new char[primeira.length];
            for (int j = 0; j < primeira.length; j++) {
                segunda[j] = primeira[primeira.length - 1 - j];
            }

            // Terceira passada: metade em diante desloca -1
            int metade = segunda.length / 2;
            for (int j = metade; j < segunda.length; j++) {
                segunda[j] = (char) (segunda[j] - 1);
            }

            // Imprime resultado final
            System.out.println(new String(segunda));
        }

        sc.close();
    }
}
