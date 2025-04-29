FROM eclipse-temurin:21-jre
WORKDIR /app
COPY backend/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
