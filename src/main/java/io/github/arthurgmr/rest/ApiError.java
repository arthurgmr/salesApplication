package io.github.arthurgmr.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiError {
    @Getter
    private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

    public ApiError (String messageError) {
        this.errors = Arrays.asList(messageError);
    }
}
