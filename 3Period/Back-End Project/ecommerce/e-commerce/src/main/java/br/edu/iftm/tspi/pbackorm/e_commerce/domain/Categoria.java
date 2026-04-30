package br.edu.iftm.tspi.pbackorm.e_commerce.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoriaID")
    private Integer id;

    @Column(name = "categoria", nullable = false)
    @NotBlank(message = "O campo nome da categoria é obrigatório")
    private String nome;

    @Column(name = "descricao")
    private String descricao;
}
