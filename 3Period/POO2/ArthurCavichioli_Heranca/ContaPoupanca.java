public class ContaPoupanca extends ContaBancaria {
	private Double taxaRendimento;

	public ContaPoupanca(String titular, Double saldo, Double taxaRendimento) {
		super(titular, saldo);
		this.taxaRendimento = taxaRendimento;
	}

	public void aplicarRendimento() {
		saldo += saldo * taxaRendimento / 100.0;
	}

	@Override
	public String exibeSaldo() {
		return String.format("Saldo da conta poupança de %s: R$ %.2f", titular, saldo);
	}
}
