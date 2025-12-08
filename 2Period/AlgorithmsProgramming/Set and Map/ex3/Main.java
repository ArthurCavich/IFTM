
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //criei o arraylist
        ArrayList<Integer> listaOrdenada = new ArrayList<>();

        //enquanto verdadeiro, adiciona o numero na lista
        while (true) {
            int num = sc.nextInt();
            if (num < 0) {
                break;
            } else {
                listaOrdenada.add(num);
            }
        }

        //passei o array para hashset
        HashSet<Integer> hashSet = new HashSet<>(listaOrdenada);

        //passei o hash para arraylist novamente
        ArrayList<Integer> listaCorreta = new ArrayList<>(hashSet);

        //percorri a lista correta imprimindo
        for (Integer num : listaCorreta){
            System.out.print(num + " ");
        }
    }

}
