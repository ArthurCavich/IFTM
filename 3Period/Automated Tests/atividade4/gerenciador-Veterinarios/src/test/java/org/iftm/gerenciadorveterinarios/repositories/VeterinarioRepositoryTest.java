package org.iftm.gerenciadorveterinarios.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class VeterinarioRepositoryTest {
    @Autowired
    private VeterinarioRepository repositorio;

    @Test
    @DisplayName("Ciclo 1 - Busca exata deve respeitar caixa")
    public void deveRetornarVazioQuandoBuscaExataReceberMinusculoERegistroEstiverMaiusculo() {
        // Arrange
        String nomeBuscado = "pedro";

        // Act
        List<Veterinario> veterinarios = repositorio.findByNome(nomeBuscado);

        // Assert
        assertNotNull(veterinarios);
        assertTrue(veterinarios.isEmpty());
    }

    @Test
    @DisplayName("Ciclo 1 - Busca exata deve encontrar PEDRO")
    public void deveEncontrarVeterinarioQuandoBuscaExataReceberMesmoTextoDoBanco() {
        // Arrange
        String nomeBuscado = "PEDRO";

        // Act
        List<Veterinario> veterinarios = repositorio.findByNome(nomeBuscado);

        // Assert
        assertEquals(1, veterinarios.size());
        assertEquals("PEDRO", veterinarios.get(0).getNome());
    }

    @Test
    @DisplayName("Ciclo 1 - Busca ignore case nao deve encontrar nome inexistente")
    public void deveRetornarVazioQuandoBuscaIgnoreCaseReceberNomeInexistente() {
        // Arrange
        String nomeBuscado = "jose";

        // Act
        List<Veterinario> veterinarios = repositorio.findByNomeIgnoreCase(nomeBuscado);

        // Assert
        assertNotNull(veterinarios);
        assertTrue(veterinarios.isEmpty());
    }

    @Test
    @DisplayName("Ciclo 1 - Busca ignore case deve encontrar JOAO")
    public void deveEncontrarVeterinarioQuandoBuscaIgnoreCaseReceberMinusculo() {
        // Arrange
        String nomeBuscado = "joao";

        // Act
        List<Veterinario> veterinarios = repositorio.findByNomeIgnoreCase(nomeBuscado);

        // Assert
        assertEquals(1, veterinarios.size());
        assertEquals("JOAO", veterinarios.get(0).getNome());
    }

    @Test
    @DisplayName("Ciclo 2 - Busca por trecho ro deve retornar PEDRO e ROBERTO")
    public void deveRetornarPedroERobertoQuandoBuscarPorTrechoRo() {
        // Arrange
        String trecho = "ro";

        // Act
        List<Veterinario> veterinarios = repositorio.findByNomeContainsIgnoreCase(trecho);
        Set<String> nomes = veterinarios.stream().map(Veterinario::getNome).collect(Collectors.toSet());

        // Assert
        assertEquals(Set.of("PEDRO", "ROBERTO"), nomes);
    }

    @Test
    @DisplayName("Ciclo 2 - Busca por trecho Maria deve retornar vazio")
    public void deveRetornarVazioQuandoBuscarPorTrechoSemRegistros() {
        // Arrange
        String trecho = "Maria";

        // Act
        List<Veterinario> veterinarios = repositorio.findByNomeContainsIgnoreCase(trecho);

        // Assert
        assertTrue(veterinarios.isEmpty());
    }

    @Test
    @DisplayName("Ciclo 2 - Busca por trecho vazio deve retornar todos")
    public void deveRetornarTodosQuandoBuscarPorTrechoVazio() {
        // Arrange
        String trecho = "";
        int totalEsperado = repositorio.findAll().size();

        // Act
        List<Veterinario> veterinarios = repositorio.findByNomeContainsIgnoreCase(trecho);

        // Assert
        assertEquals(totalEsperado, veterinarios.size());
    }

    @Test
    @DisplayName("Ciclo 3 - Deve filtrar salario maior que valor informado")
    public void deveRetornarVeterinariosComSalarioMaiorQueValor() {
        // Arrange
        BigDecimal salarioBase = new BigDecimal("3000.00");

        // Act
        List<Veterinario> veterinarios = repositorio.findBySalarioGreaterThan(salarioBase);
        Set<String> nomes = veterinarios.stream().map(Veterinario::getNome).collect(Collectors.toSet());

        // Assert
        assertEquals(Set.of("ROBERTO", "ANA"), nomes);
    }

    @Test
    @DisplayName("Ciclo 3 - Deve filtrar salario menor que valor informado")
    public void deveRetornarVeterinariosComSalarioMenorQueValor() {
        // Arrange
        BigDecimal salarioBase = new BigDecimal("2600.00");

        // Act
        List<Veterinario> veterinarios = repositorio.findBySalarioLessThan(salarioBase);
        Set<String> nomes = veterinarios.stream().map(Veterinario::getNome).collect(Collectors.toSet());

        // Assert
        assertEquals(Set.of("JOAO", "CARLA"), nomes);
    }

    @Test
    @DisplayName("Ciclo 3 - Deve filtrar salario entre minimo e maximo")
    public void deveRetornarVeterinariosComSalarioEntreMinimoEMaximo() {
        // Arrange
        BigDecimal minimo = new BigDecimal("2400.00");
        BigDecimal maximo = new BigDecimal("4600.00");

        // Act
        List<Veterinario> veterinarios = repositorio.findBySalarioBetween(minimo, maximo);
        Set<String> nomes = veterinarios.stream().map(Veterinario::getNome).collect(Collectors.toSet());

        // Assert
        assertEquals(Set.of("PEDRO", "ROBERTO", "JOAO"), nomes);
    }

    @Test
    @DisplayName("Ciclo 4 - Update deve persistir e refletir novas buscas")
    public void devePersistirAtualizacaoEEncontrarSomentePelosNovosDados() {
        // Arrange
        Veterinario veterinario = repositorio.findByNome("PEDRO").get(0);
        Integer idVeterinario = veterinario.getId();
        veterinario.setNome("PAULO");
        veterinario.setSalario(new BigDecimal("6100.00"));

        // Act
        repositorio.save(veterinario);
        List<Veterinario> porNomeNovoExato = repositorio.findByNome("PAULO");
        List<Veterinario> porNomeNovoIgnoreCase = repositorio.findByNomeIgnoreCase("paulo");
        List<Veterinario> porNomeAntigoExato = repositorio.findByNome("PEDRO");
        List<Veterinario> porNomeAntigoLike = repositorio.findByNomeContainsIgnoreCase("pedro");
        List<Veterinario> porFaixaNova = repositorio.findBySalarioGreaterThan(new BigDecimal("6000.00"));
        List<Veterinario> porFaixaAntiga = repositorio.findBySalarioBetween(new BigDecimal("2900.00"),
                new BigDecimal("3100.00"));

        // Assert
        assertFalse(porNomeNovoExato.isEmpty());
        assertFalse(porNomeNovoIgnoreCase.isEmpty());
        assertEquals(idVeterinario, porNomeNovoExato.get(0).getId());
        assertEquals(idVeterinario, porNomeNovoIgnoreCase.get(0).getId());
        assertTrue(porNomeAntigoExato.isEmpty());
        assertTrue(porNomeAntigoLike.isEmpty());
        assertTrue(porFaixaNova.stream().anyMatch(v -> v.getId().equals(idVeterinario)));
        assertTrue(porFaixaAntiga.stream().noneMatch(v -> v.getId().equals(idVeterinario)));
    }
}
