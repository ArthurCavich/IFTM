package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoDTO {

    private Integer id;
    private LocalDateTime dataPedido;
    private String clienteId;
}
