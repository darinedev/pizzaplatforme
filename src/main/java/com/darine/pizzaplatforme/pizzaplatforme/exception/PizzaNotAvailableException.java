package com.darine.pizzaplatforme.pizzaplatforme.exception;

public class PizzaNotAvailableException extends RuntimeException {
    public PizzaNotAvailableException(String message) {
        super(message);
    }

    public PizzaNotAvailableException(Long pizzaId) {
        super("Pizza avec l'ID " + pizzaId + " n'est pas disponible");
    }
}
