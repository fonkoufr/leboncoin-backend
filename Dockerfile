FROM openjdk:17-jdk-slim
WORKDIR /app
# Copie du JAR généré par Maven
COPY target/leboncoin-api-0.0.1-SNAPSHOT.jar app.jar
# Copie du dossier wallet (qui est maintenant bien placé !)
COPY wallet /app/wallet
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]