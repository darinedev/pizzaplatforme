package com.darine.pizzaplatforme.pizzaplatforme.Mapper;

import com.darine.pizzaplatforme.pizzaplatforme.Dto.CommandeDto;
import com.darine.pizzaplatforme.pizzaplatforme.Dto.CreateCommandeDto;
import com.darine.pizzaplatforme.pizzaplatforme.model.Commande;
import com.darine.pizzaplatforme.pizzaplatforme.entities.StatutCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandeMapper {

    @Autowired
    private CommandeItemMapper commandeItemMapper;

    /**
     * Convertit une entité Commande en DTO complet
     */
    public CommandeDto toDTO(Commande commande) {
        if (commande == null) {
            return null;
        }

        CommandeDto dto = new CommandeDto();
        dto.setId(commande.getId());
        dto.setNomClient(commande.getNomClient());
        dto.setEmail(commande.getEmail());
        dto.setTelephone(commande.getTelephone());
        dto.setAdresse(commande.getAdresse());
        dto.setMontantTotal(commande.getMontantTotal());
        dto.setStatut(commande.getStatut());
        dto.setDateCommande(commande.getDateCommande());
        dto.setDateLivraisonPrevue(commande.getDateLivraisonPrevue());
        dto.setCommentaire(commande.getCommentaire());
        dto.setModePaiement(commande.getModePaiement());
        dto.setFraisLivraison(commande.getFraisLivraison());

        // Conversion des items
        if (commande.getItems() != null) {
            dto.setItems(commandeItemMapper.toDTOList(commande.getItems()));
        }

        return dto;
    }

    /**
     * Convertit un CreateCommandeDTO en entité Commande
     */
    public Commande toEntity(CreateCommandeDto createDTO) {
        if (createDTO == null) {
            return null;
        }

        Commande commande = new Commande();
        commande.setNomClient(createDTO.getNomClient());
        commande.setEmail(createDTO.getEmail());
        commande.setTelephone(createDTO.getTelephone());
        commande.setAdresse(createDTO.getAdresse());
        commande.setCommentaire(createDTO.getCommentaire());
        commande.setModePaiement(createDTO.getModePaiement());
        commande.setStatut(StatutCommande.EN_ATTENTE);
        commande.setDateCommande(LocalDateTime.now());
        commande.setFraisLivraison(BigDecimal.ZERO);

        return commande;
    }

    /**
     * Convertit un CommandeDTO en entité (pour les mises à jour)
     */
    public Commande toEntity(CommandeDto dto) {
        if (dto == null) {
            return null;
        }

        Commande commande = new Commande();
        commande.setId(dto.getId());
        commande.setNomClient(dto.getNomClient());
        commande.setEmail(dto.getEmail());
        commande.setTelephone(dto.getTelephone());
        commande.setAdresse(dto.getAdresse());
        commande.setMontantTotal(dto.getMontantTotal());
        commande.setStatut(dto.getStatut());
        commande.setDateCommande(dto.getDateCommande());
        commande.setDateLivraisonPrevue(dto.getDateLivraisonPrevue());
        commande.setCommentaire(dto.getCommentaire());
        commande.setModePaiement(dto.getModePaiement());
        commande.setFraisLivraison(dto.getFraisLivraison());

        return commande;
    }

    /**
     * Met à jour les champs modifiables d'une commande
     */
    public void updateEntityFromDTO(Commande commande, CommandeDto dto) {
        if (commande == null || dto == null) {
            return;
        }

        // Seuls certains champs peuvent être modifiés après création
        commande.setStatut(dto.getStatut());
        commande.setDateLivraisonPrevue(dto.getDateLivraisonPrevue());
        commande.setCommentaire(dto.getCommentaire());

        // L'adresse peut être modifiée si la commande n'est pas encore en préparation
        if (commande.getStatut() == StatutCommande.EN_ATTENTE ||
                commande.getStatut() == StatutCommande.CONFIRMEE) {
            commande.setAdresse(dto.getAdresse());
            commande.setTelephone(dto.getTelephone());
        }
    }

    /**
     * Convertit une liste d'entités en liste de DTOs
     */
    public List<CommandeDto> toDTOList(List<Commande> commandes) {
        if (commandes == null) {
            return null;
        }

        return commandes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}