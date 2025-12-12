# Produit API - Spring Boot CRUD Application

API REST complète pour la gestion des produits avec base de données H2.

## Technologies

- **Spring Boot 3.2.1**
- **Java 17**
- **Maven**
- **H2 Database** (in-memory)
- **Spring Data JPA**
- **MapStruct** (mapping DTO/Entity)
- **Lombok**
- **Springdoc OpenAPI** (Swagger UI)

## Structure du Projet

```
src/main/java/com/example/produit/
├── controller/          # REST Controllers
│   └── ProduitController.java
├── dto/                 # Data Transfer Objects
│   ├── ProduitRequest.java
│   └── ProduitResponse.java
├── entity/              # JPA Entities
│   └── Produit.java
├── exception/           # Exception Handling
│   ├── BadRequestException.java
│   ├── ResourceNotFoundException.java
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java
├── mapper/              # MapStruct Mappers
│   └── ProduitMapper.java
├── repository/          # JPA Repositories
│   └── ProduitRepository.java
├── service/             # Business Logic
│   ├── ProduitService.java
│   └── impl/
│       └── ProduitServiceImpl.java
└── ProduitApplication.java  # Main Application
```

## Configuration

### Base de données H2

La configuration H2 est définie dans `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:produitdb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Démarrage

### 1. Compiler le projet

```bash
mvn clean install
```

### 2. Lancer l'application

```bash
mvn spring-boot:run
```

L'application démarre sur **http://localhost:8080**

## Accès aux Interfaces

### Swagger UI
- URL: http://localhost:8080/swagger-ui.html
- Documentation interactive de l'API avec possibilité de tester les endpoints

### Console H2
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:produitdb`
- Username: `sa`
- Password: (laisser vide)

## Endpoints API

Base URL: `/api/produits`

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `POST` | `/api/produits` | Créer un nouveau produit |
| `PUT` | `/api/produits/{id}` | Mettre à jour un produit |
| `GET` | `/api/produits/{id}` | Récupérer un produit par ID |
| `GET` | `/api/produits` | Lister tous les produits |
| `GET` | `/api/produits/paginated?page=0&size=10` | Lister avec pagination |
| `GET` | `/api/produits/search?nom=laptop` | Rechercher par nom |
| `DELETE` | `/api/produits/{id}` | Supprimer un produit |

## Exemples de Requêtes

### Créer un produit

```bash
curl -X POST http://localhost:8080/api/produits \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Ordinateur portable Dell",
    "description": "Ordinateur portable haute performance",
    "prix": 1299.99,
    "quantite": 50
  }'
```

### Récupérer tous les produits

```bash
curl http://localhost:8080/api/produits
```

### Rechercher par nom

```bash
curl http://localhost:8080/api/produits/search?nom=Dell
```

### Mettre à jour un produit

```bash
curl -X PUT http://localhost:8080/api/produits/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Ordinateur portable Dell XPS",
    "description": "Ordinateur portable ultra-performance",
    "prix": 1499.99,
    "quantite": 30
  }'
```

### Supprimer un produit

```bash
curl -X DELETE http://localhost:8080/api/produits/1
```

## Modèle de Données

### Produit Entity

| Champ | Type | Contraintes |
|-------|------|-------------|
| `id` | Long | Auto-généré, PK |
| `nom` | String | Requis, 3-100 caractères |
| `description` | String | Optionnel, max 255 caractères |
| `prix` | Double | Requis, positif |
| `quantite` | Integer | Requis, >= 0 |
| `dateCreation` | LocalDate | Auto-généré (@PrePersist) |

## Gestion des Exceptions

L'application utilise un gestionnaire global d'exceptions (`@RestControllerAdvice`) qui retourne des réponses JSON structurées:

```json
{
  "timestamp": "2025-12-12T15:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Produit non trouvé avec id : '1'",
  "path": "/api/produits/1"
}
```

### Types d'exceptions

- **ResourceNotFoundException** (404): Ressource non trouvée
- **BadRequestException** (400): Requête invalide
- **MethodArgumentNotValidException** (400): Erreurs de validation
- **Exception** (500): Erreur serveur interne

## Tests

Utilisez **Swagger UI** pour tester interactivement tous les endpoints de l'API.

## License

Apache 2.0
