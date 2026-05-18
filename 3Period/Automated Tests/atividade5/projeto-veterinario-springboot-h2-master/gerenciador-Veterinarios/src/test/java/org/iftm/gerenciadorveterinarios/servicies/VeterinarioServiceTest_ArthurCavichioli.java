package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VeterinarioServiceTest_ArthurCavichioli {

    @Mock
    private VeterinarioRepository repositorio;

    @InjectMocks
    private VeterinarioService service;

    @Test
    public void deveRetornarDoisVeterinariosQuandoBuscarPorSilva() {
        Veterinario v1 = new Veterinario(1, "João Silva", "", "", BigDecimal.ZERO);
        Veterinario v2 = new Veterinario(2, "Maria Silva", "", "", BigDecimal.ZERO);
        List<Veterinario> listaMock = Arrays.asList(v1, v2);

        when(repositorio.findByNomeContains("Silva")).thenReturn(listaMock);

        List<Veterinario> resultado = service.buscaVeterinariosComParteNome("Silva");

        assertEquals(2, resultado.size());
        verify(repositorio).findByNomeContains("Silva");
    }

    @Test
    public void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {
        Integer idInexistente = 99;

        when(repositorio.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.apagarPorId(idInexistente));

        verify(repositorio, never()).delete(any());
    }
}
