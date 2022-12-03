package main.java;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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
	
	public DataQuery() {

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
		Path path = Paths.get(fileName);
		try (InputStream input = Files.newInputStream(path)) {
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

			if(rs.next()) {
				ret = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return ret;
	}
	
	//Returns Json string containing data for all movies in the database
	public String getAllMovies(){
		try {
			stmt = conn.createStatement();
			
			String query = "select grossrevenue, releasetype, media.releasedate, media.title, rating from movie NATURAL JOIN Media WHERE movie.releasedate = media.releasedate and movie.title = media.title;";
			rs = stmt.executeQuery(query);

			return JsonCreator.toJson(rs).toString();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Returns Json string containing data for all TV shows in the database
	public String getAllTvShows(){
		try {
			stmt = conn.createStatement();
			
			String query = "select numseasons, numepisodes, media.releasedate, media.title, rating from tv_show NATURAL JOIN Media WHERE tv_show.releasedate = media.releasedate and tv_show.title = media.title;";
			rs = stmt.executeQuery(query);
			
			return JsonCreator.toJson(rs).toString();						
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public boolean addToWatchlist(String userEmail, String title, String releaseDate) {
		try {
			stmt = conn.createStatement();
			String query = "INSERT INTO WATCH_LIST (`UserEmail`, `ReleaseDate`, `Title`) VALUES ('" + userEmail + 
					"', '" + releaseDate + "', '" + title + "');";
			
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean removeFromWatchlist(String userEmail, String title, String releaseDate) {
		try {
			stmt = conn.createStatement();
			String query = "delete from watch_list where userEmail = '" + userEmail + "' and title = '" + title + "' and releasedate = '" +
					releaseDate + "';";
			
			stmt.execute(query);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	//Returns Json string containing all saved media within a specified user's watch-list
	public String getWatchlistTV(String userEmail) {
		try {
			stmt = conn.createStatement();
			String query = "select numseasons, numepisodes, rating, watch_list.releasedate, watch_list.title from tv_show, watch_list NATURAL JOIN Media WHERE tv_show.releasedate = watch_list.releasedate and tv_show.title = watch_list.title and watch_list.useremail = '" +
			userEmail + "';";
			rs = stmt.executeQuery(query);
			
			return JsonCreator.toJson(rs).toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public String getWatchlistMovie(String userEmail) {
		try {
			stmt = conn.createStatement();
			String query = "select grossrevenue, releasetype, watch_list.releasedate, watch_list.title, rating from movie, watch_list NATURAL JOIN Media WHERE movie.releasedate = watch_list.releasedate and movie.title = watch_list.title and watch_list.useremail = '" +
			userEmail + "';";
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
		System.out.println(server.getWatchlistMovie("sampleemail1@sample.com"));
		System.out.println(server.addToWatchlist("sampleemail1@sample.com", "The Two Popes", "2019-01-25"));
		System.out.println(server.getWatchlistTV("sampleemail1@sample.com"));
		System.out.println(server.removeFromWatchlist("sampleemail1@sample.com", "Frozen II", "2019-03-05"));
		server.closeConnection();		
	}
}
