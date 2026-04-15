package br.edu.iftm.pbackorm.contatos.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder //forma mais legível de criação de objetos e inserir atributos no objeto
public class ErroDTO {

    private String mensagem;

    private LocalDateTime dataHora;

    private List<String> erros;

}
