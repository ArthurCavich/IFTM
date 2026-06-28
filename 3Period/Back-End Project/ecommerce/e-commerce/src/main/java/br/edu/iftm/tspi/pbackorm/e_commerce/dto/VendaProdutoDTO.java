package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaProdutoDTO {

    private Integer numeroPedido;

    private Short quantidade;

    private Double valorTotal;

    private Double valorDesconto;

}
