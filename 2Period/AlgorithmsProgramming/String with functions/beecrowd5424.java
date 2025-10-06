import java.util.Scanner;

public class beecrowd5424 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String palavra = sc.nextLine();
        //eu criei um for para percorrer a palavra e imprimir a posição do vetor + a letra
        for (int i = 0; i < palavra.length(); i++) {
            System.out.println(i + " - " + palavra.charAt(i));
        }

        sc.close();
    }
}
