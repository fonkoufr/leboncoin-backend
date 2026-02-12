# Utilisation d'une image stable et légère
FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /app

# Copie du JAR (Assure-toi que le nom du fichier dans target est bien celui-là)
COPY target/leboncoin-api-0.0.1-SNAPSHOT.jar app.jar

# Copie du wallet Oracle
COPY wallet /app/wallet

EXPOSE 8080

# Lancement avec les paramètres de sécurité Oracle
ENTRYPOINT ["java", "-jar", "app.jar"]