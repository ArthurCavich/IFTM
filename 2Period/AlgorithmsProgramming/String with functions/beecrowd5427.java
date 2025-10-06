import java.util.Scanner;

public class beecrowd5427 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String frase = sc.nextLine();

        //eu dividi as palavras da frase com .split
        String[] palavras = frase.split(" ");

        //com o for, eu percorri as palavras e, caso a palavra de mais de 3 caracters, imprimi os 3 primeiros caracteres.
        for (int i = 0; i < palavras.length; i++) {
            
            if (palavras[i].length() > 3) {
                //com o substring, consigo imprimir somente os indices do 0 ao 2 (3 é o ponto de parada)
                System.out.println(palavras[i].substring(0, 3));
            }
        }

        sc.close();
    }
}