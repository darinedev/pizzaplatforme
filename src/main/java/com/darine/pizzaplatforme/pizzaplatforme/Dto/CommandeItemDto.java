package com.darine.pizzaplatforme.pizzaplatforme.Dto;

import com.darine.pizzaplatforme.pizzaplatforme.entities.TaillePizza;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CommandeItemDto{

    private Long id;

    @NotNull(message = "La pizza est obligatoire")
    private Long pizzaId;

    private PizzaDto pizza; // Pour l'affichage

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 1, message = "La quantité doit être au moins de 1")
    private Integer quantite;

    private BigDecimal prixUnitaire;
    private TaillePizza taille = TaillePizza.MOYENNE;
    private String instructionsSpeciales;
    private BigDecimal sousTotal;

    // Constructeurs
    public CommandeItemDto() {}

    public CommandeItemDto(Long pizzaId, Integer quantite, TaillePizza taille) {
        this.pizzaId = pizzaId;
        this.quantite = quantite;
        this.taille = taille;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPizzaId() { return pizzaId; }
    public void setPizzaId(Long pizzaId) { this.pizzaId = pizzaId; }

    public PizzaDto getPizza() { return pizza; }
    public void setPizza(PizzaDto pizza) { this.pizza = pizza; }

    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }

    public BigDecimal getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(BigDecimal prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    public TaillePizza getTaille() { return taille; }
    public void setTaille(TaillePizza taille) { this.taille = taille; }

    public String getInstructionsSpeciales() { return instructionsSpeciales; }
    public void setInstructionsSpeciales(String instructionsSpeciales) { this.instructionsSpeciales = instructionsSpeciales; }

    public BigDecimal getSousTotal() { return sousTotal; }
    public void setSousTotal(BigDecimal sousTotal) { this.sousTotal = sousTotal; }
}