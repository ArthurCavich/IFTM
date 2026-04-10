package br.edu.iftm.lista2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidadeEstoque;
    private String codigoBarras;

    // Construtores
    public Produto() {
    }

    public Produto(Long id, String nome, String descricao,
            Double preco, Integer quantidadeEstoque, String codigoBarras) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.codigoBarras = codigoBarras;
    }

}