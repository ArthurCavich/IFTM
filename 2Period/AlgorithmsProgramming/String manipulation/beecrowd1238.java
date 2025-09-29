import java.util.Scanner;

public class beecrowd1238 {
    
    // Função que combina duas strings conforme o enunciado
    public static String combinar(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int len1 = c1.length;
        int len2 = c2.length;
        int menor = len1 < len2 ? len1 : len2;

        String resultado = "";

        // intercala caracteres da menor string
        for (int i = 0; i < menor; i++) {
            resultado += String.valueOf(c1[i]);
            resultado += String.valueOf(c2[i]);
        }

        // adiciona o restante da string maior
        if (len1 > len2) {
            for (int i = menor; i < len1; i++) {
                resultado += String.valueOf(c1[i]);
            }
        } else if (len2 > len1) {
            for (int i = menor; i < len2; i++) {
                resultado += String.valueOf(c2[i]);
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int t = 0; t < n; t++) {
            String linha = sc.nextLine();

            // Separar manualmente as duas strings
            int espaco = -1;
            for (int i = 0; i < linha.length(); i++) {
                if (linha.charAt(i) == ' ') {
                    espaco = i;
                    break;
                }
            }

            String s1 = "";
            String s2 = "";

            // Monta primeira string
            for (int i = 0; i < espaco; i++) {
                s1 += String.valueOf(linha.charAt(i));
            }

            // Monta segunda string
            for (int i = espaco + 1; i < linha.length(); i++) {
                s2 += String.valueOf(linha.charAt(i));
            }

            System.out.println(combinar(s1, s2));
        }

        sc.close();
    }
}
