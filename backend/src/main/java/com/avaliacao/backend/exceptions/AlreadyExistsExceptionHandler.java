package com.avaliacao.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AlreadyExistsExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<AlreadyExistsExceptionCustom> error(AlreadyExistsException exception, HttpServletRequest request) {
        AlreadyExistsExceptionCustom exceptionCustom = new AlreadyExistsExceptionCustom(HttpStatus.CONFLICT.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionCustom);
    }

}
