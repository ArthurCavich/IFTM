package LP.Vetores;

import java.util.Scanner;

public class Beecrowd1176 {
    
    	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		long[] fib = new long[61];
		
		fib[0] = 0;
		fib[1] = 1;
		
		for (int i = 2; i <= 60; i++){
		    fib[i] = fib[i - 1] + fib[i - 2];
		   
		}
		 for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
           
            System.out.println("Fib(" + n + ") = " + fib[n]);
		    }
		}
}
