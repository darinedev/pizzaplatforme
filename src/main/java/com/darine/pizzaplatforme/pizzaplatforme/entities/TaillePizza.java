package com.darine.pizzaplatforme.pizzaplatforme.entities;



public enum TaillePizza {
    PETITE("Petite", 1.0),
    MOYENNE("Moyenne", 1.3),
    GRANDE("Grande", 1.6);

    private final String libelle;
    private final double coefficient;

    TaillePizza(String libelle, double coefficient) {
        this.libelle = libelle;
        this.coefficient = coefficient;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
