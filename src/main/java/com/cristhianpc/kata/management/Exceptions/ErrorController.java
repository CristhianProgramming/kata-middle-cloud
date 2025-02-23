package com.cristhianpc.kata.management.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handlerException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
