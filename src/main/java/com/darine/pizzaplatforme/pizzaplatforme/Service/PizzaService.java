package com.darine.pizzaplatforme.pizzaplatforme.Service;
import com.darine.pizzaplatforme.pizzaplatforme.model.Pizza;
import com.darine.pizzaplatforme.pizzaplatforme.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service pour la logique métier des pizzas
 * @Service : indique que c'est un composant de couche service
 */
@Service
public class PizzaService {

    /**
     * @Autowired : injection de dépendance automatique
     * Spring injecte automatiquement une instance de PizzaRepository
     */
    @Autowired
    private PizzaRepository pizzaRepository;

    /**
     * Récupère toutes les pizzas disponibles
     */
    public List<Pizza> getAllAvailablePizzas() {
        return pizzaRepository.findByDisponibleTrue();
    }

    /**
     * Récupère une pizza par son ID
     * Optional<T> : conteneur qui peut contenir ou non une valeur
     */
    public Optional<Pizza> getPizzaById(Long id) {
        return pizzaRepository.findById(id);
    }

    /**
     * Sauvegarde une pizza
     */
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    /**
     * Recherche des pizzas par nom
     */
    public List<Pizza> searchPizzasByName(String nom) {
        return pizzaRepository.findByNomContainingIgnoreCase(nom);
    }

    /**
     * Récupère toutes les pizzas (pour l'admin)
     */
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    /**
     * Supprime une pizza
     */
    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }
}