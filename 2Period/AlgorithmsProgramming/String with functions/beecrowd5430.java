import java.util.Scanner;

public class beecrowd5430 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String frase = sc.nextLine();

        // 1. substitui as letras utilizando o método replace
        String fraseModificada = frase.replace('a', '@').replace('A', '@');

        // 3. transformei a frase modificada em vetor de palavras.
        String[] palavras = fraseModificada.split(" ");

        // 4. caso a frase tenha menos que 3 palavras imprima "Frase invalida."
        if (palavras.length < 3) {
            System.out.println("Frase invalida.");
        } else {
            // 2. impressão modificada
            System.out.println(fraseModificada);
            // se não, adiciona a primeira e ultima palavra em variáveis com as posições do
            // vetor palavras
            String primeira = palavras[0];
            String ultima = palavras[palavras.length - 1];

            // e compara a primeira palavra utilizando o método compareTo(ultima palavra) em
            // números
            int comparacao = primeira.compareTo(ultima);
            // acrescento a string transforma
            String transforma;
            // validação da comparação com retorno
            if (comparacao == 0) {
                transforma = "==";
            } else if (comparacao < 0) {
                transforma = "<";
            } else {
                transforma = ">";
            }

            System.out.println("palavra1 " + transforma + " palavra2");
        }

        sc.close();
    }
}