package com.darine.pizzaplatforme.pizzaplatforme.Controller;

import com.darine.pizzaplatforme.pizzaplatforme.model.Pizza;
import com.darine.pizzaplatforme.pizzaplatforme.Service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour les pizzas
 * @RestController : combine @Controller + @ResponseBody
 * @RequestMapping : définit le chemin de base pour toutes les méthodes
 * @CrossOrigin : autorise les requêtes cross-origin (pour le frontend)
 */
@RestController
@RequestMapping("/api/pizzas")
@CrossOrigin(
        origins = "http://localhost:3000",  // ou originPatterns = "*" si tu veux un joker
        allowCredentials = "true")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    /**
     * GET /api/pizzas - Récupère toutes les pizzas disponibles
     *
     * @GetMapping : raccourci pour @RequestMapping(method = RequestMethod.GET)
     */
    @GetMapping
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        List<Pizza> pizzas = pizzaService.getAllAvailablePizzas();
        return ResponseEntity.ok(pizzas);
    }

    /**
     * GET /api/pizzas/{id} - Récupère une pizza par son ID
     *
     * @PathVariable : extrait la valeur de l'URL
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
        Optional<Pizza> pizza = pizzaService.getPizzaById(id);

        if (pizza.isPresent()) {
            return ResponseEntity.ok(pizza.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * POST /api/pizzas - Crée une nouvelle pizza
     *
     * @RequestBody : lie le JSON de la requête à l'objet Java
     * @Valid : déclenche la validation des annotations sur l'entité
     */
    @PostMapping
    public ResponseEntity<Pizza> createPizza(@Valid @RequestBody Pizza pizza) {
        Pizza savedPizza = pizzaService.savePizza(pizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPizza);
    }

    /**
     * PUT /api/pizzas/{id} - Met à jour une pizza
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Long id, @Valid @RequestBody Pizza pizza) {
        Optional<Pizza> existingPizza = pizzaService.getPizzaById(id);

        if (existingPizza.isPresent()) {
            pizza.setId(id);
            Pizza updatedPizza = pizzaService.savePizza(pizza);
            return ResponseEntity.ok(updatedPizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    /**
     * DELETE /api/pizzas/{id} - Supprime une pizza
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        Optional<Pizza> pizza = pizzaService.getPizzaById(id);

        if (pizza.isPresent()) {
            pizzaService.deletePizza(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/pizzas/search?nom=margherita - Recherche par nom
     * @RequestParam : extrait un paramètre de requête
     */
    @GetMapping("/search")
    public ResponseEntity<List<Pizza>> searchPizzas(@RequestParam String nom) {
        List<Pizza> pizzas = pizzaService.searchPizzasByName(nom);
        return ResponseEntity.ok(pizzas);
    }
}

