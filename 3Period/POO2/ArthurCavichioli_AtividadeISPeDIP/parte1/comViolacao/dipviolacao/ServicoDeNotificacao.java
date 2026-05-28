package parte1.comViolacao.dipviolacao;

public class ServicoDeNotificacao {
    private final ServicoDeEmail email;
    private final ServicoDeSMS sms;

    public ServicoDeNotificacao() {
        /*
         * VIOLAÇÃO DO DIP (Dependency Inversion Principle):
         * Este módulo de alto nível depende diretamente de classes concretas
         * (ServicoDeEmail e ServicoDeSMS) em vez de abstrações (interfaces).
         * Isso acopla o código e dificulta testes e substituição de implementações.
         *
         * CORREÇÃO: depender de interfaces e receber as dependências por injeção
         * (ver pacote dip).
         */
        this.email = new ServicoDeEmail();
        this.sms = new ServicoDeSMS();
    }

    public void notificaPorEmail(String mensagem, String destinatario) {
        email.sendEmail(mensagem, destinatario);
    }

    public void notificaPorSMS(String mensagem, String destinatario) {
        sms.sendSMS(mensagem, destinatario);
    }
}
