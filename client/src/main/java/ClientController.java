package main.java;


import static spark.Spark.*;

import spark.Request;
import spark.Response;
import spark.Route;

public class ClientController {

	public static void main(String[] args) {
		try {
			DataQuery dq = new DataQuery();
			get("/watchlist", (req, rest) -> dq.allMovies());
			
			get("/home", new Route() {
				@Override
				public Object handle(Request request, Response response) {
					return dq.allTvShows();
				}
			});
			
			dq.closeConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
