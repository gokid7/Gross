package com.migros.courierService.exception;

import com.migros.courierService.model.response.CourierBaseResponse;
import com.migros.courierService.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CourierBaseResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(getRequestPath(request), ex.getMessage(), ex.getHttpStatus() , LocalDateTime.now().toString());
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(new CourierBaseResponse<>(null,false,errorResponse));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CourierBaseResponse<Void>> handleBadRequestException(BadRequestException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(getRequestPath(request), ex.getMessage(), ex.getHttpStatus() , LocalDateTime.now().toString());
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(new CourierBaseResponse<>(null,false,errorResponse));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CourierBaseResponse<Void>> handleException(Exception ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(getRequestPath(request), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CourierBaseResponse<>(null,false,errorResponse));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CourierBaseResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(getRequestPath(request), ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CourierBaseResponse<>(null,false,errorResponse));
    }

    private String getRequestPath(HttpServletRequest request){
        return request.getRequestURI() + (request.getQueryString() != null ? "?" +request.getQueryString() : "");
    }
}
