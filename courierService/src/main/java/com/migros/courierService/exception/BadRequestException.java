package com.migros.courierService.exception;

import org.apache.http.HttpStatus;

public class BadRequestException extends AbstractBaseException{
    public BadRequestException(String message,int messageCode){
        super(message,messageCode, HttpStatus.SC_BAD_REQUEST);
    }
}
