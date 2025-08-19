package com.darine.pizzaplatforme.pizzaplatforme.exception;

public class InvalidCommandeException extends RuntimeException {
    public InvalidCommandeException(String message) {
        super(message);
    }

    public InvalidCommandeException(String message, Throwable cause) {
        super(message, cause);
    }
}
