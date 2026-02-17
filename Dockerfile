FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/card-api.jar card-api.jar
ENTRYPOINT ["java", "-jar", "card-api.jar"]
