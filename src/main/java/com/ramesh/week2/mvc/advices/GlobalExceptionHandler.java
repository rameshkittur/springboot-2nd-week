package com.ramesh.week2.mvc.advices;

import com.ramesh.week2.mvc.exception.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrors handleResourceNotFoundException(ResourceNotFoundException e) {
        return ApiErrors
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ApiErrors handleInternalServerError(Exception e){
        return ApiErrors
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrors handleInputValidationError(MethodArgumentNotValidException e){
        List<String>errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ApiErrors
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation error")
                .subErrors(errors)
                .build();
    }
}
