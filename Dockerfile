# Use Eclipse Temurin JDK base image (Java 17 or 21)
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy your Spring Boot JAR to the container
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]