package com.darine.pizzaplatforme.pizzaplatforme.Dto;

import com.darine.pizzaplatforme.pizzaplatforme.model.ModePaiement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * DTO spécifique pour la création de commande (sans les champs calculés)
 */
public class CreateCommandeDto{

    @NotBlank(message = "Le nom du client est obligatoire")
    private String nomClient;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    private String commentaire;
    private ModePaiement modePaiement = ModePaiement.ESPECES;

    @Valid
    @NotEmpty(message = "La commande doit contenir au moins un item")
    private List<CreateCommandeItemDto> items;

    // Getters et Setters
    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public ModePaiement getModePaiement() { return modePaiement; }
    public void setModePaiement(ModePaiement modePaiement) { this.modePaiement = modePaiement; }

    public List<CreateCommandeItemDto> getItems() { return items; }
    public void setItems(List<CreateCommandeItemDto> items) { this.items = items; }
}
