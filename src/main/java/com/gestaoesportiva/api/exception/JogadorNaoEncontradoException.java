package com.gestaoesportiva.api.exception;

public class JogadorNaoEncontradoException extends RuntimeException {
    public JogadorNaoEncontradoException(String message) {
        super(message);
    }
}
