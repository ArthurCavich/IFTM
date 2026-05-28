package dip;

public class DipCorrecao {

    public static void main(String[] args) {
        IEnviadorEmail email = new ServicoDeEmail();
        IEnviadorSMS sms = new ServicoDeSMS();

        ServicoDeNotificacao notificacao = new ServicoDeNotificacao(email, sms);

        notificacao.notificaPorEmail("Sua fatura chegou!", "cliente@email.com");
        notificacao.notificaPorSMS("Seu código é 1234", "11999999999");
    }
}
