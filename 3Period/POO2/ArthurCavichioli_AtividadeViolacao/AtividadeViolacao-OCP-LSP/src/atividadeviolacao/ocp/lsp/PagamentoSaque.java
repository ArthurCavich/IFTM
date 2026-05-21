package atividadeviolacao.ocp.lsp;

class PagamentoSaque implements EstrategiaPagamento {
    @Override
    public void executar(Conta conta, double valor) {
        conta.sacar(valor);
    }
}
