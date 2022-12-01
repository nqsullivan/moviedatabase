## Setup
1. Clone the repository
2. cd into the `client` directory
3. Run `npm install` to install dependencies
4. Run `npm start` to start the development server


## Setup mysql database
1. Install mysql
2. Create a database named `moviedatabase`
3. Create a user named `moviedbuser` with password `moviedbpassword`
4. Grant all privileges on `moviedatabase` to `moviedbuser`
5. Run `mysql -u moviedbuser -p moviedatabase < moviedatabase.sql` to create the tables and populate them with data

##Additional Dependencies
+ [Maven](https://maven.apache.org/download.cgi) installation
+ Java 11 or higher


