My simple application just to try Github Actions features

## How to run locally without Docker
1. Install JDK 21 and set correctly JAVA_HOME to that JDK
2. Set up a MySQL database with a database, user and password called as defined in application-local.properties (if necessary change what is in the properties file to be coherent with your local database).
3. Run the SpringBoot application using the local profile

   On Linux
   ```
   cd backend
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
   ```
   On Windows
   ```
   cd backend
   mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
   ```
4. Run the Angular application TODO
   ```
   cd frontend
   npm run build:prod
   npm run build:dev
   npm run build:local
   ng build --configuration local
   ng serve
   ```
