package com.darine.pizzaplatforme.pizzaplatforme.model;
import com.darine.pizzaplatforme.pizzaplatforme.entities.StatutCommande;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité Commande
 */
@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du client est obligatoire")
    @Column(nullable = false)
    private String nomClient;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    @Column(nullable = false)
    private String telephone;

    @NotBlank(message = "L'adresse est obligatoire")
    @Column(nullable = false)
    private String adresse;

    @NotNull
    @Column(nullable = false)
    private BigDecimal montantTotal;

    /**
     * @Enumerated : pour mapper un enum en base de données
     * STRING : stocke le nom de l'enum (recommandé)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCommande statut = StatutCommande.EN_ATTENTE;

    /**
     * @Column avec columnDefinition pour spécifier le type exact
     */
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCommande = LocalDateTime.now();

    /**
     * @OneToMany : relation un-à-plusieurs
     * mappedBy : indique que la relation est gérée par le champ "commande" dans CommandeItem
     * cascade : opérations en cascade (persist, merge, remove)
     * fetch : stratégie de chargement (LAZY = chargement à la demande)
     * orphanRemoval : supprime les entités orphelines
     */
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CommandeItem> items = new ArrayList<>();

    // Constructeurs
    public Commande() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public BigDecimal getMontantTotal() { return montantTotal; }
    public void setMontantTotal(BigDecimal montantTotal) { this.montantTotal = montantTotal; }

    public StatutCommande getStatut() { return statut; }
    public void setStatut(StatutCommande statut) { this.statut = statut; }

    public LocalDateTime getDateCommande() { return dateCommande; }
    public void setDateCommande(LocalDateTime dateCommande) { this.dateCommande = dateCommande; }

    public List<CommandeItem> getItems() { return items; }
    public void setItems(List<CommandeItem> items) { this.items = items; }

    /**
     * Méthode helper pour ajouter un item à la commande
     */
    public void addItem(CommandeItem item) {
        items.add(item);
        item.setCommande(this);
    }

    public LocalDateTime getDateLivraisonPrevue() {
        return null;
    }

    public String getCommentaire() {
        return "";
    }

    public ModePaiement getModePaiement() {
        return null;
    }

    public BigDecimal getFraisLivraison() {
        return null;
    }

    public void setCommentaire(String commentaire) {
    }

    public void setModePaiement(ModePaiement modePaiement) {
    }

    public void setFraisLivraison(BigDecimal zero) {
    }

    public void setDateLivraisonPrevue(LocalDateTime dateLivraisonPrevue) {
    }
}