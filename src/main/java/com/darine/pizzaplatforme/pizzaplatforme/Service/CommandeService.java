package com.darine.pizzaplatforme.pizzaplatforme.Service;

import com.darine.pizzaplatforme.pizzaplatforme.model.Commande;
import com.darine.pizzaplatforme.pizzaplatforme.model.CommandeItem;
import com.darine.pizzaplatforme.pizzaplatforme.model.Pizza;
import com.darine.pizzaplatforme.pizzaplatforme.entities.StatutCommande;
import com.darine.pizzaplatforme.pizzaplatforme.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private PizzaService pizzaService;

    /**
     * Crée une nouvelle commande avec calcul du montant total
     */
    @Transactional
    public Commande createCommande(Commande commande) {
        BigDecimal montantTotal = BigDecimal.ZERO;

        for (CommandeItem item : commande.getItems()) {
            Optional<Pizza> pizza = pizzaService.getPizzaById(item.getPizza().getId());
            if (pizza.isEmpty() || !pizza.get().getDisponible()) {
                throw new RuntimeException("Pizza non disponible: " + item.getPizza().getId());
            }

            item.setPrixUnitaire(pizza.get().getPrix());
            item.setCommande(commande);
            montantTotal = montantTotal.add(item.getSousTotal());
        }

        commande.setMontantTotal(montantTotal);
        commande.setStatut(StatutCommande.EN_ATTENTE);

        return commandeRepository.save(commande);
    }

    /**
     * Récupère une commande avec ses items initialisés
     */
    @Transactional(readOnly = true)
    public Optional<Commande> getCommandeById(Long id) {
        // Solution 1 : Lazy loading fonctionne si on est dans une transaction
        Optional<Commande> commandeOpt = commandeRepository.findById(id);
        commandeOpt.ifPresent(commande -> commande.getItems().size()); // force l'initialisation
        return commandeOpt;
    }

    /**
     * Récupère toutes les commandes (pour l'admin)
     */
    @Transactional(readOnly = true)
    public List<Commande> getAllCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        // Initialiser les items pour éviter lazy exception
        commandes.forEach(cmd -> cmd.getItems().size());
        return commandes;
    }

    /**
     * Récupère les commandes d'un client
     */
    @Transactional(readOnly = true)
    public List<Commande> getCommandesByEmail(String email) {
        List<Commande> commandes = commandeRepository.findByEmailOrderByDateCommandeDesc(email);
        commandes.forEach(cmd -> cmd.getItems().size()); // initialise items
        return commandes;
    }

    /**
     * Met à jour le statut d'une commande
     */
    @Transactional
    public Commande updateStatutCommande(Long id, StatutCommande nouveauStatut) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isEmpty()) {
            throw new RuntimeException("Commande non trouvée: " + id);
        }

        Commande commande = optionalCommande.get();
        commande.setStatut(nouveauStatut);
        return commandeRepository.save(commande);
    }
}
