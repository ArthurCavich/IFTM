package br.edu.iftm.tspi.pbackorm.pedido_service.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.edu.iftm.tspi.pbackorm.pedido_service.client.CatalogoClient;
import br.edu.iftm.tspi.pbackorm.pedido_service.domain.DetalhePedido;
import br.edu.iftm.tspi.pbackorm.pedido_service.domain.Pedido;
import br.edu.iftm.tspi.pbackorm.pedido_service.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final CatalogoClient catalogoClient;

    @Transactional
    public Pedido salvar(Pedido pedidoNovo) {
        pedidoNovo.setDataPedido(LocalDateTime.now());

        for (DetalhePedido detalhe : pedidoNovo.getDetalhesPedido()) {
            Integer produtoID = detalhe.getId().getProdutoId();            
            detalhe.setPedido(pedidoNovo);
            catalogoClient.baixarEstoque(produtoID,detalhe.getQuantidade());
        }
        return pedidoRepository.save(pedidoNovo);
    }

}
