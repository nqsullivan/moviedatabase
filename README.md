## Setup mysql database
1. Install mysql 
2. Run `mysql -u <db_user> < sql/moviedatabase.sql` to create the database, tables, and populate them with data

## Setup config file
1. Go to `server/config.properties`
2. Change `DB_URL` to your database url (e.g. `jdbc:mysql://localhost:3306/moviedatabase`)
3. Change `DB_USER` to your database user (e.g. `root`)
4. Change `DB_PASS` to your database password (e.g. `password`)

## Setup Backend
1. Install maven 
2. cd into the `server` directory 
3. Run `mvn clean install` to install all dependencies 
4. Run `java -jar target/moviedatabase-0.0.1-SNAPSHOT.jar;` to start the server

## Setup Client
1. cd into the `client` directory 
2. Run `npm install` to install dependencies 
3. Run `npm start` to start the development server

## Dependencies
+ [NPM](https://www.npmjs.com/)
+ [Maven](https://maven.apache.org/download.cgi) installation
+ Java 8 or higher


