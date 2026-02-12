FROM amazoncorretto:17-alpine
WORKDIR /app

# Copie du JAR
COPY target/leboncoin-api-0.0.1-SNAPSHOT.jar app.jar

# ✅ Correction ici : on utilise ./wallet pour être sûr du chemin relatif
COPY ./wallet /app/wallet

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]