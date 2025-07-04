package LP.Matrizes;
import java.util.Scanner;

public class Beecrowd1189 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char O = sc.next().charAt(0);
        int n = 12;
        double[][] matriz = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = sc.nextDouble();
            }
        }

        double soma = 0;
        int contador = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (i + j < 11) {
                    soma += matriz[i][j];
                    contador++;
                }
            }
        }

        if (O == 'S') {
            System.out.printf("%.1f\n", soma);
        } else if (O == 'M') {
            double media = soma / contador;
            System.out.printf("%.1f\n", media);
        }

        sc.close();
    }
}
