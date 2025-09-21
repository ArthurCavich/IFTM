public class JogadorDeFutebol {
    
    private String nome;
    public String posicao, nacionalidade;
    public int nascimento;
    public double peso, altura;


    public JogadorDeFutebol() {
    }

    public JogadorDeFutebol(String nome, String nacionalidade, int nascimento, String posicao, double peso, double altura) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.nascimento = nascimento;
        this.posicao = posicao;
        this.peso = peso;
        this.altura = altura;
    }

    public int calculaIdade(int nascimento){
        int anoAtual = 2025;

        int idade = anoAtual - nascimento;

        return idade;
    }

    public String exibeDados() {
     return "Nome: " + nome + "\n" +
         "Ano de Nascimento: " + nascimento + "\n" +
         "Altura: " + String.format("%.2f", altura).replace('.', ',') + " m\n" +
         "Peso: " + String.format("%.1f", peso) + " kg\n" +
         "Nacionalidade: " + nacionalidade + "\n" +
         "Posição: " + posicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public void setNascimento(int nascimento) {
        this.nascimento = nascimento;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

}
