# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY . .

# Package the application
RUN ./mvnw clean install -DskipTests

# Run the application
CMD ["java", "-jar", "target/quizapp-0.0.1-SNAPSHOT.jar"]
