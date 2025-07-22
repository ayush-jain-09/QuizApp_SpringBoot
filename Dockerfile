FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean install -DskipTests

# Expose the port your app runs on (default Spring Boot port)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/quizapp-0.0.1-SNAPSHOT.jar"]
