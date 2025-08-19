package com.darine.pizzaplatforme.pizzaplatforme.entities;
// Imports Jakarta Persistence
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// Imports Lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// Imports Jackson

// Imports Java Standard


@Entity
@Table(name = "adresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TypeAdresse type = TypeAdresse.DOMICILE;

    @NotBlank(message = "La rue est obligatoire")
    @Column(nullable = false, length = 200)
    private String rue;

    @NotBlank(message = "La ville est obligatoire")
    @Column(nullable = false, length = 100)
    private String ville;

    @Pattern(regexp = "^[0-9]{5}$", message = "Le code postal doit contenir 5 chiffres")
    @Column(name = "code_postal", nullable = false, length = 5)
    private String codePostal;

    @Column(length = 100)
    private String complement;

    @Column(nullable = false)
    private Boolean principal = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
