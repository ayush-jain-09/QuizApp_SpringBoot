FROM openjdk:21
WORKDIR /app

# Copy Maven wrapper files
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# ✅ Give execute permission to the Maven wrapper
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/quizapp-0.0.1-SNAPSHOT.jar"]
