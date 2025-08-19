package com.darine.pizzaplatforme.pizzaplatforme.Dto;

import com.darine.pizzaplatforme.pizzaplatforme.entities.TaillePizza;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO pour Pizza - transfert de données vers le client
 */
public class PizzaDto {

    private Long id;

    @NotBlank(message = "Le nom de la pizza est obligatoire")
    private String nom;

    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", message = "Le prix doit être positif")
    private BigDecimal prix;

    private String image;
    private Boolean disponible;
    private LocalDateTime dateCreation;
    private String ingredients;
    private TaillePizza tailleDefaut;

    // Constructeurs
    public PizzaDto() {}

    public PizzaDto(Long id, String nom, String description, BigDecimal prix, String image, Boolean disponible) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.disponible = disponible;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public TaillePizza getTailleDefaut() { return tailleDefaut; }
    public void setTailleDefaut(TaillePizza tailleDefaut) { this.tailleDefaut = tailleDefaut; }
}