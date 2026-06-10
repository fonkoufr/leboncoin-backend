# LeBonCoin API

API REST Spring Boot pour la plateforme LeBonCoin.

## 📋 Stack Technologique

- **Spring Boot 3.2.2** - Framework Java
- **Java 17** - JDK
- **Oracle Database** - Base de données
- **Spring Security** - Authentification & Autorisation
- **Spring Data JPA** - ORM
- **SpringDoc OpenAPI** - Documentation API Swagger
- **Maven** - Build tool

## 🚀 Démarrage Rapide

### Prérequis
- Java 17+ (Amazon Corretto ou OpenJDK)
- Maven 3.8+
- Oracle DB (ou déploiement cloud avec wallet)

### Installation

1. **Configurez la base de données** (voir `application.properties`)
```properties
spring.datasource.url=jdbc:oracle:thin:@ok9s903rhpadr9pi_high?TNS_ADMIN=C:/Projets/Wallet_Leboncoin/...
spring.datasource.username=ADMIN
spring.datasource.password=****
```

2. **Build & Démarrage**
```bash
mvn clean install
mvn spring-boot:run
```

3. **Vérifiez que l'API démarre**
```bash
curl http://localhost:8080/v3/api-docs
```

## 📚 Documentation API (Swagger)

Accédez à **http://localhost:8080/swagger-ui.html**

Contient:
- ✅ Tous les endpoints REST
- ✅ Paramètres & réponses (schémas JSON)
- ✅ Try-it-out (tester directement)
- ✅ Codes HTTP attendus

## 📁 Structure du Projet

```
src/main/java/com/monapp/api/
├── config/              # Configurations (Swagger, CORS, Security)
│   ├── OpenApiConfig.java
│   ├── CorsConfig.java
│   └── SecurityConfig.java (si existe)
├── controller/          # Endpoints REST
├── service/             # Logique métier
├── repository/          # Accès données (JPA)
├── model/               # Entités JPA
└── RestApplication.java # Point d'entrée

resources/
└── application.properties # Configuration Spring Boot
```

## 🔗 API Endpoints

### Format Réponses
Toutes les réponses sont en **JSON**

```javascript
// Success (200)
{
  "success": true,
  "data": {...}
}

// Error (4xx/5xx)
{
  "success": false,
  "error": "Message d'erreur",
  "code": "ERROR_CODE"
}
```

### CORS Configuration
- Origins autorisées: `http://localhost:5173` (frontend dev)
- Méthodes: GET, POST, PUT, DELETE, PATCH, OPTIONS
- Headers: *
- Credentials: true

## 🛡️ Sécurité

- ✅ CORS configuré
- ✅ Spring Security intégré
- ✅ Input validation (Bean Validation)
- ✅ SQL Injection prevention (JPA parameterized)

### À ajouter:
- [ ] Rate limiting
- [ ] JWT authentication
- [ ] HTTPS enforcement
- [ ] OWASP security headers

## 🧪 Tests

```bash
mvn test                    # Exécute les tests
mvn test -Dtest=NomTest    # Test spécifique
```

## 📦 Build & Deploy

### Build WAR/JAR
```bash
mvn clean package
```

### Docker Deployment
```bash
docker build -t leboncoin-api:latest .
docker run -p 8080:8080 leboncoin-api:latest
```

### Production Configuration
Créez `application-prod.properties`:
```properties
spring.profiles.active=prod
server.port=8080
logging.level.root=INFO
```

## 📊 Monitoring

### Endpoints de Health
```bash
curl http://localhost:8080/actuator/health
curl http://localhost:8080/v3/api-docs
```

### Logs
Les logs sont affichés en console et peuvent être configurés via `application.properties`

## 🔄 Integration Frontend

### Base URL
```
http://localhost:8080/api
```

### Exemple Fetch
```javascript
async function getAnnonces() {
  const response = await fetch('http://localhost:8080/api/annonces')
  return response.json()
}
```

## 🚨 Ressources

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI](https://springdoc.org/)
- [Oracle JDBC Driver](https://www.oracle.com/database/technologies/appdev/jdbc.html)

## 🐛 Troubleshooting

### "Connection refused" (BD)
Vérifiez `spring.datasource.url` et wallet Oracle

### Swagger UI ne charge pas
Vérifiez que `springdoc-openapi` dépendance est bien ajoutée

### CORS error depuis frontend
Vérifiez `CorsConfig.java` et origins configurés

---

**Dernière mise à jour**: 2026-06-10 | Mainteneur: Équipe Dev
