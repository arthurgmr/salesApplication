package io.github.arthurgmr.rest;

import lombok.Getter;

import java.util.List;

public class ApiError {
    @Getter
    private String error;

    public ApiError (String messageError) {
        this.error = messageError;
    }
}
