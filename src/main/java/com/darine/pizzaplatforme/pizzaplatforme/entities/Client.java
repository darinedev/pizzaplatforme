package com.darine.pizzaplatforme.pizzaplatforme.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_nom", nullable = false)
    @NotBlank(message = "Le nom du client est obligatoire")
    @Size(min = 2, max = 50)
    private String nom;

    @Column(name = "client_prenom", nullable = false)
    @NotBlank(message = "Le prénom du client est obligatoire")
    @Size(min = 2, max = 50)
    private String prenom;

    @Column(name = "client_telephone", nullable = false)
    @NotBlank(message = "Le téléphone du client est obligatoire")
    @Pattern(regexp = "^[+]?[0-9\\s-]{8,15}$")
    private String telephone;

    @Column(name = "client_email")
    @Email
    private String email;

    @Column(name = "client_adresse", nullable = false)
    @NotBlank
    @Size(min = 10, max = 200)
    private String adresse;
}
