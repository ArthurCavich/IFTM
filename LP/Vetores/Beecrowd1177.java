package LP.Vetores;

import java.util.Scanner;

public class Beecrowd1177 {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		long[] v = new long[1001];
		
		for (int i = 0; i <= 1000; i++){
		    v[i] = i % t;
		   
		}
		 for (int i = 0; i < 1000; i++) {
            System.out.println("N[" + i + "] = " + v[i]);
		    }
		}
	}
