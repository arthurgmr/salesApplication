package io.github.arthurgmr.exception;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException() {
        super("Password invalid!");
    }
}
