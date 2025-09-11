package LP.Matrizes;

import java.util.Scanner;

public class Beecrowd1181 {
    public static void main(String[] args) {
        
    
    Scanner sc = new Scanner(System.in);

		int l = sc.nextInt();
		char t = sc.next().charAt(0);
		int n = 12;

		double[][] matriz = new double[12][12];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = sc.nextDouble();
			}
		}
        double soma = 0;
        double media = 0;
        
        for (int j = 0; j < n; j++){
            soma += matriz[l][j];
           // soma = soma + matriz[l][j];
        }
        if (t == 'S'){
            System.out.printf("%.1f\n", soma);
        } else{
            media = soma / n;
            System.out.printf("%.1f\n", media);
        }
        
	}
}
