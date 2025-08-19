package com.darine.pizzaplatforme.pizzaplatforme.Mapper;

import com.darine.pizzaplatforme.pizzaplatforme.Dto.PizzaDto;
import com.darine.pizzaplatforme.pizzaplatforme.model.Pizza;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper pour convertir entre Pizza et PizzaDTO
 */
@Component
public class PizzaMapper {

    /**
     * Convertit une entité Pizza en DTO
     */
    public PizzaDto toDTO(Pizza pizza) {
        if (pizza == null) {
            return null;
        }

        PizzaDto dto = new PizzaDto();
        dto.setId(pizza.getId());
        dto.setNom(pizza.getNom());
        dto.setDescription(pizza.getDescription());
        dto.setPrix(pizza.getPrix());
        dto.setImage(pizza.getImage());
        dto.setDisponible(pizza.getDisponible());
        dto.setDateCreation(pizza.getDateCreation());
        dto.setIngredients(pizza.getIngredients());
        dto.setTailleDefaut(pizza.getTailleDefaut());

        return dto;
    }

    /**
     * Convertit un DTO Pizza en entité
     */
    public Pizza toEntity(PizzaDto dto) {
        if (dto == null) {
            return null;
        }

        Pizza pizza = new Pizza();
        pizza.setId(dto.getId());
        pizza.setNom(dto.getNom());
        pizza.setDescription(dto.getDescription());
        pizza.setPrix(dto.getPrix());
        pizza.setImage(dto.getImage());
        pizza.setDisponible(dto.getDisponible());
        pizza.setIngredients(dto.getIngredients());
        pizza.setTailleDefaut(dto.getTailleDefaut());

        return pizza;
    }

    /**
     * Met à jour une entité Pizza avec les données d'un DTO
     */
    public void updateEntityFromDTO(Pizza pizza, PizzaDto dto) {
        if (pizza == null || dto == null) {
            return;
        }

        pizza.setNom(dto.getNom());
        pizza.setDescription(dto.getDescription());
        pizza.setPrix(dto.getPrix());
        pizza.setImage(dto.getImage());
        pizza.setDisponible(dto.getDisponible());
        pizza.setIngredients(dto.getIngredients());
        pizza.setTailleDefaut(dto.getTailleDefaut());
    }

    /**
     * Convertit une liste d'entités en liste de DTOs
     */
    public List<PizzaDto> toDTOList(List<Pizza> pizzas) {
        if (pizzas == null) {
            return null;
        }

        return pizzas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une liste de DTOs en liste d'entités
     */
    public List<Pizza> toEntityList(List<PizzaDto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}