FROM openjdk:21
WORKDIR /app
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/quizapp-0.0.1-SNAPSHOT.jar"]
# test for commit hayd