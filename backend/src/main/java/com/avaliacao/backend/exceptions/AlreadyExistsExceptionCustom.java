package com.avaliacao.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlreadyExistsExceptionCustom {

    private Integer status;
    private String message;

}
