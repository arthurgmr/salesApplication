package io.github.arthurgmr.exception;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound() {
        super("Order not found!");
    }
}
