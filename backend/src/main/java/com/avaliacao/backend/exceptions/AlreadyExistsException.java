package com.avaliacao.backend.exceptions;

public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1268545388731278489L;

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
