package br.edu.iftm.aula03estudo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera: getters, setters, toString, equals, hashCode
@AllArgsConstructor // Gera: construtor com todos os atributos
@NoArgsConstructor // Gera: construtor vazio (necessário para JPA/BD)
public class Contato {

    private Integer codigo;
    private String nome;
}
