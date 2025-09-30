import java.util.Scanner;

public class beecrowd2062 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());
        String linha = sc.nextLine();

        String palavra = "";
        String textoCorrigido = "";

        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);

            if (c != ' ') {
                palavra += c; // vai formando a palavra
            }

            if (c == ' ' || i == linha.length() - 1) {
                // corrige se a palavra tiver exatamente 3 letras
                if (palavra.length() == 3) {
                    if (palavra.charAt(0) == 'O' && palavra.charAt(1) == 'B') {
                        palavra = "OBI";
                    } else if (palavra.charAt(0) == 'U' && palavra.charAt(1) == 'R') {
                        palavra = "URI";
                    }
                }

                textoCorrigido += palavra;

                if (c == ' ') {
                    textoCorrigido += " "; // mantém o espaço
                }

                palavra = ""; // reinicia para a próxima palavra
            }
        }

        System.out.println(textoCorrigido);

        sc.close();
    }
}
