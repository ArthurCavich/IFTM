package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRelatorioDTO {

    private String nomeProduto;

    private Short quantidade;

    private Double subtotal;

}
