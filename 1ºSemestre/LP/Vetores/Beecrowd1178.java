package LP.Vetores;

import java.util.Scanner;

public class Beecrowd1178 {

    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double x = sc.nextDouble();
		double[] v = new double[101];
		
		v[0] = x;
		
		for (int i = 1; i <= 100; i++){
		    v[i] = v[i - 1] / 2;
		   
		}
		 for (int i = 0; i < 100; i++) {
             System.out.printf("N[%d] = %.4f\n", i, v[i]);
		    }
		}
}
