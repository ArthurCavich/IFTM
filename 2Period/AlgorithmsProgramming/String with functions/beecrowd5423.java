import java.util.Scanner;

public class beecrowd5423 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String frase = sc.nextLine();

        //fiz o .length para saber se tem menos de 5 caracters, retornando "invalida" se for true.
        if (frase.length() < 5) {
            System.out.println("invalida");
        } else {
            //aqui eu utilizei o charAt para pegar a primeira letra da frase colocando em outra variável
            char primeiro = frase.charAt(0);
            //aqui eu peguei um ultimo caracter pegando a frase toda (através do length - 1)
            char ultimo = frase.charAt(frase.length() - 1);
            //aqui eu comparo as variáveis para retorno
            if (primeiro == ultimo) {
                System.out.println("iguais");
            } else {
                System.out.println("diferentes");
            }
        }

        sc.close();
    }
}