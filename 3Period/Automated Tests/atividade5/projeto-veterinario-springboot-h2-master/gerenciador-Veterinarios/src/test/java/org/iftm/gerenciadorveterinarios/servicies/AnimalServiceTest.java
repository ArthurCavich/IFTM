package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Animal;
import org.iftm.gerenciadorveterinarios.repositories.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @Mock
    private AnimalRepository repository;

    @InjectMocks
    private AnimalService service;

    @Test
    public void deveCadastrarComInternadoTrueMesmoEnviandoFalse() {
        Animal entrada = new Animal(null, "Rex", "Cão", 3, false);

        when(repository.save(any(Animal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Animal salvo = service.cadastrar(entrada);

        assertTrue(salvo.isInternado());
        verify(repository).save(any(Animal.class));
    }

    @Test
    public void deveLancarExcecaoQuandoEspecieNaoAtendida() {
        Animal entrada = new Animal(null, "Naja", "Cobra", 2, false);

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(entrada));

        verify(repository, never()).save(any());
    }

    @Test
    public void deveDarAltaQuandoAnimalExistir() {
        Integer id = 1;
        Animal animal = new Animal(id, "Rex", "Cão", 3, true);

        when(repository.findById(id)).thenReturn(Optional.of(animal));
        when(repository.save(any(Animal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Animal resultado = service.darAlta(id);

        assertFalse(resultado.isInternado());
        verify(repository).save(animal);
    }
}
