import java.util.ArrayList;
import java.util.Scanner;

public class exe05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Cadastrar 5 nomes em um ArrayList
        ArrayList<String> nomes = new ArrayList<>();
        
        // Lê os 5 nomes
        for (int i = 0; i < 5; i++) {
            String nome = sc.nextLine();
            nomes.add(nome);
        }
        
        // Imprime os nomes enumerados de 1 a 5
        System.out.println("Qual dos nomes abaixo você deseja excluir da lista?");
        for (int i = 0; i < nomes.size(); i++) {
            System.out.println((i + 1) + ". " + nomes.get(i));
        }
        
        // Pede para o usuário informar o número correspondente ao nome a ser excluído
        System.out.println("Informe o número correspondente ao nome a ser excluído:");
        int numero = sc.nextInt();
        
        // Remove o nome correspondente (número - 1 porque o índice começa em 0)
        nomes.remove(numero - 1);
        
        // Mostra os nomes restantes re-enumerados
        System.out.println("Resultado:");
        for (int i = 0; i < nomes.size(); i++) {
            System.out.println((i + 1) + ". " + nomes.get(i));
        }
        
        sc.close();
    }
}

