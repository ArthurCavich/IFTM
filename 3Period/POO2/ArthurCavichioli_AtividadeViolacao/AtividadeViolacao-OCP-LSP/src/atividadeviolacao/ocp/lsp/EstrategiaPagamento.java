package atividadeviolacao.ocp.lsp;

interface EstrategiaPagamento {
    void executar(Conta conta, double valor);
}
