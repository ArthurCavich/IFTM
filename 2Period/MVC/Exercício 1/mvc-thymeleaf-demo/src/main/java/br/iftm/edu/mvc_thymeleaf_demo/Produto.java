package br.iftm.edu.mvc_thymeleaf_demo;

import lombok.Data;

@Data

public class Produto {
	String nome;
	Double preco;

	public Produto(String nome, Double preco){
		super();
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {return nome;}

	public void setNome(String nome) {this.nome = nome;}

	public Double getPreco() {return preco;}

	public void setPreco(Double preco) {this.preco = preco;}
}
