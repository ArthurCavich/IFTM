package LP.Matrizes;

import java.util.Scanner;

public class Beecrowd2385 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int P = sc.nextInt();
        int Q = sc.nextInt();
        int R = sc.nextInt();
        int S = sc.nextInt();
        int X = sc.nextInt();
        int Y = sc.nextInt();
        int I = sc.nextInt();
        int J = sc.nextInt();

        long resultado = 0;

        for (int k = 1; k <= N; k++) {
            int a = (P * I + Q * k) % X;
            int b = (R * k + S * J) % Y;
            resultado += (long) a * b;
        }

        System.out.println(resultado);
        sc.close();
    }
}
