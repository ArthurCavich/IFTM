package br.edu.iftm.calculadora.atividade2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    // inicializar o objeto a ser testado
    private static Calculadora calculadoraTest;

    // 1. Construtor Vazio:
    @Test
    void memoria() {
        // Arrange
        calculadoraTest = new Calculadora();

        // Act
        int resultado = calculadoraTest.getMemoria();

        // Assert
        assertEquals(0, resultado);

    }

    // 2. Construtor com Parâmetro (3):
    @Test
    void memoria2() {
        // Arrange
        calculadoraTest = new Calculadora(3);

        // Act
        int resultado = calculadoraTest.getMemoria();

        // Assert
        assertEquals(3, resultado);
    }

    // 2. Construtor com Parâmetro (-3):
    @Test
    void memoria3() {
        // Arrange
        calculadoraTest = new Calculadora(-3);

        // Act
        int resultado = calculadoraTest.getMemoria();

        // Assert
        assertEquals(-3, resultado);
    }

    // 3. Somar: valor positivo
    @Test
    void soma() {
        // Arrange
        calculadoraTest = new Calculadora(3);

        // Act
        calculadoraTest.somar(2);

        // Assert
        assertEquals(5, calculadoraTest.getMemoria());
    }

    // 3.1 Somar: valor negativo
    @Test
    void somaNegativo() {
        // Arrange
        calculadoraTest = new Calculadora(3);
        // Act
        calculadoraTest.somar(-2);
        // Assert
        assertEquals(1, calculadoraTest.getMemoria());
    }

    // 4. Subtrair: valor positivo
    @Test
    void subtracao() {
        // Arrange
        calculadoraTest = new Calculadora(3);
        // Act
        calculadoraTest.subtrair(2);
        // Assert
        assertEquals(1, calculadoraTest.getMemoria());
    }

    // 4.1 Subtrair: valor negativo
    @Test
    void subtracaoNegativo() {
        // Arrange
        calculadoraTest = new Calculadora(3);
        // Act
        calculadoraTest.subtrair(-2);
        // Assert
        assertEquals(5, calculadoraTest.getMemoria());
    }

    // 5. Multiplicar: valor positivo
    @Test
    void multi() {
        // Arrange
        calculadoraTest = new Calculadora(3);
        // Act
        calculadoraTest.multiplicar(2);
        // Assert
        assertEquals(6, calculadoraTest.getMemoria());
    }

    // 5.1 Multiplicar: valor negativo
    @Test
    void multiNegativo() {
        // Arrange
        calculadoraTest = new Calculadora(3);
        // Act
        calculadoraTest.multiplicar(-2);
        // Assert
        assertEquals(-6, calculadoraTest.getMemoria());
    }

    // 6. Divisão: valor positivo
    @Test
    void div() throws Exception {
        // Arrange
        calculadoraTest = new Calculadora(3);
        // Act
        calculadoraTest.dividir(3);
        // Assert
        assertEquals(0, calculadoraTest.getMemoria());
    }

    // 6.1 Divisão: valor negativo
    @Test
    void divNegativo() throws Exception {
        // Arrange
        calculadoraTest = new Calculadora(3);
        // Act
        calculadoraTest.dividir(-2);
        // Assert
        assertEquals(-3, calculadoraTest.getMemoria());
    }

    // 6.2 Divisão: divisão por zero com Exception
    @Test
    void divZero() {
        // Arrange
        calculadoraTest = new Calculadora(3);
        // Act + Assert
        assertThrows(Exception.class, () -> calculadoraTest.dividir(0));
    }

    // 7. Exponenciar: elevado a 1
    @Test
    void exponenciar() throws Exception {
        // Arrange
        calculadoraTest = new Calculadora(3);

        // Act
        calculadoraTest.exponenciar(1);

        // Assert
        assertEquals(3, calculadoraTest.getMemoria());
    }

    // 7.1 Exponenciar: elevado a 10
    @Test
    void exponenciar10() throws Exception {
        // Arrange
        calculadoraTest = new Calculadora(2);

        // Act
        calculadoraTest.exponenciar(10);

        // Assert
        assertEquals(1024, calculadoraTest.getMemoria());
    }

    // 7.2 Exponenciar: elevado mais que 10 (exception)
    @Test
    void exponenciar20() {
        // Arrange
        calculadoraTest = new Calculadora(2);

        // Act + Assert
        assertThrows(Exception.class, () -> calculadoraTest.exponenciar(20));
    }

    // 8. Testar o método zerar
    @Test
    void zerarMemoria() {
        // Arrange
        calculadoraTest = new Calculadora(5);

        // Act
        calculadoraTest.zerarMemoria();

        // Assert
        assertEquals(0, calculadoraTest.getMemoria());
    }
}
