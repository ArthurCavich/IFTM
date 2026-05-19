package atividade3.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuncionarioTerceirizadoTeste {

    private FuncionarioTerceirizado funcionario;

    @BeforeEach
    void setUp() {
        funcionario = new FuncionarioTerceirizado("João", 25, 90.0, 0);
    }

    @Test
    void testarModificarDespesaAcimadoLimiteGeraErro() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> funcionario.setDespesasAdicionais(1001));
        Assertions.assertEquals("As despesas adicionais não podem ser superiores a R$ 1.000,00.", ex.getMessage());
    }

    @Test
    void testarModificarDespesageraPagamentoacimaLimiteSalarioGerandoErro() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> funcionario.setDespesasAdicionais(910));
        Assertions.assertEquals("O pagamento não pode ser superior a R$ 10.000,00.", ex.getMessage());
    }

    @Test
    void testarModificarDespesaGeraPagamentoValido() {
        funcionario.setDespesasAdicionais(500);
        Assertions.assertEquals(9550.0, funcionario.calcularPagamento());
    }
}
