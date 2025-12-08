// Classe que representa um Aluno
public class Aluno {
    // Atributo que armazena a matrícula do aluno
    private String matricula;
    // Atributo que armazena o nome do aluno
    private String nome;
    // Atributo que armazena o CPF do aluno
    private String cpf;

    // Construtor da classe Aluno que recebe matrícula, nome e CPF
    public Aluno(String matricula, String nome, String cpf) {
        this.matricula = matricula; // Inicializa a matrícula
        this.nome = nome; // Inicializa o nome
        this.cpf = cpf; // Inicializa o CPF
    }

    // Método getter que retorna a matrícula do aluno
    public String getMatricula() {
        return matricula; // Retorna a matrícula
    }

    // Método setter que define a matrícula do aluno
    public void setMatricula(String matricula) {
        this.matricula = matricula; // Define a matrícula
    }

    // Método getter que retorna o nome do aluno
    public String getNome() {
        return nome; // Retorna o nome
    }

    // Método setter que define o nome do aluno
    public void setNome(String nome) {
        this.nome = nome; // Define o nome
    }

    // Método getter que retorna o CPF do aluno
    public String getCpf() {
        return cpf; // Retorna o CPF
    }

    // Método setter que define o CPF do aluno
    public void setCpf(String cpf) {
        this.cpf = cpf; // Define o CPF
    }

    // Método toString que retorna uma representação em String do objeto Aluno
    @Override
    public String toString() {
        // Retorna uma string formatada com os dados do aluno
        return "Matrícula: " + matricula
                + " | Nome: " + nome
                + " | CPF: " + cpf;
    }
}
