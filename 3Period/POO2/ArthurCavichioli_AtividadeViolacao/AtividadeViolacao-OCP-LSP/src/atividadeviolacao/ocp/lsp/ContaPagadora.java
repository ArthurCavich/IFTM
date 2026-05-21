package atividadeviolacao.ocp.lsp;

interface ContaPagadora extends Conta {
    void pagarBoleto(double valor);
}
