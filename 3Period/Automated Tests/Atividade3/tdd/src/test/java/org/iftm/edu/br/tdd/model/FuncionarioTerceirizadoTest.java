package org.iftm.edu.br.tdd.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FuncionarioTerceirizadoTest {

    private FuncionarioTerceirizado funcionario;

    @BeforeEach
    void setUp() {
        funcionario = new FuncionarioTerceirizado("João", 25, 90.0, 0);
    }

    @Test
    void testarModificarDespesaAcimadoLimiteGeraErro() {
        // Arrange
        String mensagemEsperada = "As despesas adicionais não podem ser superiores a R$ 1.000,00.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setDespesasAdicionais(1001));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    void testarModificarDespesaGeraPagamentoAcimaLimiteSalarioGerandoErro() {
        // Arrange
        String mensagemEsperada = "O pagamento não pode ser superior a R$ 10.000,00.";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setDespesasAdicionais(910));

        // Assert
        assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    void testarModificarDespesaGeraPagamentoValido() {
        // Act
        funcionario.setDespesasAdicionais(500);
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(9550.0, pagamento, 0.01);
    }

    @Test
    void testarModificarDespesaComValorValidoProduzPagamentoEsperado() {
        // Act
        funcionario.setDespesasAdicionais(100);
        double pagamento = funcionario.calcularPagamento();

        // Assert
        assertEquals(9110.0, pagamento, 0.01);
    }
}
