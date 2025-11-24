import java.util.ArrayList;
import java.util.Scanner;

public class exe06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contato> listaContatos = new ArrayList<>();
        
        int opcao;
        
        do {
            // Mostra o menu
            System.out.println("\n=== MENU ===");
            System.out.println("1. Inserir Contato");
            System.out.println("2. Listar Contatos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); // Limpa o buffer do teclado
            
            switch (opcao) {
                case 1:
                    // Inserir novo contato
                    System.out.println("\n=== INSERIR CONTATO ===");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    
                    System.out.print("Celular: ");
                    String celular = sc.nextLine();
                    
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    
                    // Cria um novo objeto Contato e adiciona à lista
                    Contato novoContato = new Contato(nome, telefone, celular, email);
                    listaContatos.add(novoContato);
                    
                    System.out.println("Contato cadastrado com sucesso!");
                    break;
                    
                case 2:
                    // Listar todos os contatos
                    System.out.println("\n=== LISTA DE CONTATOS ===");
                    
                    if (listaContatos.isEmpty()) {
                        System.out.println("Nenhum contato cadastrado.");
                    } else {
                        for (int i = 0; i < listaContatos.size(); i++) {
                            Contato contato = listaContatos.get(i);
                            System.out.println("\nContato " + (i + 1) + ":");
                            System.out.println("  Nome: " + contato.getNome());
                            System.out.println("  Telefone: " + contato.getTelefone());
                            System.out.println("  Celular: " + contato.getCelular());
                            System.out.println("  Email: " + contato.getEmail());
                        }
                    }
                    break;
                    
                case 3:
                    System.out.println("Saindo do programa...");
                    break;
                    
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
        } while (opcao != 3);
        
        sc.close();
    }
}

class Contato {
    // Atributos privados (encapsulados)
    private String Nome;
    private String Telefone;
    private String Celular;
    private String Email;
    
    // Construtor padrão
    public Contato() {
    }
    
    // Construtor com parâmetros
    public Contato(String nome, String telefone, String celular, String email) {
        this.Nome = nome;
        this.Telefone = telefone;
        this.Celular = celular;
        this.Email = email;
    }
    
    // Getters e Setters (métodos de acesso)
    public String getNome() {
        return Nome;
    }
    
    public void setNome(String nome) {
        this.Nome = nome;
    }
    
    public String getTelefone() {
        return Telefone;
    }
    
    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }
    
    public String getCelular() {
        return Celular;
    }
    
    public void setCelular(String celular) {
        this.Celular = celular;
    }
    
    public String getEmail() {
        return Email;
    }
    
    public void setEmail(String email) {
        this.Email = email;
    }
}

