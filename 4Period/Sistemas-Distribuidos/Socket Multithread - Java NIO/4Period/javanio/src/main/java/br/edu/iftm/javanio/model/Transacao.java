package br.edu.iftm.javanio.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record Transacao(
        BigDecimal amount,
        LocalDate date,
        LocalTime time,
        String transmissionNetwork,
        String paymentMethod,
        String nsu,
        String responseCode) {
}
