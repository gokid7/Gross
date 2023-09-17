package com.migros.courierService.exception;

import org.apache.http.HttpStatus;

public class ResourceNotFoundException extends AbstractBaseException{
    public ResourceNotFoundException(String message,int messageCode){
        super(message,messageCode, HttpStatus.SC_NOT_FOUND);
    }
}
