My simple application just to try Github Actions features

## How to run locally without Docker
1. Install JDK 21 and set correctly JAVA_HOME to that JDK
2. Set up a MySQL database with a specific database name, user and password.
3. Copy the `setEnv` file removing the ".fake" suffix with the following commands:

   On Linux
   ```
   cd local/scripts
   cp setEnv.sh.fake setEnv.sh
   ```
   On Windows
   ```
   cd local/scripts
   copy setEnv.bat.fake setEnv.bat
   ```
4. Set database related variables according to your database with the following commands::
 
   On Linux (example if you want to set `MYSQLUSER`, `MYSQLPASSWORD` and `MYSQLDATABASE` as values)
   ```
   sed -i 's/<<set mysql name variable value>>/MYSQLUSER/g' setEnv.sh
   sed -i 's/<<set mysql password variable value>>/MYSQLPASSWORD/g' setEnv.sh
   sed -i 's/<<set mysql database variable value>>/MYSQLDATABASE/g' setEnv.sh
   ```
   On Windows (example if you want to set `MYSQLUSER`, `MYSQLPASSWORD` and `MYSQLDATABASE` as values)
   ```
   powershell -Command "(Get-Content setEnv.bat) -replace '<<set mysql name variable value>>', 'MYSQLUSER' | Set-Content setEnv.bat"
   powershell -Command "(Get-Content setEnv.bat) -replace '<<set mysql password variable value>>', 'MYSQLPASSWORD' | Set-Content setEnv.bat"
   powershell -Command "(Get-Content setEnv.bat) -replace '<<set mysql database variable value>>', 'MYSQLDATABASE' | Set-Content setEnv.bat"
   ```
5. Run the script to actually set those variable values then go back to the root with the following command
 
   On Linux
   ```
   source setEnv.sh
   cd ../..
   ``` 
   On Windows
   ```
   setEnv.bat
   cd ../..
   ```
6. Run the SpringBoot application using the local profile

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
7. Run the Angular application
   ```
   cd frontend
   npm run serve:local
   ```
8. In a browser access localhost:4300/browser


## How to run locally with Docker

To run the application locally with Docker Compose, you need to set up the required MySQL environment variables:

1. Copy the `.env.fake` removing the ".fake" suffix with the following commands:

   On Linux
   ```
   cd docker
   cp .env.fake .env
   ```
   On Windows
   ```
   cd docker
   copy .env.fake .env
   ```

2. Set database related variables according to your database with the following commands::

   On Linux (example if you want to set `MYSQL_ROOT_PASSWORD`, `MYSQLUSER`, `MYSQLPASSWORD` and `MYSQLDATABASE` as values)
   ```
   sed -i 's/<<set mysql root passoword variable value>>/MYSQL_ROOT_PASSWORD/g' .env
   sed -i 's/<<set mysql name variable value>>/MYSQLUSER/g' .env
   sed -i 's/<<set mysql password variable value>>/MYSQLPASSWORD/g' .env
   sed -i 's/<<set mysql database variable value>>/MYSQLDATABASE/g' .env
   ```
   On Windows (example if you want to set `MYSQL_ROOT_PASSWORD`, `MYSQLUSER`, `MYSQLPASSWORD` and `MYSQLDATABASE` as values)
   ```
   powershell -Command "(Get-Content .env) -replace '<<set mysql root password variable value>>', 'MYSQL_ROOT_PASSWORD' | Set-Content .env"
   powershell -Command "(Get-Content .env) -replace '<<set mysql name variable value>>', 'MYSQLUSER' | Set-Content .env"
   powershell -Command "(Get-Content .env) -replace '<<set mysql password variable value>>', 'MYSQLPASSWORD' | Set-Content .env"
   powershell -Command "(Get-Content .env) -replace '<<set mysql database variable value>>', 'MYSQLDATABASE' | Set-Content .env"
   ```

3. Run the docker compose
   ```
   docker-compose up -d
   ```
4. In a browser access localhost:4300/browser
