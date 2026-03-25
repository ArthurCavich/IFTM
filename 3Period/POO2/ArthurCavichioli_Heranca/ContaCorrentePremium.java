public class ContaCorrentePremium extends ContaCorrente {
    private Double cashBackPercentual;

    public ContaCorrentePremium(String titular, Double saldo, Double limiteChequeEspecial, Double cashBackPercentual) {
        super(titular, saldo, limiteChequeEspecial);
        this.cashBackPercentual = cashBackPercentual;
    }

    @Override
    public boolean sacar(Double valor) {
        boolean saqueRealizado = super.sacar(valor);
        if (saqueRealizado) {
            Double cashBack = valor * (cashBackPercentual / 100.0);
            saldo += cashBack;
        }

        return saqueRealizado;
    }

    public String exibeBeneficioPremium() {
        return String.format("Conta Corrente Premium com cashback de %.2f%% em cada saque.", cashBackPercentual);
    }
}
