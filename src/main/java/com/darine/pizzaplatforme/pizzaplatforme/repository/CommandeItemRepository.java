package com.darine.pizzaplatforme.pizzaplatforme.repository;

import com.darine.pizzaplatforme.pizzaplatforme.model.CommandeItem;
import com.darine.pizzaplatforme.pizzaplatforme.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository pour la gestion des items de commande
 */
@Repository
public interface CommandeItemRepository extends JpaRepository<CommandeItem, Long> {

    // Recherches par commande
    List<CommandeItem> findByCommandeId(Long commandeId);

    // Recherches par pizza
    List<CommandeItem> findByPizza(Pizza pizza);
    List<CommandeItem> findByPizzaId(Long pizzaId);

    // Statistiques des ventes
    @Query("SELECT ci.pizza, SUM(ci.quantite) as totalQuantite FROM CommandeItem ci " +
            "JOIN ci.commande c " +
            "WHERE c.dateCommande BETWEEN :debut AND :fin " +
            "GROUP BY ci.pizza " +
            "ORDER BY totalQuantite DESC")
    List<Object[]> findPizzasLesPlusVendues(@Param("debut") LocalDateTime debut,
                                            @Param("fin") LocalDateTime fin);

    @Query("SELECT SUM(ci.quantite) FROM CommandeItem ci WHERE ci.pizza.id = :pizzaId")
    Long getTotalQuantiteVendue(@Param("pizzaId") Long pizzaId);

    // Top des ventes
    @Query("SELECT ci.pizza, SUM(ci.quantite) as totalQuantite, SUM(ci.sousTotal) as totalVentes " +
            "FROM CommandeItem ci " +
            "GROUP BY ci.pizza " +
            "ORDER BY totalQuantite DESC")
    List<Object[]> getTopVentes();
}