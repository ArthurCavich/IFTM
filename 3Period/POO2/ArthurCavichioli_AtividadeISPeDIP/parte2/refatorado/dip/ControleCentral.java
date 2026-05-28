package parte2.refatorado.dip;

import parte2.refatorado.isp.DispositivoLigavel;

public class ControleCentral {
    private final DispositivoLigavel lampada;
    private final DispositivoLigavel impressora;

    public ControleCentral(DispositivoLigavel lampada, DispositivoLigavel impressora) {
        this.lampada = lampada;
        this.impressora = impressora;
    }

    public void iniciarExpediente() {
        lampada.ligar();
        impressora.ligar();
    }

    public void encerrarExpediente() {
        lampada.desligar();
        impressora.desligar();
    }
}
