package br.edu.iftm.contatos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contato {

    private Integer codigo;
    private String nome;
}
