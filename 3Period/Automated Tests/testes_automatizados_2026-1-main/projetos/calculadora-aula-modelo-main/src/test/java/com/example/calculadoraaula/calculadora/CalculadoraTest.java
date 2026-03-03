package com.example.calculadoraaula.calculadora;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    private static Calculadora calculadoraTestada;

    @BeforeAll
    private static void setUpPrincipal() {
        System.out.println("Começou o teste!");
        calculadoraTestada = new Calculadora(); //aqui ele instância um objeto antes de cada teste
    }

    @BeforeEach //executa teste antes de cada
    private void setUp() {
        System.out.println("Inicio de um teste!");

    }

    @AfterEach //retorna após cada teste
    private void fim() {
        System.out.println("Término de um teste!!");
    }

    @Test
    public void testeSomaDoisInteiros() {
        // Arrange
        int numeroSomado1 = 2;
        int numeroSomado2 = 3;
        int resultadoEsperado = 5;
        // Act
        int resultadoObtido = calculadoraTestada.somar(numeroSomado1, numeroSomado2);

        // Assert
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testeSubtraiDoisInteiros() {
        // Arrange
        // Calculadora calculadoraTestada = new Calculadora(); retirei pq utilizo a anotação beforeEach ou beforeAll
        int numeroSubtraido1 = 3;
        int numeroSubtraido2 = 3;
        int resultadoEsperado = 0;
        // Act
        int resultadoObtido = calculadoraTestada.subtrair(numeroSubtraido1, numeroSubtraido2);

        // Assert
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

}
