package LP.Vetores;
import java.util.Scanner;

public class Beecrowd1171 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        
        int[] frequencia = new int[2001];
        
        for (int i = 0; i < n; i++) {
            int numero = sc.nextInt();
            frequencia[numero]++;
        }
        for (int i = 0; i < frequencia.length; i++) {
            if (frequencia[i] > 0) {
                System.out.println(i + " aparece " + frequencia[i] + " vez(es)");
            }
    }  
    sc.close();
}
}