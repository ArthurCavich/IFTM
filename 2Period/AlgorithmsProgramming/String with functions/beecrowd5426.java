import java.util.Scanner;

public class beecrowd5426 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String texto = sc.nextLine();
        String palavra = sc.nextLine();

        // utilizei o split para separar cada palavra da frase em uma posição do array
        String[] palavras = texto.split(" ");
        int contador = 0;

        //com o for, eu percorro esse vetor de palavras e utilizo o método contains para saber se tem a palavra dentro e retorna com o contador++
        for (int i = 0; i < palavras.length; i++) {
            if (palavras[i].contains(palavra)) {
                contador++;
            }
        }

        System.out.println(contador);

        sc.close();
    }
}