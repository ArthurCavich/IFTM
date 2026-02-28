
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        Vetor<Pessoa> vetorPessoas = new Vetor<>(5);
        Vetor<Cidade> vetorCidades = new Vetor<>(5);

        int qtdPessoas = 0;
        int qtdCidades = 0;

        do {
            System.out.println("--- MENU ---");
            System.out.println("1 - CADASTRO DE PESSOA");
            System.out.println("2 - CADASTRO DE CIDADE");
            System.out.println("3 - PESQUISA");
            System.out.println("4 - ENCERRAR");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: String nome = sc.nextLine();
                        String sexo = sc.nextLine();
                        String naturalidade = sc.nextLine();
                        Pessoa p = new Pessoa(nome, sexo, naturalidade);
                        vetorPessoas.setElemento(qtdPessoas, p);
                        qtdPessoas++;
                    break;
                case 2: String cidade = sc.nextLine();
                        String adjetivo = sc.nextLine();
                        String estado = sc.nextLine();
                        Cidade c = new Cidade(cidade, adjetivo, estado);
                        vetorCidades.setElemento(qtdCidades, c);
                        qtdCidades++;
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);

        sc.close();
    }
}
