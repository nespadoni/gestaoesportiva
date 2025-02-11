package com.gestaoesportiva.api.domain.exception;

public class JogoNaoEncontradoException extends RuntimeException {
    public JogoNaoEncontradoException(String message) {
        super(message);
    }
}
