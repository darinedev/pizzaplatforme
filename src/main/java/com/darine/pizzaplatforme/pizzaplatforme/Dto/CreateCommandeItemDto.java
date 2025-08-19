package com.darine.pizzaplatforme.pizzaplatforme.Dto;

import com.darine.pizzaplatforme.pizzaplatforme.entities.TaillePizza;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateCommandeItemDto{

    @NotNull(message = "L'ID de la pizza est obligatoire")
    private Long pizzaId;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 1, message = "La quantité doit être au moins de 1")
    private Integer quantite;

    private TaillePizza taille = TaillePizza.MOYENNE;
    private String instructionsSpeciales;

    // Getters et Setters
    public Long getPizzaId() { return pizzaId; }
    public void setPizzaId(Long pizzaId) { this.pizzaId = pizzaId; }

    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }

    public TaillePizza getTaille() { return taille; }
    public void setTaille(TaillePizza taille) { this.taille = taille; }

    public String getInstructionsSpeciales() { return instructionsSpeciales; }
    public void setInstructionsSpeciales(String instructionsSpeciales) { this.instructionsSpeciales = instructionsSpeciales; }
}
