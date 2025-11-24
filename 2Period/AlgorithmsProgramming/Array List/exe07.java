import java.util.ArrayList;
import java.util.Scanner;

public class exe07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        int opcao;
        
        do {
            // Mostra o menu
            System.out.println("\n=== MENU DE GERENCIAMENTO DE CLIENTES ===");
            System.out.println("1. Inserir novo Cliente");
            System.out.println("2. Listar todos os clientes");
            System.out.println("3. Buscar Cliente pelo CPF");
            System.out.println("4. Remover cliente pelo CPF");
            System.out.println("5. Apagar Lista");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); // Limpa o buffer do teclado
            
            switch (opcao) {
                case 1:
                    // a) Adicionar Clientes
                    System.out.println("\n=== INSERIR NOVO CLIENTE ===");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    
                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine(); // Limpa o buffer
                    
                    Cliente novoCliente = new Cliente(nome, cpf, idade);
                    listaClientes.add(novoCliente);
                    
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                    
                case 2:
                    // b) Exibir Clientes
                    System.out.println("\n=== LISTA DE CLIENTES ===");
                    
                    if (listaClientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (int i = 0; i < listaClientes.size(); i++) {
                            Cliente cliente = listaClientes.get(i);
                            System.out.println("\nCliente " + (i + 1) + ":");
                            System.out.println("  Nome: " + cliente.getNome());
                            System.out.println("  CPF: " + cliente.getCPF());
                            System.out.println("  Idade: " + cliente.getIdade());
                        }
                    }
                    break;
                    
                case 3:
                    // c) Pesquisar Cliente por CPF
                    System.out.println("\n=== BUSCAR CLIENTE POR CPF ===");
                    System.out.print("Digite o CPF: ");
                    String cpfBusca = sc.nextLine();
                    
                    boolean encontrado = false;
                    for (Cliente cliente : listaClientes) {
                        if (cliente.getCPF().equals(cpfBusca)) {
                            System.out.println("\nCliente encontrado:");
                            System.out.println("  Nome: " + cliente.getNome());
                            System.out.println("  CPF: " + cliente.getCPF());
                            System.out.println("  Idade: " + cliente.getIdade());
                            encontrado = true;
                            break;
                        }
                    }
                    
                    if (!encontrado) {
                        System.out.println("Cliente não está na lista.");
                    }
                    break;
                    
                case 4:
                    // d) Remover Cliente por CPF
                    System.out.println("\n=== REMOVER CLIENTE POR CPF ===");
                    System.out.print("Digite o CPF do cliente a ser removido: ");
                    String cpfRemover = sc.nextLine();
                    
                    boolean removido = false;
                    for (int i = 0; i < listaClientes.size(); i++) {
                        if (listaClientes.get(i).getCPF().equals(cpfRemover)) {
                            listaClientes.remove(i);
                            System.out.println("Cliente removido com sucesso!");
                            removido = true;
                            break;
                        }
                    }
                    
                    if (!removido) {
                        System.out.println("Cliente não encontrado na lista.");
                    } else {
                        // Exibe a lista atualizada
                        System.out.println("\n=== LISTA ATUALIZADA ===");
                        if (listaClientes.isEmpty()) {
                            System.out.println("Nenhum cliente cadastrado.");
                        } else {
                            for (int i = 0; i < listaClientes.size(); i++) {
                                Cliente cliente = listaClientes.get(i);
                                System.out.println("\nCliente " + (i + 1) + ":");
                                System.out.println("  Nome: " + cliente.getNome());
                                System.out.println("  CPF: " + cliente.getCPF());
                                System.out.println("  Idade: " + cliente.getIdade());
                            }
                        }
                    }
                    break;
                    
                case 5:
                    // e) Remover Todos os Clientes
                    System.out.println("\n=== APAGAR LISTA ===");
                    if (listaClientes.isEmpty()) {
                        System.out.println("A lista já está vazia.");
                    } else {
                        listaClientes.clear();
                        System.out.println("Todos os clientes foram removidos da lista.");
                    }
                    break;
                    
                case 6:
                    System.out.println("Saindo do programa...");
                    break;
                    
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
        } while (opcao != 6);
        
        sc.close();
    }
}

class Cliente {
    // Atributos privados (encapsulados)
    private String Nome;
    private String CPF;
    private int Idade;
    
    // Construtor padrão
    public Cliente() {
    }
    
    // Construtor com parâmetros
    public Cliente(String nome, String cpf, int idade) {
        this.Nome = nome;
        this.CPF = cpf;
        this.Idade = idade;
    }
    
    // Getters e Setters (métodos de acesso)
    public String getNome() {
        return Nome;
    }
    
    public void setNome(String nome) {
        this.Nome = nome;
    }
    
    public String getCPF() {
        return CPF;
    }
    
    public void setCPF(String cpf) {
        this.CPF = cpf;
    }
    
    public int getIdade() {
        return Idade;
    }
    
    public void setIdade(int idade) {
        this.Idade = idade;
    }
}

