package org.iftm.sistema.servicies;

import org.iftm.sistema.entities.Pessoa;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GeradorCrachasTest {

    // Testar o cenário de teste com sucesso da impressão.
    @Test
    public void testarImpressaoCrachaComSucessoQuandoImpressoraOnlineESemErros() {
        // Arragne
        String nome = "Ana";
        String sobrenome = "Silva";
        Pessoa pessoa = new Pessoa(nome, sobrenome);
        String nomeCompleto = "Ana Silva";
        String sigla = "AN";

        GerenciadorImpressora gerenciadorImpressoraMock = mock(GerenciadorImpressora.class);

        // Configurar o mock
        // cenário com a impressora online
        when(gerenciadorImpressoraMock.estaConectado()).thenReturn(true);
        // definindo o cenário da impressão com sucesso, retornando o valor 1
        when(gerenciadorImpressoraMock.imprimir(nomeCompleto, sigla)).thenReturn(1);
        GeradorCracha geradorCracha = new GeradorCracha(gerenciadorImpressoraMock);
        // Act e assert
        assertDoesNotThrow(() -> {
            geradorCracha.gerarPara(pessoa);
        });
    }

    // Testar o cenário de teste na qual a impressora não está online.
    @Test
    public void testarImpressaoCrachaNãoImprimeQuandoImpressoraOffline() {
        // Arragne
        String nome = "Ana";
        String sobrenome = "Silva";
        Pessoa pessoa = new Pessoa(nome, sobrenome);
        String nomeCompleto = "Ana Silva";
        String sigla = "AN";
        String mensagemErroEsperado = "Impressora Offline";

        GerenciadorImpressora gerenciadorImpressoraMock = mock(GerenciadorImpressora.class);

        // Configurar o mock

        // cenário com a impressora offline
        when(gerenciadorImpressoraMock.estaConectado()).thenReturn(false);
        GeradorCracha geradorCracha = new GeradorCracha(gerenciadorImpressoraMock);

        // Act e assert
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            geradorCracha.gerarPara(pessoa);
        });

        assertEquals(mensagemErroEsperado, e.getMessage());

        verify(gerenciadorImpressoraMock, never()).imprimir(nomeCompleto, sigla);
    }

}