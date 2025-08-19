package com.darine.pizzaplatforme.pizzaplatforme.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PizzaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePizzaNotFoundException(
            PizzaNotFoundException ex, WebRequest request) {
        logger.warn("Pizza non trouvée: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Pizza Non Trouvée",
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommandeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommandeNotFoundException(
            CommandeNotFoundException ex, WebRequest request) {
        logger.warn("Commande non trouvée: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Commande Non Trouvée",
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PizzaNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handlePizzaNotAvailableException(
            PizzaNotAvailableException ex, WebRequest request) {
        logger.warn("Pizza non disponible: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Pizza Non Disponible",
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCommandeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCommandeException(
            InvalidCommandeException ex, WebRequest request) {
        logger.warn("Commande invalide: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Commande Invalide",
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Erreur de validation: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erreur de Validation",
                "Les données fournies ne sont pas valides",
                request.getDescription(false),
                errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // HANDLER AMÉLIORÉ POUR LE DÉBOGAGE
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {

        // LOG DÉTAILLÉ POUR LE DÉBOGAGE
        logger.error("=== ERREUR GLOBALE CAPTURÉE ===");
        logger.error("Type d'exception: {}", ex.getClass().getName());
        logger.error("Message: {}", ex.getMessage());
        logger.error("Cause racine: {}", ex.getCause() != null ? ex.getCause().getMessage() : "Aucune");
        logger.error("Chemin de la requête: {}", request.getDescription(false));

        // Log de la stack trace complète
        logger.error("Stack trace complète:", ex);

        // Log des 5 premières lignes de la stack trace pour debug rapide
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
            logger.error("Premières lignes de la stack trace:");
            Arrays.stream(ex.getStackTrace())
                    .limit(10)
                    .forEach(trace -> logger.error("  at {}", trace));
        }

        // Si c'est une exception spécifique commune, loggons plus de détails
        if (ex instanceof NullPointerException) {
            logger.error("NPE détectée - vérifiez les objets null dans votre code");
        } else if (ex instanceof ClassCastException) {
            logger.error("ClassCastException - problème de conversion de type");
        } else if (ex.getMessage() != null && ex.getMessage().contains("JSON")) {
            logger.error("Possible problème de parsing JSON");
        }

        logger.error("=== FIN ERREUR GLOBALE ===");

        // Créer une réponse d'erreur plus détaillée pour le développement
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erreur Interne du Serveur",
                "Une erreur inattendue s'est produite: " + ex.getClass().getSimpleName() +
                        (ex.getMessage() != null ? " - " + ex.getMessage() : ""),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Classes pour les réponses d'erreur (inchangées)
    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
        }

        // Getters
        public LocalDateTime getTimestamp() { return timestamp; }
        public int getStatus() { return status; }
        public String getError() { return error; }
        public String getMessage() { return message; }
        public String getPath() { return path; }
    }

    public static class ValidationErrorResponse extends ErrorResponse {
        private Map<String, String> validationErrors;

        public ValidationErrorResponse(LocalDateTime timestamp, int status, String error,
                                       String message, String path, Map<String, String> validationErrors) {
            super(timestamp, status, error, message, path);
            this.validationErrors = validationErrors;
        }

        public Map<String, String> getValidationErrors() { return validationErrors; }
    }
}