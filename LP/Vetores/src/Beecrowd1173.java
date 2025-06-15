import java.util.Scanner;

public class Beecrowd1173 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] n = new int[10];
        int v = sc.nextInt();

        n[0] = v;

        for (int i = 1; i < 10; i++) {
            n[i] = n[i - 1] * 2;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("N[" + i + "] = " + n[i]);
        }

        sc.close();
    }
}