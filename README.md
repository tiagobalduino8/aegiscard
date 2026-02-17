# AegisCard API

**AegisCard API** is a secure card registration and lookup system built with **Java Spring Boot**, following **Clean Architecture** and **CQRS** principles.  
It uses **JWT authentication**, encrypts card numbers with **AES**, and stores user passwords with **BCrypt**. The system also leverages **Virtual Threads (Java 21)** for efficient concurrency.

---

## 🚀 Running Locally (Develop Branch)

### Prerequisites
- Java 21+
- Maven

### Steps
1. Clone the repository and switch to the `develop` branch:
   ```bash
   git clone https://github.com/tiagobalduino8/aegiscard.git
   cd aegiscard
   git checkout develop

./mvnw clean package

./mvnw spring-boot:run

java -jar target/card-api.jar


## 🐳 Running in Production with Docker
### Prerequisites
- Docker
- Docker Compose

### Steps
Build the JAR:

./mvnw clean package

#### Build and start containers:
docker-compose up --build

#### Services:
MySQL DB → localhost:3306 

AegisCard App → localhost:8080

## Authentication

The API requires JWT authentication.
A default user is already created in the database:

Username: admin

Password: senhaSecreta123

### Obtain Token

POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "senhaSecreta123"
}

Response:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}

 ### Use this token in all requests by adding the header:

 Authorization: Bearer <token>

### Endpoints

### Insert Card: 

POST /cards
Authorization: Bearer <token>
Content-Type: application/json

{
  "number": "1234567890123456"
}

### Find Card
GET /cards/1234567890123456
Authorization: Bearer <token>

### Upload Cards (TXT file)

POST /cards/upload

Authorization: Bearer <token>

Content-Type: multipart/form-data

file=@cards.txt

