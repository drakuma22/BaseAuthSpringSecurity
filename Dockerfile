# Base image con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Imposta la working dir
WORKDIR /app

# Copia il jar
COPY ./target/*.jar app.jar

# Espone la porta 8080
EXPOSE 8080

# Comando per avviare l'app
ENTRYPOINT ["java", "-jar", "app.jar"]