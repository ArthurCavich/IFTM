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
    void testarLimiteDeHorasTrabalhadasInferior() {
        // Arrange
        Funcionario funcionario = new Funcionario("Paula", 100, 20.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(19); // < 20
        });
    }

    @Test
    void testarLimiteDeHorasTrabalhadasSuperior() {
        // Arrange
        Funcionario funcionario = new Funcionario("Ana", 100, 20.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(161);// > 160
        });
    }

    @Test
    void testarSetValorHoraValido() {
        // Arrange
        Funcionario funcionario = new Funcionario("Carlos", 100, 20.0);

        // Act
        funcionario.setValorHora(50.0);
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(5000.0, pagamento); // 100 * 50.0
    }

    @Test
    void testarSetValorHoraAbaixoDoMinimo() {
        // Arrange
        Funcionario funcionario = new Funcionario("lucia", 100, 20.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setValorHora(15.17); // < 15.18
        });
    }

    @Test
    void testarSetValorHoraAcimaDoMaximo() {
        // Arrange
        Funcionario funcionario = new Funcionario("Roberto", 100, 20.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setValorHora(151.81); // > 151.80
        });
    }

    @Test
    void testarSetNomeValido() {
        // Arrange
        Funcionario funcionario = new Funcionario("Pedro", 100, 20.0);

        // Act
        funcionario.setNome("Beatriz");
        String nome = funcionario.getNome();

        // Assert
        assertEquals("Beatriz", nome);
    }

    @Test
    void testarSetNomeComEspacos() {
        // Arrange
        Funcionario funcionario = new Funcionario("João", 100, 20.0);

        // Act
        funcionario.setNome("Maria da Silva");
        String nome = funcionario.getNome();

        // Assert
        assertEquals("Maria da Silva", nome);
    }

    @Test
    void testarSetNomeVazio() {
        // Arrange
        Funcionario funcionario = new Funcionario("Fernando", 100, 20.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setNome(""); // Nome vazio
        });
    }

    
}
