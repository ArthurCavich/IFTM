package br.edu.iftm.projeto_gradle_groovy.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teste")
class TesteController {

    @GetMapping
    String mensagem() {
        return "Projeto Gradle Groovy funcionando!"
    }
}