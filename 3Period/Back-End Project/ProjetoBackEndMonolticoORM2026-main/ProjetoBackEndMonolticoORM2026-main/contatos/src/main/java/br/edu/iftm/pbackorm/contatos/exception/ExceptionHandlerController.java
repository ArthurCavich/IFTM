package br.edu.iftm.pbackorm.contatos.exception;

import java.time.LocalDateTime;
import java.util.Arrays;

import br.edu.iftm.pbackorm.contatos.dto.ErroDTO;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // criador de tratamento global de exceções
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDTO> tratarBadRequest(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(erro -> erro.getDefaultMessage())
                .orElse("Dados invalidos");

        ErroDTO erro = ErroDTO.builder()
                .mensagem(mensagem)
                .dataHora(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroDTO> tratarNaoEncontrado(EntityNotFoundException ex) {
        ErroDTO erro = ErroDTO.builder()
                .mensagem("Não encontrado")
                .erros(Arrays.asList(ex.getMessage()))
                .dataHora(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
