package org.iftm.edu.br.tdd.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FuncionarioTest {

    private Funcionario funcionario;

    @BeforeEach
    void setUp() {
        funcionario = new Funcionario("Maria", 25, 20.0);
    }

    @Test
    void testarModificarHorasAbaixoLimiteInferiorGeraErro() {
        // Arrange
        String mensagemEsperada = "O número de horas trabalhadas por funcionários próprios deve ser um valor entre 5 e 40.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setHorasTrabalhadas(4));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    void testarModificarHorasAcimaLimiteSuperiorGeraErro() {
        // Arrange
        String mensagemEsperada = "O número de horas trabalhadas por funcionários próprios deve ser um valor entre 5 e 40.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setHorasTrabalhadas(41));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    void testarModificarHorasComValoresValidosGeraPagamentoAcimaLimiteGeraErro() {
        // Arrange
        funcionario.setValorHora(90.0);
        String mensagemEsperada = "O pagamento não pode ser superior a R$ 10.000,00.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setHorasTrabalhadas(40));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    void testarModificarHorasComValoresValidosProduzPagamentoEsperado() {
        // Act
        funcionario.setHorasTrabalhadas(30);
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(2400.0, pagamento, 0.01);
    }

    @Test
    void testarModificarHorasComValoresValidosGeraPagamentoAbaixoLimiteGeraErro() {
        // Arrange
        funcionario.setValorHora(15.18);
        String mensagemEsperada = "O pagamento não pode ser inferior a R$ 1.518,00.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setHorasTrabalhadas(5));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    void testarModificarValorPorHoraAbaixoLimiteInferiorGeraErro() {
        // Arrange
        String mensagemEsperada = "O valor por hora deve ser um valor entre 15,18 e 151,80.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setValorHora(15.17));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    void testarModificarValorPorHoraAcimaLimiteSuperiorGeraErro() {
        // Arrange
        String mensagemEsperada = "O valor por hora deve ser um valor entre 15,18 e 151,80.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setValorHora(151.81));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    void testarModificarValorPorHoraComValoresValidosGeraPagamentoAcimaLimiteGeraErro() {
        // Arrange
        funcionario.setHorasTrabalhadas(40);
        String mensagemEsperada = "O pagamento não pode ser superior a R$ 10.000,00.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setValorHora(90.0));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    void testarModificarValorPorHoraComValoresValidosProduzPagamentoEsperado() {
        // Act
        funcionario.setValorHora(25.0);
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(2500.0, pagamento, 0.01);
    }

    @Test
    void testarModificarValorPorHoraComValoresValidosGeraPagamentoAbaixoLimiteGeraErro() {
        // Arrange
        funcionario.setHorasTrabalhadas(5);
        String mensagemEsperada = "O pagamento não pode ser inferior a R$ 1.518,00.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setValorHora(15.18));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }
}
