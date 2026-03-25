public class ContaCorrente extends ContaBancaria {
    private Double limiteChequeEspecial;

    public ContaCorrente(String titular, Double saldo, Double limiteChequeEspecial) {
        super(titular, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public boolean sacar(Double valor) {
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
