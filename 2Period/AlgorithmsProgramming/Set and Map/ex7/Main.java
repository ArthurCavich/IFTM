
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //lista recebendo dois parametros
        HashMap<String, Integer> usuarios = new HashMap<>();

        while (true) {//enquanto verdadeiro
            String letra = sc.next();//solicita a letra

            if (letra.equalsIgnoreCase("Fim")) {
                break;//compara se for "fim", ignorando maiusculo e minusculo
            }

            Integer num = sc.nextInt();//solicita o num
            sc.nextLine(); //limpa o buffer

            usuarios.put(letra, num);//insere os dois na lista

        }

        // hashmap invertido
        HashMap<Integer, String> users = new HashMap<>();

        for (Map.Entry<String, Integer> entrada : usuarios.entrySet()) {//percorro a lista
            // map.entry<String, Integer> trás a lista anterior
            // .entrySet() retorna todas as entradas

            // adiciono os valores invertidos
            users.put(entrada.getValue(), entrada.getKey());
        }

        System.out.println(users); //imprimo a lista, sai no padrão solicitado da saída
        sc.close();
    }
}
