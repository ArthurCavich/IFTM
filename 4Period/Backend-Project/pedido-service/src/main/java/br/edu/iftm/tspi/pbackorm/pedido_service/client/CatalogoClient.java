package br.edu.iftm.tspi.pbackorm.pedido_service.client;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogoClient {

    private final RestClient restClient;

    public CatalogoClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    public void baixarEstoque(Integer produtoId, Short quantidade) {
        Map<String, Short> body = Map.of("quantidade", quantidade);

        restClient.put()
            .uri("/produtos/{id}/baixar-estoque", produtoId)
            .body(body)
            .retrieve()
            .toBodilessEntity();
    }       
    
}
