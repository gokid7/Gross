package com.migros.courierService.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractBaseException extends RuntimeException{
    private final String message;
    private final int messageCode;
    private final int httpStatus;

    protected AbstractBaseException(String message, int messageCode, int httpStatus) {
        this.message = message;
        this.messageCode = messageCode;
        this.httpStatus = httpStatus;
    }
}
