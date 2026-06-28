package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRelatorioDTO {

    private Integer numeroPedido;

    private Double valorTotal;

    private List<ItemRelatorioDTO> produtos;

}
