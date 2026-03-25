public class ContaBancaria {
    protected String titular;
    protected Double saldo;

    public ContaBancaria(String titular, Double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(Double valor) {
        saldo = saldo + valor;
    }

    public boolean sacar(Double valor) {
        if (saldo >= valor) {
            return true;
        } else {
            return false;
        }
    }

    public String exibeSaldo() {
        return String.format("Saldo da conta de %s: R$ %.2f", titular, saldo);
    }

    Object getTitular() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}