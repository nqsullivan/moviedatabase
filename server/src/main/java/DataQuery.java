package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates connection to moviedatabase and provides methods to make
 * predefined sql queries
 * 
 * @author J. Scotty Solomon
 */

public class DataQuery {
	static String url;
	static String user;
	static String password;
	static ResultSet rs;
	static Connection conn;
	static Statement stmt;
	
	DataQuery() {

		// Get database credentials from config.properties
		Properties prop = readPropertiesFile("config.properties");
		url = prop.getProperty("DB_URL");
		user = prop.getProperty("DB_USER");
		password = prop.getProperty("DB_PASS");

		assert url != null;
		assert user != null;
		assert password != null;

		rs = null;
		conn = null;
		stmt = null;
		
		try {
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Properties readPropertiesFile(String fileName) {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(fileName)) {
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}
	
	//Closes database connection
	public void closeConnection() {
		try {
			if(conn != null)	conn.close();
			if(stmt != null) 	stmt.close();
			if(rs != null)		rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Returns true if new user was created with inputed email and password
	public boolean createUser(String userEmail, String password) {
		try {
			stmt = conn.createStatement();
			String query = "INSERT INTO USER (`Email`, `Password`) VALUES ('" + userEmail + 
					"', '" + password + "');";
			
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	//Checks if specified email and password exist as a key value in the database
	public boolean validateUser(String userEmail, String password) {
		Boolean ret = false;
		try {
			stmt = conn.createStatement();
			
			String query = "select email, password from user where email = '" + userEmail +
					"' and password = '" + password + "';";
			
			rs = stmt.executeQuery(query);
			
			ret = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return ret;
	}
	
	//Returns Json string containing data for all movies in the database
	public String allMovies(){
		try {
			stmt = conn.createStatement();
			
			String query = "select grossrevenue, releasetype, releasedate, title from movie";
			rs = stmt.executeQuery(query);
			
			return JsonCreator.toJson(rs).toString();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Returns Json string containing data for all TV shows in the database
	public String allTvShows(){
		try {
			stmt = conn.createStatement();
			
			String query = "select numseasons, numepisodes, releasedate, title from tv_show";
			rs = stmt.executeQuery(query);
			
			return JsonCreator.toJson(rs).toString();						
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	//Returns Json string containing all saved media within a specified user's watch-list
	public String watchlist(String userEmail) {
		try {
			stmt = conn.createStatement();
			String query = "select ReleaseDate, Title from watch_list where UserEmail = '" + userEmail + "'";
			rs = stmt.executeQuery(query);
			
			return JsonCreator.toJson(rs).toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	//Returns a Json string of all movies offered by a specified platform
	public String platformOffers(String pName) {
		try {
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.createStatement();
			String query = "select ReleaseDate, Title from offers, movie where platformname = '" + pName + 
					"' and offers.title = movie.title;";
			
			rs = stmt.executeQuery(query);
			
			return JsonCreator.toJson(rs).toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		DataQuery server = new DataQuery();
		System.out.println(server.allMovies());
		System.out.println(server.allTvShows());
		server.closeConnection();		
	}
}
