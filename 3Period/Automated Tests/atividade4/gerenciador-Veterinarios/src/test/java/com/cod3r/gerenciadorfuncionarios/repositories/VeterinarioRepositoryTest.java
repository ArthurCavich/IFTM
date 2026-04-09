package com.cod3r.gerenciadorfuncionarios.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // define a forma de ordenar os métodos
public class VeterinarioRepositoryTest {
    @Autowired // faz injeção de dependência da classe a ser testada
    private VeterinarioRepository repositorio;

    @Test
    void test() {
        fail("not yet implemented");
    }

    /**
     * <p>
     * <b>Cenário:</b> Verificar se a busca por id realmente retorna o veterinario
     * correto.
     * </p>
     * <p>
     * <b>Entrada:</b> idExistente = 1
     * </p>
     * <p>
     * <b>Saída esperada:</b> Veterinário com nome "Conceição Evaristo" e email
     * 'conceicao@gmail.com'
     * </p>
     */

    @Test
    @DisplayName("Testa se a busca pelo ID realmente retorna o veterinario correto.")
    @Order(1) // Definir ordem do caso de teste
    public void testarBuscaPorIdRetornaVeterinarioCorreto1() {
        // arrange
        Integer idexistente = 1;
        String nomeEsperado = "Conceição Evaristo";
        String emailEsperado = "conceicao@gmail.com";

        // Act
        Veterinario veterinarioObtido = repositorio.getById(idexistente);

        // Assert
        assertNotNull(veterinarioObtido);
        assertEquals(nomeEsperado, veterinarioObtido.getNome());
        assertEquals(emailEsperado, veterinarioObtido.getEmail());
    }

    @Test
    @DisplayName("Teste realizado igual o de cima porém utilizando o Optional dentro da lista de objetos")
    @Order(2)
    public void testarBuscaPorIdRetornaVeterinarioCorretoComOptional() {
        // arrange
        Integer idExistente = 1;
        String nomeEsperado = "Conceição Evaristo";
        String emailEsperado = "conceicao@gmail.com";

        // Act
        Optional<Veterinario> veterinarioEncontrado = repositorio.findById(idExistente);

        // Assert
        assertNotNull(veterinarioEncontrado);
        assertEquals(nomeEsperado, veterinarioEncontrado.get().getNome());
        assertEquals(emailEsperado, veterinarioEncontrado.get().getEmail());
    }

    

}
