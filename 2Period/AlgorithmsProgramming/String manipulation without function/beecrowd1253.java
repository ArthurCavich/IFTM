import java.util.Scanner;

public class beecrowd1253 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine()); // número de casos

        for (int t = 0; t < n; t++) {
            String cifrada = sc.nextLine();
            int desloc = Integer.parseInt(sc.nextLine());

            char[] letras = cifrada.toCharArray();
            String decodificada = "";

            for (int i = 0; i < letras.length; i++) {
                char c = letras[i];
                // decodifica subtraindo o deslocamento
                c = (char)(c - desloc);
                
                // se passar de 'A', volta para o final do alfabeto
                if (c < 'A') {
                    c = (char)(c + 26);
                }

                decodificada += String.valueOf(c);
            }

            System.out.println(decodificada);
        }

        sc.close();
    }
}
