package parte2.comViolacao.dipviolacao;

public class ControleCentral {
    // Violação do DIP: Dependendo diretamente de classes concretas (baixo nível)
    // em vez de depender de interfaces (abstrações)
    private LampadaPhilips lampada;
    private ImpressoraEpson impressora;

    public ControleCentral() {
        /*
         * VIOLAÇÃO DO DIP (Dependency Inversion Principle):
         * Alto acoplamento: o controle cria as próprias instâncias rigidamente,
         * dependendo de classes concretas em vez de abstrações.
         *
         * CORREÇÃO: depender de interfaces e receber as dependências por injeção
         * (ver pacote parte2.refatorado.dip).
         */
        this.lampada = new LampadaPhilips();
        this.impressora = new ImpressoraEpson();
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
