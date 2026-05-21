package atividadeviolacao.ocp.lsp;

class PagamentoBoleto implements EstrategiaPagamento {
    @Override
    public void executar(Conta conta, double valor) {
        if (conta instanceof ContaPagadora) {
            ((ContaPagadora) conta).pagarBoleto(valor);
        } else {
            System.out.println("Esta conta não suporta pagamento de boleto.");
        }
    }
}
