package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.exception.NegotiateRule;
import io.github.arthurgmr.rest.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(NegotiateRule.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNegotiateRule (NegotiateRule ex) {
        String messageError = ex.getMessage();
        return new ApiError(messageError);
    }
}
