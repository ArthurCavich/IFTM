public class ContaPoupancaEstudantil extends ContaPoupanca {
    private double limiteIsencaoTaxa;

    public ContaPoupancaEstudantil(String titular, double saldo, double taxaRendimento, double limiteIsencaoTaxa) {
        super(titular, saldo, taxaRendimento);
        this.limiteIsencaoTaxa = limiteIsencaoTaxa;
    }

    @Override
    public boolean sacar(double valor) {
        return super.sacar(valor);
    }

    public String exibeLimiteIsencao() {
        return String.format("Limite de isenção de taxa para estudantes: R$ %.2f", limiteIsencaoTaxa);
    }
}
