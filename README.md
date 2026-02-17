AegisCard API

AegisCard API is a secure card registration and lookup system built with Java Spring Boot, following Clean Architecture and CQRS principles.

It uses JWT authentication, encrypts card numbers with AES, and stores user passwords with BCrypt. The system also leverages Virtual Threads (Java 21) for efficient concurrency.



🚀 Running Locally (Develop Branch)

Prerequisites

Java 21+



Maven



Steps

Clone the repository and switch to the develop branch:



bash

git clone https://github.com/tiagobalduino8/aegiscard.git

cd aegiscard

git checkout develop

Build the project:



bash

./mvnw clean package

Run the application:



bash

./mvnw spring-boot:run

or run the JAR:



bash

java -jar target/card-api.jar

The API will be available at:



Código

http://localhost:8080

Database (Local Dev)

Uses H2 in-memory database by default.



Access the H2 console at:



Código

http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:aegiscarddb



User: sa



Password: (empty)



🐳 Running in Production with Docker

Prerequisites

Docker



Docker Compose



Steps

Build the JAR:



bash

./mvnw clean package

Build and start containers:



bash

docker-compose up --build

Services:



MySQL DB → localhost:3306



AegisCard App → localhost:8080



Environment

Profile: prod



Database: MySQL (configured in docker-compose.yml)



🔑 Authentication

The API requires JWT authentication.

A default user is already created in the database:



Username: admin



Password: admin



Obtain Token

http

POST http://localhost:8080/auth/login

Content-Type: application/json



{

&nbsp; "username": "admin",

&nbsp; "password": "admin"

}

Response:



json

{

&nbsp; "token": "eyJhbGciOiJIUzI1NiIsInR..."

}

Use this token in all requests by adding the header:



Código

Authorization: Bearer <token>

📌 Endpoints

Insert Card

http

POST /cards

Authorization: Bearer <token>

Content-Type: application/json



{

&nbsp; "number": "1234567890123456"

}

Find Card

http

GET /cards/1234567890123456

Authorization: Bearer <token>

Upload Cards (TXT file)

http

POST /cards/upload

Authorization: Bearer <token>

Content-Type: multipart/form-data

file=@cards.txt

📝 Logs

All requests and responses are logged, including method, endpoint, status, duration, and payload.



⚡ Architecture Highlights

Clean Architecture: separation of concerns across application, domain, infrastructure, and interface layers.



CQRS: commands (InsertCardCommand) and queries (FindCardQuery) are handled separately for clarity and scalability.



Virtual Threads: configured with Executors.newVirtualThreadPerTaskExecutor() to optimize concurrency in IO-bound operations.





