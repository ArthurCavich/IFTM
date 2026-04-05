package atividade3.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FuncionarioTerceirizadoTest {

    @Test
    void testarDespesaTerceirizadoEsperado() {
        // Arrange
        FuncionarioTerceirizado funcionario = new FuncionarioTerceirizado("Carlos", 100, 50.0, 200.0);
        
        // Act
        double pagamento = funcionario.calcularPagamento();
        
        // Assert
        // Pagamento = (100 * 50) + (200 * 1.1) = 5000 + 220 = 5220
        assertEquals(5220.0, pagamento);
    }
    
    @Test
    void testarModificarDespesaAcimaLimite() {
        // Arrange
        FuncionarioTerceirizado funcionario = new FuncionarioTerceirizado("Carlos", 100, 50.0, 100.0);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setDespesasAdicionais(1100.0); // > 1000
        });
    }
    
    @Test
    void testarModificarDespesaNegativa() {
        // Arrange
        FuncionarioTerceirizado funcionario = new FuncionarioTerceirizado("Carlos", 160, 50.0, 100.0);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setDespesasAdicionais(-100.0); // < 0
        });
    }
}
