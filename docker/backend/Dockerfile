FROM eclipse-temurin:21-jre

# Environment variables
ENV DB_SCHEMA=mysqlappdb \
    DATABASE_PLATFORM=org.hibernate.dialect.MySQLDialect \
    DB_URL=jdbc:mysql://mysql-db:3306/mysqlappdb?allowPublicKeyRetrieval=true&useSSL=false \
    DB_USR=mysqluser \
    DB_PWD=mysqlpassword \
    DB_DRIVER=com.mysql.cj.jdbc.Driver

# Copy initialization jar
WORKDIR /app
COPY backend/target/*.jar app.jar

# Run the application when container starts
ENTRYPOINT ["java", "-jar", "app.jar"]

# Add healthcheck (e.g., checking HTTP 200 on /actuator/health)
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1
