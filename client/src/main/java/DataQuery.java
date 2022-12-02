/**
 * 
 */

package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataQuery {
	static String url;
	static String user;
	static String password;
	static ResultSet rs;
	static Connection conn;
	static Statement stmt;
	
	DataQuery() {
		url = "jdbc:mysql://localhost:3306/moviedatabase";
		user  = "moviedbuser";
		password = "moviedbpassword";
		rs = null;
		conn = null;
		stmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//closes DB connection
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
	
	//create user
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
	//checks if user and password exists in database
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
	
	//return list of all movies
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
