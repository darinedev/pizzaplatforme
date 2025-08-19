package com.darine.pizzaplatforme.pizzaplatforme.repository;

import com.darine.pizzaplatforme.pizzaplatforme.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository pour Pizza
 * @Repository : indique que c'est un composant de couche d'accès aux données
 * JpaRepository<Pizza, Long> : fournit des méthodes CRUD automatiques
 * - Pizza : type d'entité
 * - Long : type de la clé primaire
 */
@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    /**
     * Spring Data JPA génère automatiquement la requête basée sur le nom de la méthode
     * findBy + NomPropriete + Condition
     */
    List<Pizza> findByDisponibleTrue();

    /**
     * Recherche par nom (insensible à la casse)
     */
    List<Pizza> findByNomContainingIgnoreCase(String nom);

    /**
     * @Query : permet d'écrire des requêtes JPQL personnalisées
     */
    @Query("SELECT p FROM Pizza p WHERE p.disponible = true ORDER BY p.nom")
    List<Pizza> findAvailablePizzasOrderByName();
}