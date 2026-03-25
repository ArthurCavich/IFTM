package br.edu.iftm.aula03estudo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ErroDTO {
    
    private String mensagem;
    private LocalDateTime data;
}
