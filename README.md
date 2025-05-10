My simple application just to try Github Actions features

## How to run locally without Docker
1. Install JDK 21 and set correctly JAVA_HOME to that JDK
2. Set up a MySQL database with a specific database name, user and password.
3. Copy the setEnv removing the ".fake" suffix with the following commands:

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
 
   On Linux (example if you want to set MYSQLUSER, MYSQLPASSWORD and MYSQLDATABASE as values)
   ```
   sed -i 's/<<set mysql name variable value>>/MYSQLUSER/g' setEnv.sh
   sed -i 's/<<set mysql password variable value>>/MYSQLPASSWORD/g' setEnv.sh
   sed -i 's/<<set mysql database variable value>>/MYSQLDATABASE/g' setEnv.sh
   ```
   On Windows (example if you want to set MYSQLUSER, MYSQLPASSWORD and MYSQLDATABASE as values)
   ```
   powershell -Command "(Get-Content setEnv.bat) -replace '<<set mysql name variable value>>', 'MYSQLUSER' | Set-Content setEnv.bat"
   powershell -Command "(Get-Content setEnv.bat) -replace '<<set mysql password variable value>>', 'MYSQLPASSWORD' | Set-Content setEnv.bat"
   powershell -Command "(Get-Content setEnv.bat) -replace '<<set mysql database variable value>>', 'MYSQLDATABASE' | Set-Content setEnv.bat"
   ```
7. Run the script to actually set those variable values then go back to the root with the following command
 
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
4. Run the Angular application
   ```
   cd frontend
   npm run serve:local
   ```
5. In a browser access localhost:4300





# Local Development Setup

## Environment Variables

To run the application locally with Docker Compose, you need to set up the required MySQL environment variables:

1. Create a `.env` file in the root directory of the project:
```shell script
touch .env
```


2. Add the following variables to your `.env` file:
```
MYSQL_ROOT_PASSWORD=your_root_password
   MYSQL_USER=your_username
   MYSQL_PASSWORD=your_password
   MYSQL_DATABASE=your_database_name
```


3. Replace the placeholder values with your desired configuration:
   - `MYSQL_ROOT_PASSWORD`: Password for the MySQL root user
   - `MYSQL_USER`: Username for the application database user
   - `MYSQL_PASSWORD`: Password for the application database user
   - `MYSQL_DATABASE`: Name of the database to be created

> **Important**: The `.env` file contains sensitive information and is excluded from version control. Never commit this file to the repository.

After creating your `.env` file, you can start the application with:
```shell script
docker-compose up -d
```


## Example Values for Local Development

For local development, you might use something like:
```
MYSQL_ROOT_PASSWORD=rootpass
MYSQL_USER=devuser
MYSQL_PASSWORD=devpass
MYSQL_DATABASE=appdb
```
