package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoProdutoDTO {

    private String nomeProduto;

    private Double valorTotal;

}
