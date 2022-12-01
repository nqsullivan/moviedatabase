package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.*;

public class DataQuery {
	static String url = "jdbc:mysql://localhost:3306/moviedatabase";
	static String user = "moviedbuser";
	static String password = "moviedbpassword";
	static ResultSet rs = null;
	static Connection conn = null;
	static Statement stmt;
	
	public static void watchlist(String userEmail) {
		try {
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.createStatement();
			String query = "select ReleaseDate, Title from watch_list where UserEmail = '" + userEmail + "'";
			
			rs = stmt.executeQuery(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null) 	conn.close();
				if(stmt != null) 	stmt.close();
				if(rs != null) 		rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		watchlist("SampleEmail1@sample.com");
	}
}
