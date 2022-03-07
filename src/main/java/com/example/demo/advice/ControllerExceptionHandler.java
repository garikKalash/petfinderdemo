package com.example.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(RuntimeException exception, WebRequest request){
        Map<String, String> body = new HashMap<>();
        body.put("msg", exception.getMessage());
        return handleExceptionInternal(exception, body, null,
                HttpStatus.BAD_REQUEST, request);
    }
}
