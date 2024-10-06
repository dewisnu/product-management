# Stage 1: Build the project using Maven
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

# Stage 2: Run the application using OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/product-management.jar /app/product-management.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/product-management.jar"]