package com.elst.banking_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    // handle specific exception
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponse> handleAccountException(AccountException ex, WebRequest webReq) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                webReq.getDescription(false),
                "ACCOUNT_NOT_FOUND"
        );
        ErrorResponse response = new ErrorResponse(errorDetails);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // handle general exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, WebRequest webReq) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                webReq.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        ErrorResponse response = new ErrorResponse(errorDetails);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
