import java.util.Scanner;

public class beecrowd2025 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int t = 0; t < n; t++) {
            String linha = sc.nextLine();
            String resultado = "";
            String palavra = "";

            for (int i = 0; i <= linha.length(); i++) {
                char c = (i < linha.length()) ? linha.charAt(i) : ' '; // adiciona espaço extra só para processar a última palavra

                if (c != ' ') {
                    palavra += c;
                }

                if (c == ' ' || i == linha.length()) {
                    // checar se palavra contém "oulupukk"
                    int len = palavra.length();
                    boolean contem = false;

                    for (int j = 0; j <= len - 8; j++) {
                        if (palavra.charAt(j) == 'o' &&
                            palavra.charAt(j+1) == 'u' &&
                            palavra.charAt(j+2) == 'l' &&
                            palavra.charAt(j+3) == 'u' &&
                            palavra.charAt(j+4) == 'p' &&
                            palavra.charAt(j+5) == 'u' &&
                            palavra.charAt(j+6) == 'k' &&
                            palavra.charAt(j+7) == 'k') {
                            contem = true;
                            break;
                        }
                    }

                    if (contem) {
                        String extras = "";
                        if (palavra.length() > 10) {
                            extras = palavra.substring(10);
                        }
                        palavra = "Joulupukki" + extras;
                    }

                    resultado += palavra;

                    // adiciona espaço somente se não for o fim da linha
                    if (c == ' ' && i < linha.length() - 1) {
                        resultado += " ";
                    }

                    palavra = "";
                }
            }

            System.out.println(resultado);
        }

        sc.close();
    }
}
