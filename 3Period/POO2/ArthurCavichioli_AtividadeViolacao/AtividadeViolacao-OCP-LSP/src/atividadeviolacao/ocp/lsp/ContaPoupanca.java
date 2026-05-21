package atividadeviolacao.ocp.lsp;

class ContaPoupanca implements Conta {
    private double saldo;

    public ContaPoupanca(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public void sacar(double valor) {
        this.saldo = this.saldo - valor;
    }
}
