package com.darine.pizzaplatforme.pizzaplatforme.model;



public enum ModePaiement {
    ESPECES("Espèces"),
    CARTE_BANCAIRE("Carte bancaire"),
    PAYPAL("PayPal"),
    VIREMENT("Virement"),
    CHEQUE("Chèque");

    private final String libelle;

    ModePaiement(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() { return libelle; }
}
