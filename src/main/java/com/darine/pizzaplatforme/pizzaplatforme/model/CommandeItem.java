package com.darine.pizzaplatforme.pizzaplatforme.model;

import com.darine.pizzaplatforme.pizzaplatforme.entities.TaillePizza;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Entité qui représente un item dans une commande (pizza + quantité)
 * SOLUTION 1 : Avec champ sousTotal persisté en base de données
 */
@Entity
@Table(name = "commande_items")
public class CommandeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @NotNull
    @Min(value = 1, message = "La quantité doit être au moins de 1")
    @Column(nullable = false)
    private Integer quantite;

    @NotNull
    @Column(nullable = false)
    private BigDecimal prixUnitaire;

    // NOUVEAU : Champ sousTotal persisté en base de données
    @NotNull
    @Column(nullable = false)
    private BigDecimal sousTotal;

    // Constructeurs
    public CommandeItem() {}

    public CommandeItem(Pizza pizza, Integer quantite, BigDecimal prixUnitaire) {
        this.pizza = pizza;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.sousTotal = calculerSousTotal(); // Calcul automatique
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }

    public Pizza getPizza() { return pizza; }
    public void setPizza(Pizza pizza) { this.pizza = pizza; }

    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
        updateSousTotal(); // Met à jour automatiquement le sous-total
    }

    public BigDecimal getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        updateSousTotal(); // Met à jour automatiquement le sous-total
    }

    public BigDecimal getSousTotal() { return sousTotal; }
    public void setSousTotal(BigDecimal sousTotal) { this.sousTotal = sousTotal; }

    // Méthodes privées pour le calcul
    private BigDecimal calculerSousTotal() {
        if (prixUnitaire != null && quantite != null) {
            return prixUnitaire.multiply(BigDecimal.valueOf(quantite));
        }
        return BigDecimal.ZERO;
    }

    private void updateSousTotal() {
        this.sousTotal = calculerSousTotal();
    }

    /**
     * Callback JPA : s'exécute avant la sauvegarde
     */
    @PrePersist
    @PreUpdate
    private void beforeSave() {
        updateSousTotal();
    }

    public String getInstructionsSpeciales() {
        return "";
    }

    public TaillePizza getTaille() {
        return null;
    }

    public void setTaille(TaillePizza taille) {
    }

    public void setInstructionsSpeciales(String instructionsSpeciales) {
    }
}