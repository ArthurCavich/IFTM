package parte2.refatorado.dip;

import parte2.refatorado.isp.DispositivoLigavel;
import parte2.refatorado.isp.ImpressoraEpson;
import parte2.refatorado.isp.LampadaPhilips;

public class DipCorrecao {

    public static void main(String[] args) {
        DispositivoLigavel lampada = new LampadaPhilips();
        DispositivoLigavel impressora = new ImpressoraEpson();

        ControleCentral controle = new ControleCentral(lampada, impressora);

        controle.iniciarExpediente();
        controle.encerrarExpediente();
    }
}
