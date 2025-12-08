
import java.util.Scanner;
import java.util.HashSet;

public class Main {

    //modularizei o código, sem utilizar classe contato.
    public static HashSet<String> nomes = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            exibirMenu(); //chama o menu
            int opcao = sc.nextInt();
            sc.nextLine(); //limpar o buffer

            switch (opcao) {//switch case chamando cada função do código
                case 1:
                    adicionarConvidado(sc);
                    break;
                case 2:
                    removerConvidado(sc);
                    break;
                case 3:
                    verificarConvidado(sc);
                    break;
                case 4:
                    exibirConvidados();
                    break;
                case 5:
                    quantidadeConvidados();
                    break;
                case 6:
                    apagarConvidados(sc);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida! rs\nTente novamente sz");
                    throw new AssertionError();
            }
        }
    }

    //criei uma função que adiciona convidado
    public static void adicionarConvidado(Scanner sc) {//scanner como parâmetro
        System.out.print("Digite o nome completo do convidado: ");//ux aplicada
        String nome = sc.nextLine();//solicita o nome

        if (nomes.add(nome)) {//por padrão o hashset valida se tem outro com o mesmo nome
            System.out.println("Nome adicionado com sucesso!");//se não tiver, adiciona
        } else {
            System.out.println("Este convidado já está na lista!");//se não ele retorna o erro
        }
    }

    public static void removerConvidado(Scanner sc) {//método que remove
        System.out.println("Nome do convidado a ser excluído?");//ux
        String excluir = sc.nextLine();

        if (nomes.remove(excluir)) {//método remove, que valida e excluí, sem necessidade de usar contains antes
            System.out.println("Convidado excluído com sucesso!");
        } else {
            System.out.println("Convidado não encontrado");
        }
    }

    private static void verificarConvidado(Scanner sc) {//função que verifica convidado
        System.out.println("Qual o nome do convidado?");
        String verifica = sc.nextLine();

        if (nomes.contains(verifica)) {//utiliza o contains para validar
            System.out.println("Convidado" + verifica + "encontrado na lista!");
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    private static void exibirConvidados() {//função que exibe os convidados
        if (nomes.isEmpty()) {//se estiver vazia, retorna lista vazia
            System.out.println("Lista vazia!");
        } else {//se não, retorna com a lista de convidados
            System.out.println("\n=== Lista de Convidados ===");
            int i = 1;
            for (String nome : nomes) {
                System.out.println(i + " - " + nome);
                i++;
            }
        }
    }

    private static void quantidadeConvidados() {
        System.out.println("Quantidade de convidados cadastrados: " + nomes.size());//método size para retornar o tamanho da lista em numeros
    }

    private static void apagarConvidados(Scanner sc) {// apaga convidado com o método clear
        System.out.println("Tem certeza que deseja apagar todos os convidados?\n(1) Sim | (2) Não");
        int resposta = sc.nextInt();
        sc.nextLine();

        if (resposta < 1 || resposta > 2) {
            System.out.println("Opção inválida");
        } else if (resposta == 1) {
            nomes.clear();
            System.out.println("Lista vazia!");
        } else {
            System.out.println("Operação cancelada.");
        }

    }

    private static void exibirMenu() {//menu exibido
        System.out.println("\n=== Sistema de Gerenciamento de Convidados ===");
        System.out.println("1. Adicionar um convidado");
        System.out.println("2. Remover um convidado");
        System.out.println("3. Verificar se um convidado está na lista");
        System.out.println("4. Exibir todos os convidados");
        System.out.println("5. Imprimir quantidade de convidados cadastrados");
        System.out.println("6. Apagar todos os convidados");
        System.out.println("0. Sair");
        System.out.print("\nEscolha uma opção: ");
    }

}
