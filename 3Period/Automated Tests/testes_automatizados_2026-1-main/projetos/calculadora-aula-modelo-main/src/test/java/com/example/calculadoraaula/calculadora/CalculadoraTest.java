package com.example.calculadoraaula.calculadora;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    @Test
    public void testeSomaDoisInteiros(){
        // Arrange
        Calculadora calculadoraTestada = new Calculadora();
        int numeroSomado1 = 2;
        int numeroSomado2 = 3;
        int resultadoEsperado = 5;
        // Act
        int resultadoObtido = calculadoraTestada.somar(numeroSomado1, numeroSomado2);

        // Assert
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void testeSubtraiDoisInteiros(){
        // Arrange
        Calculadora calculadoraTestada = new Calculadora();
        int numeroSubtraido1 = 3;
        int numeroSubtraido2 = 3;
        int resultadoEsperado = 0;
        // Act
        int resultadoObtido = calculadoraTestada.subtrair(numeroSubtraido1, numeroSubtraido2);

        // Assert
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    
}
