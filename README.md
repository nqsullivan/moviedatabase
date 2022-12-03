## Setup mysql database
1. Install mysql
2. Create a database named `moviedatabase`
3. Create a user named `moviedbuser` with password `moviedbpassword`
4. Grant all privileges on `moviedatabase` to `moviedbuser`
5. Run `mysql -u <movie db user> -p <movie database name> < moviedatabase.sql` to create the tables and populate them with data

## Setup config file
1. Go to `src/server/config.properties`
2. Change `DB_URL` to your database url (e.g. `jdbc:mysql://localhost:3306/moviedatabase`)
3. Change `DB_USER` to your database user (e.g. `root`)
4. Change `DB_PASSWORD` to your database password (e.g. `password`)

## Setup Backend
1. Clone the repository
2. Install maven 
3. cd into the `server` directory 
4. Run `mvn clean install` to install all dependencies 
5. Run `java -jar target/moviedatabase-0.0.1-SNAPSHOT.jar;` to start the server

## Setup Client
1. Clone the repository
2. cd into the `client` directory
3. Run `npm install` to install dependencies
4. Run `npm start` to start the development server

## Dependencies
+ [NPM](https://www.npmjs.com/)
+ [Maven](https://maven.apache.org/download.cgi) installation
+ Java 8 or higher


