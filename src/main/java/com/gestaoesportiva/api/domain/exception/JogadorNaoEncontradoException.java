package com.gestaoesportiva.api.domain.exception;

public class JogadorNaoEncontradoException extends RuntimeException {
    public JogadorNaoEncontradoException(String message) {
        super(message);
    }
}
