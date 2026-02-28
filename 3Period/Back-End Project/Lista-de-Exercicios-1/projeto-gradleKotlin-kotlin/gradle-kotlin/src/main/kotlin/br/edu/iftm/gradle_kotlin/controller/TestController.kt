package br.edu.iftm.projeto_gradle_kotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teste")
class TesteController {

    @GetMapping
    fun mensagem(): String {
        return "Projeto Gradle Kotlin funcionando!"
    }
}