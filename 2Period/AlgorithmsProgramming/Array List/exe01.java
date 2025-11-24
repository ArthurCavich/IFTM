import java.util.Scanner;
import java.util.ArrayList;

public class exe01 {
    void main() {
        Scanner sc = new Scanner(System.in);

        //declaro o ArrayList para armazenar os numeros inteiros
        ArrayList<Integer> numeros = new ArrayList<>();

        //declaro a variavel numero para armazenar o numero inteiro
        int numero = 0;

        //while (true) cria um loop infinito que só para com 'break'
        while (true) { 
            //le o proximo numero inteiro digitado pelo usuario
            numero = sc.nextInt();
            if (numero < 0) {
                break;
            }
            numeros.add(numero);
        }
        // variável de soma
        int soma = 0;
        //foreach para percorrer a lista e somar os elementos
        for (int num : numeros) {
            soma += num;
        }

        //imprime o tamanho total com método .size
        System.out.println(numeros.size());
        //imprime a soma
        System.out.println("soma = " + soma);
        sc.close();
    }
}
