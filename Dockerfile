# Use an official JDK image
FROM openjdk:21

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy rest of the code
COPY src src

# Package the app
RUN ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/quizapp-0.0.1-SNAPSHOT.jar"]
