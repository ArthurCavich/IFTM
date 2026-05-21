package atividadeviolacao.ocp.lsp;

class ProcessadorPagamento {
    public void processar(Conta conta, double valor, EstrategiaPagamento estrategia) {
        estrategia.executar(conta, valor);
    }
}
