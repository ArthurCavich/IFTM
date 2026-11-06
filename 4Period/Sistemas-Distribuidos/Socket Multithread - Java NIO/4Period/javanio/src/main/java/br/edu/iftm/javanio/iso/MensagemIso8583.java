package br.edu.iftm.javanio.iso;

import java.math.BigDecimal;

public record MensagemIso8583(String messageType, String cardNumber, BigDecimal amount, String network) {

    public static MensagemIso8583 parse(String value) {
        String[] parts = value.trim().split("\\|", -1);
        if (parts.length < 3 || !"0200".equals(parts[0])) {
            throw new IllegalArgumentException("Mensagem ISO 8583 inválida. Use 0200|cartao|valor|rede");
        }
        try {
            return new MensagemIso8583(parts[0], parts[1], new BigDecimal(parts[2]),
                    parts.length > 3 && !parts[3].isBlank() ? parts[3] : "EFT");
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Valor da transação inválido", exception);
        }
    }

    public static MensagemIso8583 request(String cardNumber, BigDecimal amount, String network) {
        return new MensagemIso8583("0200", cardNumber, amount, network);
    }

    @Override
    public String toString() {
        return messageType + "|" + cardNumber + "|" + amount.toPlainString() + "|" + network;
    }
}
