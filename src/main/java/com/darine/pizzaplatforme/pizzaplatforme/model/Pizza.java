package com.darine.pizzaplatforme.pizzaplatforme.model;
import com.darine.pizzaplatforme.pizzaplatforme.entities.TaillePizza;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la pizza est obligatoire")
    @Column(nullable = false)
    private String nom;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", message = "Le prix doit être positif")
    @Column(nullable = false)
    private BigDecimal prix;

    private String image;

    @Column(nullable = false)
    private Boolean disponible = true;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation; // ✅ Champ ajouté

    private String ingredients;
    private TaillePizza tailleDefaut;

    // Constructeurs
    public Pizza() {}

    public Pizza(String nom, String description, BigDecimal prix, String image) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.dateCreation = LocalDateTime.now(); // initialise à maintenant
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
