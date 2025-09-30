import java.util.Scanner;

public class beecrowd1192 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String seq = sc.nextLine();

            int primeiro = seq.charAt(0) - '0';
            char letra = seq.charAt(1);
            int segundo = seq.charAt(2) - '0';

            int resultado;

            if (primeiro == segundo) {
                // dígitos iguais → produto
                resultado = primeiro * segundo;
            } else if (letra >= 'A' && letra <= 'Z') {
                // maiúscula → segundo - primeiro
                resultado = segundo - primeiro;
            } else {
                // minúscula → soma
                resultado = primeiro + segundo;
            }

            System.out.println(resultado);
        }

        sc.close();
    }
}
