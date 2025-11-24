import java.util.ArrayList;
import java.util.Scanner;

public class exe04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> nomes = new ArrayList<>();

        // a) Adicionar nomes até digitar "fim"
        String nome;
        while (true) {
            nome = sc.nextLine(); // Lê o nome primeiro
            
            // Se for "fim", para de adicionar
            if (nome.equalsIgnoreCase("fim")) {
                break;
            }
            
            // b) Verificar se o convidado já existe antes de adicionar
            boolean existe = false;
            for (int i = 0; i < nomes.size(); i++) {
                if (nome.equals(nomes.get(i))) { // Usa .equals() para comparar strings
                    existe = true;
                    break;
                }
            }
            //caso exista, imprime a mensagem de erro
            if (existe) {
                System.out.println("convidado existe");
            } else {
                nomes.add(nome);
            }
        }

        // c) Mostrar a lista de convidados separados por hífen
        for (int i = 0; i < nomes.size(); i++) {
            System.out.print(nomes.get(i));
            if (i < nomes.size() - 1) {
                System.out.print("-");
            }
        }
        System.out.println();

        // d) Buscar um nome na lista
        String nomeBusca = sc.nextLine();
        
        boolean encontrado = false;
        for (int i = 0; i < nomes.size(); i++) {
            if (nomeBusca.equals(nomes.get(i))) {
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("Não existe convidado chamado " + nomeBusca);
        }
        
        sc.close();
    }
}
