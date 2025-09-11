package LP.Vetores;
import java.util.Scanner;

public class Beecrowd1174{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double[] v = new double[100];
		
		for (int i = 0; i < 100; i++){
		    v[i] = sc.nextDouble();
		   
		}
		for (int i = 0; i < 100; i++){
		    if (v[i] <= 10){
		        System.out.printf("A[%d] = %.1f\n", i, v[i]);
		    }
		}
	}
}