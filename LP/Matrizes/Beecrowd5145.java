package LP.Matrizes;

import java.util.Scanner;

public class Beecrowd5145{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int linha = sc.nextInt();
        int coluna = sc.nextInt();

        int[][] matriz = new int[linha][coluna];

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                matriz[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < linha; i++) {
            for (int j = coluna - 1; j < coluna; j++) {
                System.out.print(matriz[i][j]);
                if (j < coluna - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        sc.close();
    }
}