package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.exception.NegotiateRule;
import io.github.arthurgmr.exception.OrderNotFound;
import io.github.arthurgmr.rest.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(NegotiateRule.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNegotiateRule (NegotiateRule ex) {
        String messageError = ex.getMessage();
        return new ApiError(messageError);
    }

    @ExceptionHandler(OrderNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleOrderNotFound (OrderNotFound ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map( err -> err.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiError(errors);
    }

}
