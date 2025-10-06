import java.util.Scanner;

public class beecrowd5428 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        //utilizei o metodo indexof para encontrar a string s2 dentro de s1.
        int posicao = s1.indexOf(s2);

        if (posicao != -1) {
            //se houver s2 dentro de s1, ele retorna o índice que é utilizado na posição inicial da substring
            System.out.println(s1.substring(posicao));
        } else {
            //ele não encontrou e imprime s1 completo
            System.out.println(s1);
        }

        sc.close();
    }
}