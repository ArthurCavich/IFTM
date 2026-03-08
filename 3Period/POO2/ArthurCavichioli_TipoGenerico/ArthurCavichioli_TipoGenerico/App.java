
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // vetores genéricos já instanciados
        Vetor<Pessoa> vetorPessoas = new Vetor<>(5);
        Vetor<Cidade> vetorCidades = new Vetor<>(5);

        // quantos já foram cadastrados em cada vetor
        int qtdPessoas = 0;
        int qtdCidades = 0;

        int opcao = 0;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - CADASTRO DE PESSOA");
            System.out.println("2 - CADASTRO DE CIDADE");
            System.out.println("3 - PESQUISA");
            System.out.println("4 - ENCERRAR");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consome o enter

            switch (opcao) {
                case 1:
                    if (qtdPessoas == vetorPessoas.getTamanho()) {
                        System.out.println("Vetor de pessoas cheio.");
                        break;
                    }

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Sexo (M/F): ");
                    String sexo = sc.nextLine().trim().toUpperCase();
                    System.out.print("Naturalidade (cidade de nascimento): ");
                    String naturalidade = sc.nextLine();

                    Pessoa p = new Pessoa(nome, sexo, naturalidade);
                    vetorPessoas.setElemento(qtdPessoas, p);
                    qtdPessoas++;

                    System.out.println("Pessoa cadastrada com sucesso.");
                    break;

                case 2:
                    if (qtdCidades == vetorCidades.getTamanho()) {
                        System.out.println("Vetor de cidades cheio.");
                        break;
                    }

                    System.out.print("Nome da cidade: ");
                    String nomeCidade = sc.nextLine();
                    System.out.print("Adjetivo pátrio (ex.: uberlandense, cuiabano/cuiabana): ");
                    String adjetivo = sc.nextLine();
                    System.out.print("Estado: ");
                    String estado = sc.nextLine();

                    Cidade c = new Cidade(nomeCidade, adjetivo, estado);
                    vetorCidades.setElemento(qtdCidades, c);
                    qtdCidades++;

                    System.out.println("Cidade cadastrada com sucesso.");
                    break;

                case 3:
                    System.out.print("Digite o nome da pessoa para pesquisa: ");
                    String nomeBuscado = sc.nextLine();

                    // busca da pessoa
                    Pessoa pessoaEncontrada = null;
                    for (int i = 0; i < qtdPessoas; i++) {
                        Pessoa px = vetorPessoas.getElemento(i);
                        if (px != null && px.getNome().equalsIgnoreCase(nomeBuscado)) {
                            pessoaEncontrada = px;
                            break;
                        }
                    }

                    if (pessoaEncontrada == null) {
                        // não encontrou a pessoa
                        System.out.println(nomeBuscado + " nasceu em cidade desconhecida.");
                    } else {
                        // encontrou a pessoa, agora procurar a cidade
                        String nat = pessoaEncontrada.getNaturalidade();
                        Cidade cidadeEncontrada = null;

                        for (int i = 0; i < qtdCidades; i++) {
                            Cidade cx = vetorCidades.getElemento(i);
                            if (cx != null && cx.getNome().equalsIgnoreCase(nat)) {
                                cidadeEncontrada = cx;
                                break;
                            }
                        }

                        if (cidadeEncontrada == null) {
                            // cidade não cadastrada
                            System.out.println(pessoaEncontrada.getNome()
                                    + " nasceu em " + nat + ".");
                        } else {
                            // pessoa e cidade cadastradas
                            String artigo = pessoaEncontrada.getSexo().equals("M") ? "O" : "A";

                            String adj = cidadeEncontrada.getAdjetivo();
                            if (adj.contains("/")) {
                                String[] partes = adj.split("/");
                                adj = pessoaEncontrada.getSexo().equals("M")
                                        ? partes[0].trim()
                                        : partes[1].trim();
                            }

                            System.out.println(artigo + " " + adj + " "
                                    + pessoaEncontrada.getNome()
                                    + " nasceu em " + cidadeEncontrada.getNome()
                                    + " - " + cidadeEncontrada.getEstado() + ".");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Encerrando o programa.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);

        sc.close();
    }
}
