package LP.Vetores;

import java.util.Scanner;

public class Beecrowd1175 {
    
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] v = new int[20];
		
		for (int i = 19; i >= 0; i--){
		    v[i] = sc.nextInt();
		   
		}
		for (int i = 0; i < 20; i++){
		        System.out.println("N[" + i + "] = " + v[i]);
		    }
		}
	}

