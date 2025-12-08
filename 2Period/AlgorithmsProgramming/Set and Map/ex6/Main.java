
import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashMap<Integer, Integer> contagem = new HashMap<>(); //declaro a hashmap contagem com dois parametros

        while (true) {//enquanto verdadeiro
            int numero = sc.nextInt();//solicita o número
            if (numero < 0) {//se for negado, encerra
                break;
            }
            if (contagem.containsKey(numero)) {// se dentor da lista conter o número como key
                int valorAtual = contagem.get(numero);//pega o valor dessa posição
                contagem.put(numero, valorAtual + 1); // e insere o mesmo na posição com +1
            } else {//senão tiver essa key, retorna com o valor 1;
                contagem.put(numero, 1);
            }
        }
        //declarei o treeset para ordenar a lista
        TreeSet<Integer> contagemOrdenada = new TreeSet<>(contagem.keySet());

        for (Integer chave : contagemOrdenada){//percorro a lista ordenada
            System.out.println(chave + ": " + contagem.get(chave));
        }

        sc.close();
    }
}
