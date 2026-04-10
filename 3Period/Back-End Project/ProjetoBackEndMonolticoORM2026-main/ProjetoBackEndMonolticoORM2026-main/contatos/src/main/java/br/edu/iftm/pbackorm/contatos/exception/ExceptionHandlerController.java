package br.edu.iftm.pbackorm.contatos.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //criador de tratamento global de exceções
public class ExceptionHandlerController {

@ExceptionHandler(MethodArgumentNotValidException.class);

    public ResponseEntity<ErroDTO>
    
}
