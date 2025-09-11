package LP.Vetores;

import java.util.Scanner;

public class Beecrowd1179 {
    
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] par = new int[5];
        int[] impar = new int[5];
        

        int parCount = 0;
        int imparCount = 0;
        

        for (int i = 0; i < 15; i++) {
            int valor = sc.nextInt();
            

            if (valor % 2 == 0) {

                par[parCount] = valor;
                parCount++;
                
                if (parCount == 5) {
                    for (int j = 0; j < 5; j++) {
                        System.out.println("par[" + j + "] = " + par[j]);
                    }
                    parCount = 0;
                }
            } else {

                impar[imparCount] = valor;
                imparCount++;
                
                if (imparCount == 5) {
                    for (int j = 0; j < 5; j++) {
                        System.out.println("impar[" + j + "] = " + impar[j]);
                    }
                    imparCount = 0; 
                }
            }
        }
        for (int i = 0; i < imparCount; i++) {
            System.out.println("impar[" + i + "] = " + impar[i]);
        }
        for (int i = 0; i < parCount; i++) {
            System.out.println("par[" + i + "] = " + par[i]);
        }

    }
}

