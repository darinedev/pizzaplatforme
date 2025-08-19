package com.darine.pizzaplatforme.pizzaplatforme.Dto;

import com.darine.pizzaplatforme.pizzaplatforme.model.ModePaiement;
import com.darine.pizzaplatforme.pizzaplatforme.entities.StatutCommande;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommandeDto {

    private Long id;

    @NotBlank(message = "Le nom du client est obligatoire")
    private String nomClient;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    private BigDecimal montantTotal;
    private StatutCommande statut;
    private LocalDateTime dateCommande;
    private LocalDateTime dateLivraisonPrevue;
    private String commentaire;
    private ModePaiement modePaiement;
    private BigDecimal fraisLivraison;

    @Valid
    @NotEmpty(message = "La commande doit contenir au moins un item")
    private List<CommandeItemDto> items = new ArrayList<>();

    // Constructeurs
    public CommandeDto() {}

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

    public LocalDateTime getDateLivraisonPrevue() { return dateLivraisonPrevue; }
    public void setDateLivraisonPrevue(LocalDateTime dateLivraisonPrevue) { this.dateLivraisonPrevue = dateLivraisonPrevue; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public ModePaiement getModePaiement() { return modePaiement; }
    public void setModePaiement(ModePaiement modePaiement) { this.modePaiement = modePaiement; }

    public BigDecimal getFraisLivraison() { return fraisLivraison; }
    public void setFraisLivraison(BigDecimal fraisLivraison) { this.fraisLivraison = fraisLivraison; }

    public List<CommandeItemDto> getItems() { return items; }
    public void setItems(List<CommandeItemDto> items) { this.items = items; }
}