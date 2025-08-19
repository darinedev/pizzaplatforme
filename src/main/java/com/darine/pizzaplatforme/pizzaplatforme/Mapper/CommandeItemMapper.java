package com.darine.pizzaplatforme.pizzaplatforme.Mapper;

import com.darine.pizzaplatforme.pizzaplatforme.Dto.CommandeItemDto;
import com.darine.pizzaplatforme.pizzaplatforme.Dto.CreateCommandeItemDto;
import com.darine.pizzaplatforme.pizzaplatforme.model.CommandeItem;
import com.darine.pizzaplatforme.pizzaplatforme.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandeItemMapper {

    @Autowired
    private PizzaMapper pizzaMapper;

    /**
     * Convertit une entité CommandeItem en DTO
     */
    public CommandeItemDto toDTO(CommandeItem item) {
        if (item == null) {
            return null;
        }

        CommandeItemDto dto = new CommandeItemDto();
        dto.setId(item.getId());
        dto.setPizzaId(item.getPizza().getId());
        dto.setPizza(pizzaMapper.toDTO(item.getPizza()));
        dto.setQuantite(item.getQuantite());
        dto.setPrixUnitaire(item.getPrixUnitaire());
        dto.setTaille(item.getTaille());
        dto.setInstructionsSpeciales(item.getInstructionsSpeciales());
        dto.setSousTotal(item.getSousTotal());

        return dto;
    }

    /**
     * Convertit un CreateCommandeItemDTO en entité CommandeItem
     */
    public CommandeItem toEntity(CreateCommandeItemDto createDTO, Pizza pizza) {
        if (createDTO == null) {
            return null;
        }

        CommandeItem item = new CommandeItem();
        item.setPizza(pizza);
        item.setQuantite(createDTO.getQuantite());
        item.setTaille(createDTO.getTaille());
        item.setInstructionsSpeciales(createDTO.getInstructionsSpeciales());
        // Le prix unitaire sera défini par le service

        return item;
    }

    /**
     * Convertit un CommandeItemDTO en entité
     */
    public CommandeItem toEntity(CommandeItemDto dto) {
        if (dto == null) {
            return null;
        }

        CommandeItem item = new CommandeItem();
        item.setId(dto.getId());
        item.setQuantite(dto.getQuantite());
        item.setPrixUnitaire(dto.getPrixUnitaire());
        item.setTaille(dto.getTaille());
        item.setInstructionsSpeciales(dto.getInstructionsSpeciales());

        return item;
    }

    /**
     * Met à jour une entité CommandeItem avec les données d'un DTO
     */
    public void updateEntityFromDTO(CommandeItem item, CommandeItemDto dto) {
        if (item == null || dto == null) {
            return;
        }

        item.setQuantite(dto.getQuantite());
        item.setTaille(dto.getTaille());
        item.setInstructionsSpeciales(dto.getInstructionsSpeciales());
        // Le prix unitaire ne doit pas être modifié après création
    }

    /**
     * Convertit une liste d'entités en liste de DTOs
     */
    public List<CommandeItemDto> toDTOList(List<CommandeItem> items) {
        if (items == null) {
            return null;
        }

        return items.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une liste de DTOs en liste d'entités
     */
    public List<CommandeItem> toEntityList(List<CommandeItemDto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}