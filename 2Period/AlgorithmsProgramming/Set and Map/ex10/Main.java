import java.util.HashMap;
import java.util.Scanner;

public class Main {
    // HashMap para armazenar os alunos, onde a chave é a matrícula
    private static HashMap<String, Aluno> alunos = new HashMap<>();
    // HashMap para armazenar as notas dos alunos, onde a chave é a matrícula
    private static HashMap<String, Double> notas = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao; 


        do {
            exibirMenu(); // Exibe o menu de opções
            opcao = sc.nextInt(); // Lê a opção escolhida pelo usuário
            sc.nextLine(); // Limpa o buffer do Scanner

            // Estrutura switch para tratar a opção escolhida
            switch (opcao) {
                case 1: 
                    adicionarAluno(); 
                    break; 
                case 2: 
                    adicionarNota(); 
                    break; 
                case 3: 
                    calcularMediaGeral(); // Chama o método para calcular média
                    break; 
                case 4: // Opção para imprimir os dados dos alunos
                    imprimirDadosAlunos(); // Chama o método para imprimir dados
                    break; 
                case 0: // Opção para sair do programa
                    System.out.println("Encerrando o programa..."); // Exibe mensagem de encerramento
                    break; 
                default: // Caso a opção seja inválida
                    System.out.println("Opção inválida! Tente novamente."); // Exibe mensagem de erro
            }

            System.out.println(); // Imprime uma linha em branco

        } while (opcao != 0); // Continua o laço enquanto a opção não for 0

        sc.close(); // Fecha o Scanner
    }

    // Método que exibe o menu de opções para o usuário
    private static void exibirMenu() {
        System.out.println("===== MENU DE GERENCIAMENTO DE ALUNOS ====="); 
        System.out.println("1 - Adicionar um aluno");
        System.out.println("2 - Adicionar uma nota a um aluno");
        System.out.println("3 - Calcular a média das notas de todos os alunos"); 
        System.out.println("4 - Imprimir os dados dos alunos"); 
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Método para adicionar um novo aluno ao sistema
    private static void adicionarAluno() {
        System.out.println("\n--- Adicionar Aluno ---"); // Título da seção

        System.out.print("Digite a matrícula: "); // Solicita a matrícula
        String matricula = sc.nextLine(); // Lê a matrícula digitada

        // Verifica se já existe um aluno com essa matrícula
        if (alunos.containsKey(matricula)) {
            System.out.println("Erro: Já existe um aluno com esta matrícula!"); // Exibe mensagem de erro
            return; // Encerra o método
        }

        System.out.print("Digite o nome: "); // Solicita o nome
        String nome = sc.nextLine(); // Lê o nome digitado

        System.out.print("Digite o CPF: "); // Solicita o CPF
        String cpf = sc.nextLine(); // Lê o CPF digitado

        Aluno aluno = new Aluno(matricula, nome, cpf); // Cria um novo objeto Aluno
        alunos.put(matricula, aluno); // Adiciona o aluno ao HashMap

        System.out.println("Aluno adicionado com sucesso!"); // Exibe mensagem de sucesso
    }

    // Método para adicionar uma nota a um aluno existente
    private static void adicionarNota() {
        System.out.println("\n--- Adicionar Nota ---");

        // Verifica se há alunos cadastrados
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado! Adicione um aluno primeiro."); // Exibe mensagem de aviso
            return; // Encerra o método
        }

        System.out.print("Digite a matrícula do aluno: "); // Solicita a matrícula
        String matricula = sc.nextLine(); // Lê a matrícula digitada

        // Verifica se o aluno existe
        if (!alunos.containsKey(matricula)) {
            System.out.println("Erro: Aluno não encontrado!"); // Exibe mensagem de erro
            return; // Encerra o método
        }

        System.out.print("Digite a nota: "); // Solicita a nota
        double nota = sc.nextDouble(); // Lê a nota digitada
        sc.nextLine(); // Limpa o buffer do Scanner

        notas.put(matricula, nota); // Adiciona a nota ao HashMap
        System.out.println("Nota adicionada com sucesso para o aluno " + alunos.get(matricula).getNome() + "!"); // Exibe mensagem de sucesso
    }

    // Método para calcular a média das notas de todos os alunos
    private static void calcularMediaGeral() {
        System.out.println("\n--- Média das Notas ---"); // Título da seção

        // Verifica se há notas cadastradas
        if (notas.isEmpty()) {
            System.out.println("Nenhuma nota cadastrada!"); // Exibe mensagem de aviso
            return; // Encerra o método
        }

        double soma = 0; // Variável para acumular a soma das notas
        // Laço para percorrer todas as notas
        for (Double nota : notas.values()) {
            soma += nota; // Adiciona a nota à soma
        }

        double media = soma / notas.size(); // Calcula a média dividindo a soma pelo número de notas
        System.out.printf("A média das notas de todos os alunos é: %.2f%n", media); // Exibe a média formatada
        System.out.println("Total de alunos com notas cadastradas: " + notas.size()); // Exibe o total de alunos com notas
    }

    // Método para imprimir os dados de todos os alunos cadastrados
    private static void imprimirDadosAlunos() {
        System.out.println("\n--- Dados dos Alunos ---"); // Título da seção

        // Verifica se há alunos cadastrados
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!"); // Exibe mensagem de aviso
            return; // Encerra o método
        }

        // Laço para percorrer todas as matrículas do HashMap de alunos
        for (String matricula : alunos.keySet()) {
            Aluno aluno = alunos.get(matricula); // Obtém o aluno pela matrícula
            System.out.println(aluno); // Imprime os dados do aluno 

            // Verifica se o aluno possui nota cadastrada
            if (notas.containsKey(matricula)) {
                System.out.printf("   Nota: %.2f%n", notas.get(matricula)); // Imprime a nota formatada
            } else {
                System.out.println("   Nota: Não cadastrada"); // Imprime mensagem indicando que não há nota
            }
            System.out.println("-------------------------------------------"); // Imprime uma linha separadora
        }
    }
}
