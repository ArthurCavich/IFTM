package br.edu.iftm.javanio.server;

import br.edu.iftm.javanio.iso.MensagemIso8583;
import br.edu.iftm.javanio.iso.RespostaIso8583;
import br.edu.iftm.javanio.service.ServicoTransacao;

public final class ProcessadorTransacao {

    private ProcessadorTransacao() {
    }

    public static String process(String input, ServicoTransacao service) {
        try {
            MensagemIso8583 request = MensagemIso8583.parse(input);
            return RespostaIso8583.from(service.authorize(request.cardNumber(), request.amount(),
                    request.network())).toString();
        } catch (IllegalArgumentException exception) {
            return "0210|96|000000|" + exception.getMessage() + "|";
        }
    }
}
