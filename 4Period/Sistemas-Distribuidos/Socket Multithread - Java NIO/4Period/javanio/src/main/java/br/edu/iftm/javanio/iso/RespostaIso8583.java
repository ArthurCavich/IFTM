package br.edu.iftm.javanio.iso;

import br.edu.iftm.javanio.model.Cartao;

public record RespostaIso8583(String messageType, String responseCode, String nsu, String message,
                              String balance) {
    public static RespostaIso8583 from(Cartao.Authorization authorization) {
        return new RespostaIso8583("0210", authorization.responseCode(), authorization.nsu(),
                authorization.message(), authorization.balance() == null
                ? "" : authorization.balance().toPlainString());
    }

    @Override
    public String toString() {
        return messageType + "|" + responseCode + "|" + nsu + "|" + message + "|" + balance;
    }
}
