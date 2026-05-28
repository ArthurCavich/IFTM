package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemPedidoDTO {

    @NotNull(message = "O ID do produto é obrigatório")
    private Integer produtoId;

    private Double precoVenda;
    private Short quantidade;
    private Double desconto;
}
