package br.edu.iftm.javanio.service;

import br.edu.iftm.javanio.model.Cartao;
import br.edu.iftm.javanio.model.Transacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServicoTransacao {

    private final Map<String, Cartao> cards = new ConcurrentHashMap<>();

    public ServicoTransacao() {
        cards.put("401210-3210-1845", new Cartao("401210-3210-1845", "Cliente Demonstração",
                new BigDecimal("1000.00")));
        cards.put("555555-5555-4444", new Cartao("555555-5555-4444", "Maria Silva",
                new BigDecimal("250.00")));
    }

    public Cartao.Authorization authorize(String cardNumber, BigDecimal amount, String network) {
        Cartao card = cards.get(cardNumber);
        if (card == null) {
            return Cartao.Authorization.denied("05", "Cartão inexistente");
        }
        return card.authorize(amount, network);
    }

    public List<CardView> listCards() {
        return cards.values().stream()
                .sorted(Comparator.comparing(Cartao::getNumber))
                .map(card -> new CardView(card.getNumber(), card.getCustomerName(), card.getBalance()))
                .toList();
    }

    public List<TransactionView> listTransactions() {
        return cards.values().stream()
                .flatMap(card -> card.getTransactions().values().stream()
                        .map(transaction -> TransactionView.from(card, transaction)))
                .sorted(Comparator.comparing(TransactionView::nsu).reversed())
                .toList();
    }

    public record CardView(String number, String customerName, BigDecimal balance) {
    }

    public record TransactionView(String cardNumber, String customerName, BigDecimal amount,
                                  String date, String time, String network, String paymentMethod,
                                  String nsu, String responseCode) {
        static TransactionView from(Cartao card, Transacao transaction) {
            return new TransactionView(card.getNumber(), card.getCustomerName(), transaction.amount(),
                    transaction.date().toString(), transaction.time().toString(), transaction.transmissionNetwork(),
                    transaction.paymentMethod(), transaction.nsu(), transaction.responseCode());
        }
    }
}
