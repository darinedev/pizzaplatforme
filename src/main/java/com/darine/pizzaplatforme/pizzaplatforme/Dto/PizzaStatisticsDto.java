package com.darine.pizzaplatforme.pizzaplatforme.Dto;


import java.math.BigDecimal;

public class PizzaStatisticsDto{

    private Long pizzaId;
    private String nomPizza;
    private Long totalQuantite;
    private BigDecimal totalVentes;
    private Long nombreCommandes;

    // Constructeur pour les requêtes JPQL
    public PizzaStatisticsDto(Long pizzaId, String nomPizza, Long totalQuantite, BigDecimal totalVentes, Long nombreCommandes) {
        this.pizzaId = pizzaId;
        this.nomPizza = nomPizza;
        this.totalQuantite = totalQuantite;
        this.totalVentes = totalVentes;
        this.nombreCommandes = nombreCommandes;
    }

    // Getters et Setters
    public Long getPizzaId() { return pizzaId; }
    public void setPizzaId(Long pizzaId) { this.pizzaId = pizzaId; }

    public String getNomPizza() { return nomPizza; }
    public void setNomPizza(String nomPizza) { this.nomPizza = nomPizza; }

    public Long getTotalQuantite() { return totalQuantite; }
    public void setTotalQuantite(Long totalQuantite) { this.totalQuantite = totalQuantite; }

    public BigDecimal getTotalVentes() { return totalVentes; }
    public void setTotalVentes(BigDecimal totalVentes) { this.totalVentes = totalVentes; }

    public Long getNombreCommandes() { return nombreCommandes; }
    public void setNombreCommandes(Long nombreCommandes) { this.nombreCommandes = nombreCommandes; }
}
