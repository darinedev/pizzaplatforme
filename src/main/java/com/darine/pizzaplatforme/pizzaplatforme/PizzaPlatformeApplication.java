package com.darine.pizzaplatforme.pizzaplatforme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PizzaPlatformeApplication {

	public static void main(String[] args) {
		System.out.println("Démarrage application Pizzeria...");
		try {
			SpringApplication app = new SpringApplication(PizzaPlatformeApplication.class);
			app.run(args);
			System.out.println("Application démarrée !");
		} catch (Exception e) {
			System.err.println("Erreur: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@GetMapping("/")
	public String home() {
		return " Pizzeria API - L'application fonctionne !";
	}

	@GetMapping("/test")
	public String test() {
		return " Test réussi - Serveur opérationnel";
	}

	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}
}

