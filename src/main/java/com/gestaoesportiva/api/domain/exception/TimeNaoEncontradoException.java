package com.gestaoesportiva.api.domain.exception;

public class TimeNaoEncontradoException extends RuntimeException {
    public TimeNaoEncontradoException(String message) {
        super(message);
    }
}
