package com.br.reusai.api.domain.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final Integer httpStatus;
    private final String message;

    public BusinessException(Integer httpStatus,String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
