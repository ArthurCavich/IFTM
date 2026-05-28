package parte2.refatorado.isp;

public class IspCorrecao {

    public static void main(String[] args) {
        DispositivoLigavel lampada = new LampadaPhilips();
        Imprimivel impressora = new ImpressoraEpson();

        lampada.ligar();
        lampada.desligar();

        ((DispositivoLigavel) impressora).ligar();
        impressora.imprimirDocumento("Relatório mensal");
        ((DispositivoLigavel) impressora).desligar();
    }
}
