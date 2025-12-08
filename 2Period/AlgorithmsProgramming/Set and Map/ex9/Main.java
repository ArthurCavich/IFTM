
import java.util.Scanner;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lê a string de entrada
        String entrada = scanner.nextLine();

        // Cria um HashMap para armazenar as frequências
        LinkedHashMap<Character, Integer> frequencia = new LinkedHashMap<>();

        // Itera sobre cada caractere da string
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);

            if (c != ' ') {
                // Verifica se o caractere já existe no HashMap
                if (frequencia.containsKey(c)) {
                    // Se existe, pega o valor atual e adiciona 1
                    int valorAtual = frequencia.get(c);
                    frequencia.put(c, valorAtual + 1);
                } else {
                    // Se não existe, adiciona com valor 1
                    frequencia.put(c, 1);
                }
            }
        }

        // Exibe o resultado
        System.out.println(frequencia);

        scanner.close();
    }
}
