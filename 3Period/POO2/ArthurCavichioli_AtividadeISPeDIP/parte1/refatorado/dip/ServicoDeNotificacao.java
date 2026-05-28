package dip;

public class ServicoDeNotificacao {
    private final IEnviadorEmail email;
    private final IEnviadorSMS sms;

    public ServicoDeNotificacao(IEnviadorEmail email, IEnviadorSMS sms) {
        this.email = email;
        this.sms = sms;
    }

    public void notificaPorEmail(String mensagem, String destinatario) {
        email.sendEmail(mensagem, destinatario);
    }

    public void notificaPorSMS(String mensagem, String destinatario) {
        sms.sendSMS(mensagem, destinatario);
    }
}
