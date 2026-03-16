public class ContaCorrente extends ContaBancaria {
    private double limiteChequeEspecial;

    public ContaCorrente(String titular, double saldo, double limiteChequeEspecial) {
        super(titular, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public boolean sacar(double valor) {
        if (saldo + limiteChequeEspecial >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public String exibeLimiteChequeEspecial() {
        return String.format("Limite de cheque especial: R$ %.2f", limiteChequeEspecial);
    }

    protected double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }
}
