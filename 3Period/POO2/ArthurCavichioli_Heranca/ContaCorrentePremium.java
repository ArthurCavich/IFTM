public class ContaCorrentePremium extends ContaCorrente {
    private double cashBackPercentual;

    public ContaCorrentePremium(String titular, double saldo, double limiteChequeEspecial, double cashBackPercentual) {
        super(titular, saldo, limiteChequeEspecial);
        this.cashBackPercentual = cashBackPercentual;
    }

    @Override
    public boolean sacar(double valor) {
        boolean saqueRealizado = super.sacar(valor);
        if (saqueRealizado) {
            double cashBack = valor * (cashBackPercentual / 100.0);
            saldo += cashBack;
        }

        return saqueRealizado;
    }

    public String exibeBeneficioPremium() {
        return String.format("Conta Corrente Premium com cashback de %.2f%% em cada saque.", cashBackPercentual);
    }
}
