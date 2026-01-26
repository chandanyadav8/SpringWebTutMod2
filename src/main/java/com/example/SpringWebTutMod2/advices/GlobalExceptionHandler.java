package com.example.SpringWebTutMod2.advices;

import com.example.SpringWebTutMod2.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponses<?>>handleResourceNotFoundException(ResourceNotFoundException exception)
    {
        ApiError apiError=ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
        return buildApiResponse(apiError);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponses<?>>handleInternalServerError(Exception exception)
    {

        ApiError apiError=ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage())
                .build();
        return  buildApiResponse(apiError);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponses<?>>handleInputValidationError(MethodArgumentNotValidException exception)
    {
    List<String> errors=exception.getBindingResult()
            .getAllErrors().stream().map(a->a.getDefaultMessage())
            .collect(Collectors.toList());
    ApiError apiError=ApiError.builder().status(HttpStatus.BAD_REQUEST).message(errors.toString())
            .build();
    return  buildApiResponse(apiError);
    }
    private ResponseEntity<ApiResponses<?>> buildApiResponse(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponses<>(apiError),apiError.getStatus());
    }
}
