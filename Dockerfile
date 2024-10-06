FROM openjdk:17-jdk-alpine


WORKDIR /app


COPY target/product-management-0.0.1-SNAPSHOT.jar /app/product-management.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jarå", "/app/product-management.jar"]