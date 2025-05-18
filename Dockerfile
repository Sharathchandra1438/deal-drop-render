# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=build /app/target/*.jar ecom.jar

EXPOSE 8080
CMD ["java", "-jar", "ecom.jar"]
