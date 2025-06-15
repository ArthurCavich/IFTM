import java.util.Scanner;
public class Beecrowd1170 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        
        int N = sc.nextInt();

        
        double[] comida = new double[N];

        
        for (int i = 0; i < n; i++) {
            comida[i] = sc.nextDouble();
        }

       
        for (int i = 0; i < N; i++) {
            int dias = 0;
            while (comida[i] > 1.0) {
                comida[i] /= 2.0;
                dias++;
            }
            System.out.println(dias + " dias");
        }

        sc.close();
    }
}