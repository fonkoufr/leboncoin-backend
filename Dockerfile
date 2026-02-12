# Image stable et certifiée pour Docker
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copie du JAR (Assure-toi que le fichier est bien présent dans /target)
COPY target/leboncoin-api-0.0.1-SNAPSHOT.jar app.jar

# Copie du dossier wallet (Vérifie qu'il est bien à la racine de ton projet)
COPY wallet /app/wallet

EXPOSE 8080

# Lancement de l'application
ENTRYPOINT ["java", "-jar", "app.jar"]