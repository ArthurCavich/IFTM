public class GeradorExtratos {

    public String exibeExtrato(InterfaceConta conta) {
        String tipoConta = conta.getClass().getSimpleName();
        return "Extrato - " + tipoConta + "\nSaldo atual: R$ " + String.format("%.2f", conta.getSaldo());
    }
}
