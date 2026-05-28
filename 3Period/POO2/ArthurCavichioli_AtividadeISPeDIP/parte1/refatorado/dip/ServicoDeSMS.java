package dip;

public class ServicoDeSMS implements IEnviadorSMS {

    @Override
    public void sendSMS(String mensagem, String destinatario) {
        System.out.println("Enviando SMS para " + destinatario + " : " + mensagem);
    }
}
