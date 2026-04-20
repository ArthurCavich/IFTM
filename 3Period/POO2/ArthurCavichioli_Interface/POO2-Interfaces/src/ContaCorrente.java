public class ContaCorrente implements InterfaceConta {
    private double saldo, taxa;

    public ContaCorrente(double taxa) {
        this.taxa = taxa;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    @Override
    public void sacar(double valor) {
        double valorComTaxa = valor + taxa;
        if (valor > 0 && valorComTaxa <= saldo) {
            saldo -= valorComTaxa;
        }
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    public double getTaxa() {
        return taxa;
    }
}
