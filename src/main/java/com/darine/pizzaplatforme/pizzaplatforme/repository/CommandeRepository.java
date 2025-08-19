package com.darine.pizzaplatforme.pizzaplatforme.repository;

import com.darine.pizzaplatforme.pizzaplatforme.model.Commande;
import com.darine.pizzaplatforme.pizzaplatforme.entities.StatutCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByStatut(StatutCommande statut);

    List<Commande> findByEmailOrderByDateCommandeDesc(String email);

    /**
     * Requête avec paramètre nommé
     */
    @Query("SELECT c FROM Commande c WHERE c.dateCommande >= :dateDebut ORDER BY c.dateCommande DESC")
    List<Commande> findCommandesSince(@Param("dateDebut") LocalDateTime dateDebut);
}