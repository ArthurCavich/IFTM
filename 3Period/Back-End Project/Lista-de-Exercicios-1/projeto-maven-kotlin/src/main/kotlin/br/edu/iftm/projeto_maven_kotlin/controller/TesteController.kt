package br.edu.iftm.projeto_maven_kotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teste")
class TesteController {

    @GetMapping
    fun mensagem(): String {
        return "Projeto Maven Kotlin funcionando!"
    }
}