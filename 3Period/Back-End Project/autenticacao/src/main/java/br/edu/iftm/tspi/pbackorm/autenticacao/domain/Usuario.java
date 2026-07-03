package br.edu.iftm.tspi.pbackorm.autenticacao.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    private String login;

    private String senha;

}
