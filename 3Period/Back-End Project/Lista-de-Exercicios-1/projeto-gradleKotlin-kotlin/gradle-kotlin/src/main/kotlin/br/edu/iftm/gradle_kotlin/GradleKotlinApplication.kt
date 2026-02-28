package br.edu.iftm.gradle_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GradleKotlinApplication

fun main(args: Array<String>) {
	runApplication<GradleKotlinApplication>(*args)
}
