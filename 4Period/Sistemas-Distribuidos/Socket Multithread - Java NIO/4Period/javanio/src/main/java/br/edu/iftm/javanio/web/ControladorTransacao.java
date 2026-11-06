package br.edu.iftm.javanio.web;

import br.edu.iftm.javanio.client.ClienteTransacao;
import br.edu.iftm.javanio.service.ServicoTransacao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorTransacao {

    private final ServicoTransacao service;
    private final int multithreadPort;
    private final int nioPort;

    public ControladorTransacao(ServicoTransacao service,
                                 @Value("${transaction.server.multithread-port:9091}") int multithreadPort,
                                 @Value("${transaction.server.nio-port:9092}") int nioPort) {
        this.service = service;
        this.multithreadPort = multithreadPort;
        this.nioPort = nioPort;
    }

    @GetMapping("/cards")
    public List<ServicoTransacao.CardView> cards() {
        return service.listCards();
    }

    @GetMapping("/transactions")
    public List<ServicoTransacao.TransactionView> transactions() {
        return service.listTransactions();
    }

    @PostMapping("/transactions")
    public TransactionResponse authorize(@RequestBody TransactionRequest request) {
        int port = "nio".equalsIgnoreCase(request.server()) ? nioPort : multithreadPort;
        try {
            String response = ClienteTransacao.send("localhost", port,
                    "0200|" + request.cardNumber() + "|" + request.amount() + "|EFT");
            String[] fields = response.split("\\|", -1);
            return new TransactionResponse(fields[1], fields[2], fields[3],
                    fields.length > 4 ? fields[4] : "",
                    "nio".equalsIgnoreCase(request.server()) ? "Java NIO" : "Socket Multithread");
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Servidor de transações indisponível", exception);
        }
    }

    public record TransactionRequest(String cardNumber, BigDecimal amount, String server) {
    }

    public record TransactionResponse(String responseCode, String nsu, String message,
                                      String balance, String server) {
    }
}
