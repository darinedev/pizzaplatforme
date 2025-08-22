<<<<<<< HEAD
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
=======
# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)
>>>>>>> b6c3f39 (Initialize project using Create React App)
