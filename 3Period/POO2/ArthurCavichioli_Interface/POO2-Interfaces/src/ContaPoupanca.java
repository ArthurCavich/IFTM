public class ContaPoupanca implements InterfaceConta {
    private double saldo;

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        }
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
}
