package br.edu.iftm.tspi.pbackorm.catalogo_service.exception;

public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(String mensagem) {
        super(mensagem);
    }

}
