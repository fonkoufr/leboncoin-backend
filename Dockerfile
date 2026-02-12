# On utilise Amazon Corretto, une version très stable de Java 17
FROM amazoncorretto:17-alpine

WORKDIR /app

# On copie le JAR (vérifie bien le nom dans ton dossier target)
COPY target/leboncoin-api-0.0.1-SNAPSHOT.jar app.jar

# On copie le wallet
COPY wallet /app/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]