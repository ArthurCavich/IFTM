package LP.Matrizes;

import java.util.Scanner;

public class Beecrowd5146 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int contador = 0;

        int[][] matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = sc.nextInt();

                if (matriz[i][j] < 0) {
                    contador++;
                }
            }
        }
        System.out.println("DIAGONAL PRINCIPAL:");
        for (int i = 0; i < n; i++) {
            System.out.println(matriz[i][i]);
        }
        System.out.println("QUANTIDADE DE NEGATIVOS = " + contador);
        sc.close();
    }

}