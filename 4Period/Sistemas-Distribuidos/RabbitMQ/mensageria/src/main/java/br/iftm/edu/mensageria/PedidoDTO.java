package br.iftm.edu.mensageria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 

public class PedidoDTO {
    private Integer id;
    private String email;


}