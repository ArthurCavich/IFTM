package LP.Matrizes;

import java.util.Scanner;

public class Beecrowd5202 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int soma = 0;

        int n = sc.nextInt();

        int[][] matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = sc.nextInt();
            }
        }
        int l = sc.nextInt();
        int c = sc.nextInt();

        if (l <= 0 || c <= 0 || l > n || c > n) {
            System.out.println("Entrada incorreta.");
        } else {
            System.out.println("Matriz resultante:");
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < c; j++) {
                    soma += matriz[i][j];
                    System.out.print(matriz[i][j]);
                    if (j < c - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            System.out.println("Soma:");
            System.out.println(soma);
        }
        sc.close();
    }

}