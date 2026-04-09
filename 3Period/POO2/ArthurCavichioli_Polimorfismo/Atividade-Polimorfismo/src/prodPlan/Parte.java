package prodPlan;

public abstract class Parte {
    protected int cod;
    protected String nome;
    protected String descricao;
    protected float valor;

    public Parte(int cod, String nome, String descricao, float valor) {
        this.cod = cod;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public abstract float calculaValor();

    public abstract String toString();
}
