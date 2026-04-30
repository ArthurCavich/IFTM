package org.iftm.sistema.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PessoaTest {
    @Test
    public void testarRetornarNomeCompleto() {
        // arrange
        String entradaNome = "Bruno";
        String entradaSobrenome = "Queiroz";
        String respostaEsperada = "Bruno Queiroz";

        // act
        Pessoa p = new Pessoa(entradaNome, entradaSobrenome);
        String respostaObtida = p.retornarNomeCompleto();

        // assert
        assertEquals(respostaEsperada, respostaObtida);

    }
    @Test
    public void testarRetornarSigla() {
        // arrange
        String entradaNome = "Bruno";
        String entradaSobrenome = "Queiroz";
        String respostaEsperada = "BQ";

        // act
        Pessoa p = new Pessoa(entradaNome, entradaSobrenome);
        String respostaObtida = p.retornarIniciais();

        // assert
        assertEquals(respostaEsperada, respostaObtida);

    }

    @Test
    public void testarRetornarSiglaComEntradaMinuscula() {
        // arrange
        String entradaNome = "bruno";
        String entradaSobrenome = "queiroz";
        String respostaEsperada = "BQ";

        // act
        Pessoa p = new Pessoa(entradaNome, entradaSobrenome);
        String respostaObtida = p.retornarIniciais();

        // assert
        assertEquals(respostaEsperada, respostaObtida);

    }    
    
}
