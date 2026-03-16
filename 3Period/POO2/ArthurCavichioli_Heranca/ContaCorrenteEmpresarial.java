public class ContaCorrenteEmpresarial extends ContaCorrente {
    private double taxaJurosEmprestimo;

    public ContaCorrenteEmpresarial(String titular, double saldo, double limiteChequeEspecial, double taxaJurosEmprestimo) {
        super(titular, saldo, limiteChequeEspecial);
        this.taxaJurosEmprestimo = taxaJurosEmprestimo;
    }

    public boolean solicitaEmprestimo(double valor) {
        if (valor <= saldo + getLimiteChequeEspecial()) {
            saldo += valor;
            return true;
        }
        return false;
    }

    @Override
    public String exibeSaldo() {
        return String.format("Saldo da conta empresarial de %s: R$ %.2f", titular, saldo);
    }

    public String exibeTaxaJurosEmprestimo() {
        return String.format("Taxa de juros do empréstimo: %.2f%%", taxaJurosEmprestimo);
    }
}
