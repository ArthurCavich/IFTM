
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static String menu() { //função do menu
        return "\n=== Menu ===\n"
                + "1) Adicionar um produto\n"
                + "2) Buscar um produto pelo id\n"
                + "3) Remover um produto pelo id\n"
                + "4) Listar todos os produtos\n"
                + "0) Encerrar o programa";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Produto> produtos = new HashMap<>();

        while (true) {
            System.out.println(menu());
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine(); // consome o \n deixado pelo nextInt

            switch (op) {
                case 1: // adicionar
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    if (produtos.containsKey(id)) {
                        System.out.println("\nProduto com esse ID já existe.");
                    } else {
                        produtos.put(id, new Produto(id, nome));
                        System.out.println("Produto adicionado com sucesso!");
                    }
                    break;

                case 2: // buscar
                    System.out.print("ID para busca: ");
                    String idBusca = sc.nextLine();
                    Produto encontrado = produtos.get(idBusca);

                    if (encontrado == null) {
                        System.out.println("\nProduto não encontrado.");
                    } else {
                        System.out.println(encontrado);
                    }
                    break;

                case 3: // remover
                    System.out.print("ID para remover: ");
                    String idRemover = sc.nextLine();
                    Produto removido = produtos.remove(idRemover);

                    if (removido == null) {
                        System.out.println("\nProduto não encontrado.");
                    } else {
                        System.out.println("Produto removido: " + removido);
                    }
                    break;

                case 4: // listar
                    if (produtos.isEmpty()) {
                        System.out.println("\nNenhum produto cadastrado.");
                    } else {
                        System.out.println("\n=== Produtos Cadastrados ===");
                        for (Produto p : produtos.values()) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 0: // sair
                    System.out.println("Encerrando o programa...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

}
