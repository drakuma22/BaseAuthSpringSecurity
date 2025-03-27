# 🔐 Base JWT Security Project

Questo progetto fornisce una base solida e moderna per gestire autenticazione e autorizzazione in Java con Spring Boot 3 e JWT.  
Pensato per architetture modulari o microservizi.

## 🚀 Tecnologie utilizzate

- ✅ Spring Boot 3.2+
- ✅ Spring Security 6
- ✅ JWT (JJWT 0.11.5)
- ✅ JPA + PostgreSQL
- ✅ Lombok
- ✅ Maven

## 📦 Funzionalità

- ✨ Login e Registrazione
- 🔐 Generazione e validazione JWT
- 🎭 Supporto a ruoli (`USER`, `ADMIN`)
- 🛡️ Protezione degli endpoint
- 🧩 Gestione personalizzata degli errori:
  - `401 Unauthorized` → token assente/scaduto
  - `403 Forbidden` → utente non autorizzato
- 💬 Endpoint separati per utenti e admin

## 📥 Clonazione del progetto

git clone https://github.com/tuo-utente/Base-Jwt-Security-Project.git
cd Base-Jwt-Security-Project

## 🛠️ Configurazione ambiente

### ⚙️ Configura PostgreSQL (locale o container)

Assicurati di avere un database chiamato `authdb`:

CREATE DATABASE authdb;

### 📄 File application.yml

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/authdb
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.PostgreSQLDialect

## 🧪 Avvio dell'applicazione

### Esecuzione via Maven

./mvnw clean install -DskipTests
./mvnw spring-boot:run

## 🔐 Endpoint disponibili

### 📥 Registrazione utente

POST /auth/register

{
  "username": "mario",
  "password": "password123",
  "email": "mario@esempio.com",
  "role": "USER"
}

### 🔐 Login utente

POST /auth/login

{
  "username": "mario",
  "password": "password123"
}

📤 Risposta:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5..."
}

### 🧑‍💼 Endpoint utente protetto

GET /user/profile  
Authorization: Bearer {token}

### 👑 Endpoint solo admin

GET /admin/dashboard  
Authorization: Bearer {token}

## ⚠️ Gestione degli errori

| Caso                        | Codice | Messaggio                                 |
|-----------------------------|--------|-------------------------------------------|
| Token mancante/scaduto      | 401    | Autenticazione richiesta o token non valido. |
| Accesso negato per ruolo    | 403    | Accesso negato: non hai i permessi necessari. |

## 🐳 Esecuzione con Docker (opzionale)

### Esempio Dockerfile

FROM eclipse-temurin:17-jdk-alpine  
COPY target/*.jar app.jar  
ENTRYPOINT ["java", "-jar", "/app.jar"]

### Docker Compose (esempio)

auth-service:
  build: .
  ports:
    - "8080:8080"
  depends_on:
    - postgres

postgres:
  image: postgres:15
  environment:
    POSTGRES_DB: authdb
    POSTGRES_USER: postgres
    POSTGRES_PASSWORD: postgres
  ports:
    - "5433:5432"

## 📚 Suggerimenti

- ✅ Può essere usato come microservizio `auth-service` in architetture più grandi
- 💡 Può essere integrato con Spring Cloud Gateway o API Gateway
- 🔐 JWT generato con firma HMAC SHA256 (HS256)

## 👨‍💻 Autore

Creato da [Tuo Nome o Nickname]  
Progetto pensato per essere modulare, sicuro e pronto alla produzione.

## 📜 Licenza

Distribuito sotto licenza MIT.
