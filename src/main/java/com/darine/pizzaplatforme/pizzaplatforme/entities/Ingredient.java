package com.darine.pizzaplatforme.pizzaplatforme.entities;

// Imports Jakarta Persistence
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// Imports Lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// Imports Jackson
import com.fasterxml.jackson.annotation.JsonBackReference;

// Imports Java Standard
import java.math.BigDecimal;

@Entity
@Table(name = "ingredients")

public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id", nullable = false)
    @JsonBackReference
    private PizzaEntities pizza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    @Column(nullable = false)
    private Integer quantite;

    @Column(nullable = false)
    private Boolean obligatoire = true;

    @DecimalMin(value = "0.0", message = "Le prix supplémentaire doit être positif")
    @Column(name = "prix_supplement", precision = 8, scale = 2)
    private BigDecimal prixSupplement = BigDecimal.ZERO;
}