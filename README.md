<h1 align="center">🍕 Application Pizza Full Stack</h1>

## 📋 Description
Application web complète permettant aux utilisateurs de consulter un menu de pizzas, personnaliser leur commande et passer commande. Le projet est composé d'un backend REST API en Java Spring Boot et d'un frontend web moderne.

## Technologies utilisées
### Backend
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (développement)
- **Maven**
- **Swagger (Documentation API)**
### Frontend
- **HTML5, CSS3, JavaScript(react)**
- **API REST**
### **Outils**
- Postman (Tests API)
- Git
## 📁 Structure du projet
pizzaplatforme/
pizzaplatforme/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/darine/pizzaplatforme/
│   │   │   │       ├── PizzaPlatformeApplication.java
│   │   │   │       ├── controller/
│   │   │   │       ├── model/
│   │   │   │       ├── repository/
│   │   │   │       └── service/
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       ├── static/
│   │   │       └── templates/
│   │   └── test/
│   ├── pom.xml
│   └── README.md
├── pizza-app/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   └── pages/
│   ├── package.json
│   └── README.md
└── README.md
## 🚀 Installation et lancement
### Prérequis
- Java 17 ou supérieur
- Maven 3.6+
- Node.js 16+ et npm
- Git
### Étapes

# Cloner le repository
git clone https://github.com/darinedev/pizzaplatforme.git
# Naviguer dans le projet
cd pizzaplatforme
# Installer les dépendances
mvn clean install


# Lancer l'application
## Backend
mvn spring-boot:run
## Frontend
- cd pizza-app
- npm install
- npm start


- Frontend : http://localhost:3000
- API : http://localhost:8080/api
- Base de données H2 : http://localhost:8080/h2-console

## 🔗 **Endpoints API (exemple)**
### **Pizzas**
- `GET /api/pizzas` → Liste des pizzas
- `GET /api/pizzas/1` → Détails d’une pizza
- `POST /api/pizzas` → Ajouter une pizza (Admin)
- `Put /api/pizzas`
- `Del /api/pizzas/{id}` → Supprimer une pizza 

### **Commandes**
- `POST /api/commandes` → Créer une commande
- `GET api/commandes/{id}` → Voir une commande


## 📅 **Planning prévisionnel**
| Semaine | Tâches |
|---------|--------|
| 1 | Backend (modèle + API pizzas) |
| 2 | API commandes + Tests backend |
| 3 | Frontend (UI + Connexion API) |
| 4 | Tests complets + Documentation |

## 🚀 **Évolutions futures**
- Authentification utilisateur (JWT)
- Paiement en ligne
- Notifications temps réel
- Interface administrateur complète