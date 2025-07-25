
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven dependencies first (for caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY . .

# Package the application
RUN ./mvnw clean package -DskipTests

# Expose port (change if needed)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "target/codeexplainer-0.0.1-SNAPSHOT.jar"]
