package com.darine.pizzaplatforme.pizzaplatforme.Controller;

import com.darine.pizzaplatforme.pizzaplatforme.model.Commande;
import com.darine.pizzaplatforme.pizzaplatforme.entities.StatutCommande;
import com.darine.pizzaplatforme.pizzaplatforme.Service.CommandeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(
        origins = "http://localhost:3000",  // ou originPatterns = "*" si tu veux un joker
        allowCredentials = "true")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    /**
     * POST /api/commandes - Crée une nouvelle commande
     */
    @PostMapping
    public ResponseEntity<Commande> createCommande(@Valid @RequestBody Commande commande) {
        try {
            Commande savedCommande = commandeService.createCommande(commande);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCommande);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/commandes/{id} - Récupère une commande par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.getCommandeById(id);

        if (commande.isPresent()) {
            return ResponseEntity.ok(commande.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/commandes - Récupère toutes les commandes (pour l'admin)
     */
    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.getAllCommandes();
        return ResponseEntity.ok(commandes);
    }

    /**
     * GET /api/commandes/client/{email} - Récupère les commandes d'un client
     */
    @GetMapping("/client/{email}")
    public ResponseEntity<List<Commande>> getCommandesByEmail(@PathVariable String email) {
        List<Commande> commandes = commandeService.getCommandesByEmail(email);
        return ResponseEntity.ok(commandes);
    }

    /**
     * PUT /api/commandes/{id}/statut - Met à jour le statut d'une commande
     */
    @PutMapping("/{id}/statut")
    public ResponseEntity<Commande> updateStatutCommande(
            @PathVariable Long id,
            @RequestBody StatutCommandeRequest request) {
        try {
            Commande updatedCommande = commandeService.updateStatutCommande(id, request.getStatut());
            return ResponseEntity.ok(updatedCommande);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Classe interne pour recevoir le nouveau statut
     */
    public static class StatutCommandeRequest {
        private StatutCommande statut;

        public StatutCommande getStatut() { return statut; }
        public void setStatut(StatutCommande statut) { this.statut = statut; }
    }
}
