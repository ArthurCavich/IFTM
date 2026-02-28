package br.edu.iftm.projeto_war

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class ProjetoWarApplication : SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(ProjetoWarApplication::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<ProjetoWarApplication>(*args)
}