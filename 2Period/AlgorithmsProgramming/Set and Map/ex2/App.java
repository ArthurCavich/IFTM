
import java.util.Scanner;
import java.util.HashSet;

public class App {

    public static void main(String[] args) {
        //scanner para entrada do usuário
        Scanner sc = new Scanner(System.in);
        //criando as duas listas HashSet
        HashSet<Integer> numeros1 = new HashSet<>();
        HashSet<Integer> numeros2 = new HashSet<>();
        //inserindo os numeros com while, encerrando quando entra numb negat.
        while (true) {
            int n1 = sc.nextInt();
            if (n1 < 0) {
                break;
            } else {
                numeros1.add(n1);
            }
        }
        while (true) {
            int n2 = sc.nextInt();
            if (n2 < 0) {
                break;
            } else {
                numeros2.add(n2);
            }
        }
        //crio o terceiro HashSet como intersecao
        HashSet<Integer> intersecao = new HashSet<>();

        //percorro a lista1 e verifico se existe na lista2
        for (Integer num : numeros1){ 

            //caso contenha, insiro na intersecao
            if (numeros2.contains(num)){
                intersecao.add(num);
            }
        }
        //percorro intersecao imprimindo o resultado
        for (Integer num : intersecao){
            System.out.print(num + " ");
        }
    }
}

