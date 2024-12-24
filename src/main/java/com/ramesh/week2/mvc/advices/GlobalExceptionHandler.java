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
    public ApiResponse<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return buildErrorResponse(apiErrors);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleInternalServerError(Exception e) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();

        return buildErrorResponse(apiErrors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleInputValidationError(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        ApiErrors apiErrors = ApiErrors
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation error")
                .subErrors(errors)
                .build();

        return buildErrorResponse(apiErrors);
    }

    private ApiResponse<?> buildErrorResponse(ApiErrors apiErrors) {
        return new ApiResponse<>(apiErrors);
    }
}
