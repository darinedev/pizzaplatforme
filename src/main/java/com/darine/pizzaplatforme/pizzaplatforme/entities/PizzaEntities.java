package com.darine.pizzaplatforme.pizzaplatforme.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "pizzas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la pizza est obligatoire")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    @Column(nullable = false, length = 100)
    private String nom;

    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    @Column(length = 500)
    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être positif")
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal prix;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TaillePizza taille = TaillePizza.MOYENNE;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CategorieIngredient categorie = CategorieIngredient.CLASSIQUE;

    @Column(nullable = false)
    private Boolean disponible = true;

    @Min(value = 1, message = "Le temps de préparation doit être au moins 1 minute")
    @Column(name = "temps_preparation")
    private Integer tempsPreparation = 15;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation = LocalDateTime.now();

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    // Relations
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Ingredient> ingredients = new ArrayList<>();

    @PreUpdate
    public void preUpdate() {
        this.dateModification = LocalDateTime.now();
    }
}
