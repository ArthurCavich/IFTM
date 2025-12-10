
public class Movimento {

    private String data, historico;
    private Conta conta;
    private int operacao;
    private float valor, saldoAnterior;

    public final static int sacar = 0;

    public final static int depositar = 1;

    public Movimento(String data, Conta conta, String historico, float valor, int operacao) {
        this.data = data;
        this.conta = conta;
        this.historico = historico;
        this.valor = valor;
        this.operacao = operacao;
    }

    public boolean movimentar() {
        this.saldoAnterior = conta.getSaldo();
        return conta.movimentar(valor, operacao);
    }

    public boolean movimentar(Especial taxa) {
        this.saldoAnterior = taxa.getLimite();
        return conta.movimentar(valor, operacao, taxa);

    }
}
