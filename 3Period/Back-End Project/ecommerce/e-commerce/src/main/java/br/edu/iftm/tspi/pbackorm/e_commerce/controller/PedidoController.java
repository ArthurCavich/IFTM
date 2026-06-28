package br.edu.iftm.tspi.pbackorm.e_commerce.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Pedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ItemRelatorioDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.PedidoRelatorioDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.PedidoRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoRepository repository;

    @GetMapping
    public ResponseEntity<List<PedidoRelatorioDTO>> porClienteEPeriodo(
            @RequestParam String idCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {

        List<Pedido> pedidos = repository.buscarPorClienteEPeriodo(idCliente, inicio, fim);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PedidoRelatorioDTO> relatorio = pedidos.stream().map(ped -> {
            List<ItemRelatorioDTO> itens = ped.getDetalhesPedido().stream()
                    .map(d -> new ItemRelatorioDTO(
                            d.getProduto().getNome(),
                            d.getQuantidade(),
                            d.getPrecoVenda() * d.getQuantidade()))
                    .toList();

            Double valorTotal = itens.stream()
                    .mapToDouble(ItemRelatorioDTO::getSubtotal)
                    .sum();

            return new PedidoRelatorioDTO(ped.getId(), valorTotal, itens);
        }).toList();

        return ResponseEntity.ok(relatorio);
    }

}
