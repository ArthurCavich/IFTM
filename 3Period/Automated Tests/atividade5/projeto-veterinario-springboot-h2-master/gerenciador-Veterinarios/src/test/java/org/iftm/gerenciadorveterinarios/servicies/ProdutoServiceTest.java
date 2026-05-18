package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    public void deveCadastrarComAtivoTrueMesmoEnviandoFalse() {
        Produto entrada = new Produto(null, "Ração Premium", BigDecimal.TEN, 50, false);

        when(repository.save(any(Produto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Produto salvo = service.cadastrar(entrada);

        assertTrue(salvo.isAtivo());
        verify(repository).save(any(Produto.class));
    }

    @Test
    public void deveLancarExcecaoQuandoPrecoNegativo() {
        Produto entrada = new Produto(null, "Produto Inválido", BigDecimal.valueOf(-1), 10, false);

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(entrada));

        verify(repository, never()).save(any());
    }

    @Test
    public void deveInativarQuandoProdutoExistir() {
        Integer id = 1;
        Produto produto = new Produto(id, "Ração Premium", BigDecimal.TEN, 50, true);

        when(repository.findById(id)).thenReturn(Optional.of(produto));
        when(repository.save(any(Produto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Produto resultado = service.inativar(id);

        assertFalse(resultado.isAtivo());
        verify(repository).save(produto);
    }
}
