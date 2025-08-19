package com.darine.pizzaplatforme.pizzaplatforme.entities;
// Imports Jakarta Persistence
import com.darine.pizzaplatforme.pizzaplatforme.model.Commande;
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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lignes_commande")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id", nullable = false)
    private PizzaEntities pizza;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    @Column(nullable = false)
    private Integer quantite;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TaillePizza taille;

    @DecimalMin(value = "0.0", message = "Le prix unitaire doit être positif")
    @Column(name = "prix_unitaire", nullable = false, precision = 8, scale = 2)
    private BigDecimal prixUnitaire;

    @DecimalMin(value = "0.0", message = "Le sous-total doit être positif")
    @Column(name = "sous_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal sousTotal;

    @ElementCollection
    @CollectionTable(name = "ligne_commande_supplements", joinColumns = @JoinColumn(name = "ligne_commande_id"))
    @Column(name = "supplement")
    private List<String> supplements = new ArrayList<>();

    @Column(length = 200)
    private String commentaire;
}
