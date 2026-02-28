package br.edu.iftm.aula03estudo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //get e set
@AllArgsConstructor //gera os construtores

public class Contato { // aqui são informações para alimentar o banco de dados
    private Integer codigo;

    private String nome;
}
