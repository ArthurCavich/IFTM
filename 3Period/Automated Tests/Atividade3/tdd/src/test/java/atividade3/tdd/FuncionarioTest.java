package atividade3.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuncionarioTest {

    private Funcionario funcionario;

    @BeforeEach
    void setUp() {
        funcionario = new Funcionario("Maria", 25, 20.0);
    }

    @Test
    void testarModificarHorasAbaixoLimiteInferiorGeraErro() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> funcionario.setHorasTrabalhadas(4));
        Assertions.assertEquals(
                "O número de horas trabalhadas por funcionários próprios deve ser um valor entre 5 e 40.",
                ex.getMessage());
    }

    @Test
    void testarModificarHorasAcimaLimiteSuperiorGeraErro() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> funcionario.setHorasTrabalhadas(41));
        Assertions.assertEquals(
                "O número de horas trabalhadas por funcionários próprios deve ser um valor entre 5 e 40.",
                ex.getMessage());
    }

    @Test
    void testarModificarHorasComValoresValidosGeraPagamentoAcimaLimiteGeraErro() {
        funcionario.setValorHora(90.0);
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> funcionario.setHorasTrabalhadas(40));
        Assertions.assertEquals("O pagamento não pode ser superior a R$ 10.000,00.", ex.getMessage());
    }

    @Test
    void testarModificarHorasComValoresValidosProduzPagamentoEsperado() {
        funcionario.setHorasTrabalhadas(30);
        Assertions.assertEquals(2400.0, funcionario.calcularPagamento());
    }

    @Test
    void testarModificarValorPorHoraAbaixoLimiteInferiorGeraErro() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> funcionario.setValorHora(15.17));
        Assertions.assertEquals("O valor por hora deve ser um valor entre 15,18 e 151,80.", ex.getMessage());
    }

    @Test
    void testarModificarValorPorHoraAcimaLimiteSuperiorGeraErro() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> funcionario.setValorHora(151.81));
        Assertions.assertEquals("O valor por hora deve ser um valor entre 15,18 e 151,80.", ex.getMessage());
    }

    @Test
    void testarModificarValorPorHoraComValoresValidosGeraPagamentoAcimaLimiteGeraErro() {
        funcionario.setHorasTrabalhadas(40);
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> funcionario.setValorHora(90.0));
        Assertions.assertEquals("O pagamento não pode ser superior a R$ 10.000,00.", ex.getMessage());
    }

    @Test
    void testarModificarValorPorHoraComValoresValidosProduzPagamentoEsperado() {
        funcionario.setValorHora(25.0);
        Assertions.assertEquals(2500.0, funcionario.calcularPagamento());
    }
}
