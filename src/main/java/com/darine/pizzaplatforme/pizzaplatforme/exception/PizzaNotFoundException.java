package com.darine.pizzaplatforme.pizzaplatforme.exception;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(String message) {
        super(message);
    }

    public PizzaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PizzaNotFoundException(Long pizzaId) {
        super("Pizza avec l'ID " + pizzaId + " non trouvée");
    }
}
