package atividadeviolacao.ocp.lsp;

public class AtividadeViolacaoOCPLSP {

    public static void main(String[] args) {
        ProcessadorPagamento pg = new ProcessadorPagamento();

        ContaPagadora cc = new ContaCorrente(1000);
        pg.processar(cc, 100, new PagamentoBoleto());
        pg.processar(cc, 100, new PagamentoSaque());
        System.out.println("Saldo da conta corrente após o saque: " + cc.getSaldo());

        Conta cp = new ContaPoupanca(1000);
        pg.processar(cp, 100, new PagamentoBoleto());
        pg.processar(cp, 100, new PagamentoSaque());
        System.out.println("\nSaldo da conta poupanca após o saque: " + cp.getSaldo());
    }
}
