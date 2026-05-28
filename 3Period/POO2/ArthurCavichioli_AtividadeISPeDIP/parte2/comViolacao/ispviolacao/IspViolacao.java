package parte2.comViolacao.ispviolacao;

public class IspViolacao {

    public static void main(String[] args) {
        DispositivoSuperInteligente impressora = new ImpressoraEpson();
        DispositivoSuperInteligente lampada = new LampadaPhilips();

        impressora.ligar();
        impressora.imprimirDocumento("Relatório mensal");

        lampada.ligar();
        lampada.desligar();
    }
}
