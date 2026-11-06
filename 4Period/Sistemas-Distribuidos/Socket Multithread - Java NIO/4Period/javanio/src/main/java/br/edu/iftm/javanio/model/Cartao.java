package br.edu.iftm.javanio.model;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Cartao {

    private final String number;
    private final String customerName;
    private BigDecimal balance;
    private final ConcurrentMap<String, Transacao> transactions = new ConcurrentHashMap<>();

    public Cartao(String number, String customerName, BigDecimal balance) {
        this.number = number;
        this.customerName = customerName;
        this.balance = balance;
    }

    public synchronized Authorization authorize(BigDecimal amount, String transmissionNetwork) {
        if (amount.signum() <= 0) {
            return Authorization.denied("51", "Valor da transação deve ser positivo");
        }
        if (balance.compareTo(amount) < 0) {
            return Authorization.denied("51", "Saldo insuficiente");
        }

        balance = balance.subtract(amount);
        String nsu = String.format("%06d", transactions.size() + 1);
        Transacao transaction = new Transacao(amount, java.time.LocalDate.now(),
                java.time.LocalTime.now().withNano(0), transmissionNetwork, "CREDITO", nsu, "00");
        transactions.put(nsu, transaction);
        return new Authorization("00", nsu, balance, "Transação aprovada");
    }

    public String getNumber() {
        return number;
    }

    public String getCustomerName() {
        return customerName;
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }

    public ConcurrentMap<String, Transacao> getTransactions() {
        return transactions;
    }

    public record Authorization(String responseCode, String nsu, BigDecimal balance, String message) {
        public static Authorization denied(String code, String message) {
            return new Authorization(code, "000000", null, message);
        }
    }
}
