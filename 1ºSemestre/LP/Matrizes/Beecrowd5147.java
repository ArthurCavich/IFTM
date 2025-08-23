package LP.Matrizes;

import java.util.Scanner;

public class Beecrowd5147 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int diagonalPrincipal = 0;
        int acimaDiagonal = 0;
        int abaixoDiagonal = 0;

        int[][] matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    diagonalPrincipal += matriz[i][j];
                } else if (j > i) {
                    acimaDiagonal += matriz[i][j];
                } else if (i > j) {
                    abaixoDiagonal += matriz[i][j];
                }
            }
        }
        System.out.println("DIAGONAL PRINCIPAL = " + diagonalPrincipal);
        System.out.println("ACIMA DA DIAGONAL = " + acimaDiagonal);
        System.out.println("ABAIXO DA DIAGONAL = " + abaixoDiagonal);

        sc.close();
    }

}