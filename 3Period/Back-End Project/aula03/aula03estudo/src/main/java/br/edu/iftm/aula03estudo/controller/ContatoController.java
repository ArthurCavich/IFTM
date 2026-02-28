package br.edu.iftm.aula03estudo.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.aula03estudo.domain.Contato;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController

public class ContatoController {

    @GetMapping("/contatos")
    
    public List<Contato> getContatos(){
        Contato contato1 = new Contato(1,"Jonas");
        Contato contato2 = new Contato(2, "Tadeu");
        List<Contato> contatos = new ArrayList<>();
        contatos.add(contato1);
        contatos.add(contato2);
        return contatos;

    }
    
}
