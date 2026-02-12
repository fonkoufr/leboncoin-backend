# On change l'image de base pour une version maintenue
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# On garde le reste identique
COPY target/leboncoin-api-0.0.1-SNAPSHOT.jar app.jar
COPY wallet /app/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]