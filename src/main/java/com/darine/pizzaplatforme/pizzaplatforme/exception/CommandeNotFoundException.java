package com.darine.pizzaplatforme.pizzaplatforme.exception;

public class CommandeNotFoundException extends RuntimeException {
    public CommandeNotFoundException(String message) {
        super(message);
    }

    public CommandeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandeNotFoundException(Long commandeId) {
        super("Commande avec l'ID " + commandeId + " non trouvée");
    }
}
