package atividade3.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FuncionarioTest {

    @Test // teste comum
    void testarCalcularPagamento() {
        // Arrange
        Funcionario funcionario = new Funcionario("Arthur", 100, 20.0);

        // Act
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(2000.0, pagamento);
    }

    @Test
    void testarPagamentoMinimo() {
        // Arrange
        Funcionario funcionario = new Funcionario("Maria", 100, 15.18);

        // Act
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(1518.0, pagamento);
    }

    @Test
    void testarPagamentoMaximo() {
        // Arrange
        Funcionario funcionario = new Funcionario("José", 160, 62.5);

        // Act
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(10000.0, pagamento);
    }

    @Test
    void testarHorasMin() {
        // Arrange
        Funcionario funcionario = new Funcionario("João", 20, 75.9);

        // Act
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(1518.0, pagamento); // Respeita todos os limites
    }

    @Test
    void testarHorasMax() {
        // Arrange
        Funcionario funcionario = new Funcionario("Paula", 160, 50.0);

        // Act
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(8000.0, pagamento);
    }

    @Test
    void testarValorHoraMin() {
        // Arrange
        Funcionario funcionario = new Funcionario("Ana", 20, 75.9);

        // Act
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(1518.0, pagamento);
    }

    @Test
    void testarValorHoraMax() {
        // Arrange
        Funcionario funcionario = new Funcionario("Bruno", 20, 150.0);

        // Act
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(10000.0, pagamento);
    }

    // ===== TESTES DOS SETTERS =====

    @Test
    void testarSetterValido() {
        // Arrange
        Funcionario funcionario = new Funcionario("João", 100, 20.0);

        // Act
        funcionario.setHorasTrabalhadas(120);
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(2400.0, pagamento); // 120 * 20
    }

    @Test
    void testarLimiteInferior() {// < 20
        // Arrange
        Funcionario funcionario = new Funcionario("João", 100, 20.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(19.9);
        });
    }

    @Test
    void testarLimiteSuperior() {// > 160
        // Arrange
        Funcionario funcionario = new Funcionario("João", 100, 20.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(161);
        });
    }
}
