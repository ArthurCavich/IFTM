package atividadeviolacao.ocp.lsp;

class ContaCorrente implements ContaPagadora {
    private double saldo;

    public ContaCorrente(double saldo) {
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

    @Override
    public void pagarBoleto(double valor) {
        this.saldo = this.saldo - valor;
        System.out.println("Boleto pago com Conta Corrente. Saldo restante: " + this.saldo);
    }
}
