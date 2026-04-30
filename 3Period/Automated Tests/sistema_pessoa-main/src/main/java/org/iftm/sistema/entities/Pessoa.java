package org.iftm.sistema.entities;

public class Pessoa {
    private String nome;
    private String sobrenome;
    
    public Pessoa(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String retornarNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    public String retornarIniciais() {
        return (this.nome.charAt(0) +""+ this.sobrenome.charAt(0)).toUpperCase();
    }

    
}
