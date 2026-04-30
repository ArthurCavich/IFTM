package br.edu.iftm.tspi.pbackorm.e_commerce.domain;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProdutoID")
    private Integer id;

    @Column(name = "produtonome", nullable = false)
    @NotBlank(message = "Nome do produto é obrigatório")
    private String nome;

    @Column(name = "preco")
    @Positive(message = "O preço deve ser maior do que zero")
    private Double preco;

    @Column(name = "unidadesemestoque")
    @Min(value = 0, message = "Não pode ter estoque negativo")
    private Short estoque;

    @Column(name = "Imagem")
    private String caminhoImagem;

}
