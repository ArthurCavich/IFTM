public class ContaPoupancaEstudantil extends ContaPoupanca {
    private Double limiteIsencaoTaxa;

    public ContaPoupancaEstudantil(String titular, Double saldo, Double taxaRendimento, Double limiteIsencaoTaxa) {
        super(titular, saldo, taxaRendimento);
        this.limiteIsencaoTaxa = limiteIsencaoTaxa;
    }

    @Override
    public boolean sacar(Double valor) {
        return super.sacar(valor);
    }

    public String exibeLimiteIsencao() {
        return String.format("Limite de isenção de taxa para estudantes: R$ %.2f", limiteIsencaoTaxa);
    }
}
