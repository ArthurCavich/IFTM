
import java.util.Scanner;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //declaro a lista hashset com nomes
        HashSet<String> nomes = new HashSet<>();

        //declaro o controle
        String nome;

        while (true) {
            nome = sc.nextLine(); //solicita o nome
            if (nome.equals("fim")) {// se o nome for igual "fim", encerra
                break;
            } else { //se não ele adiciona o nome na lista
                nomes.add(nome);
            }
        }

        String verifica = sc.nextLine(); //solicita a verificação

        if (nomes.contains(verifica)) { //se a lista tiver o nome verifica
            System.out.println(verifica);//retorna o nome
        } else {
            System.out.print("Não existe");//se não, retorna "Não existe"
        }

    }
}
