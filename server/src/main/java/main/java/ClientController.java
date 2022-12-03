package main.java;

import spark.Filter;

import static spark.Spark.*;

public class ClientController {

    public static void main(String[] args) {

        port(8080);

        DataQuery dq = new DataQuery();

        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        get("/watchlistMovie/:userid", (req, res) -> {
            System.out.println("Getting the movie watchlist for user: " + req.params(":userid"));
            String watchList = dq.getWatchlistMovie(req.params(":userid"));
            res.status(200);
            return watchList;
        });
        
        get("/watchlistTV/:userid", (req, res) -> {
            System.out.println("Getting the TV show watchlist for user: " + req.params(":userid"));
            String watchList = dq.getWatchlistTV(req.params(":userid"));
            res.status(200);
            return watchList;
        });
        
        get("addToWatchlist/:userid/:title/:releasedate", (req, res) -> {
            System.out.println("Adding " + req.params(":title") + " to " + req.params(":userid") + "'s watchlist");
        	boolean added = dq.addToWatchlist(req.params(":userid"), req.params(":title"), req.params(":releasedate"));
        	if(added) 	
        		res.status(200);
        	else		
        		res.status(400);
        	
        	return added;
        });
        
        get("removeFromWatchlist/:userid/:title/:releasedate", (req, res) -> {
            System.out.println("Removing " + req.params(":title") + " from " + req.params(":userid") + "'s watchlist");
        	boolean removed = dq.removeFromWatchlist(req.params(":userid"), req.params(":title"), req.params(":releasedate"));
        	if(removed)	
        		res.status(200);
        	else		
        		res.status(400);
        	
        	return removed;
        });

        get("/tv-shows", (req, res) -> {
            System.out.println("Getting all tv shows");
            String tvshows = dq.getAllTvShows();
            res.status(200);
            
            return tvshows;
        });

        get("/movies", (req, res) -> {
            System.out.println("Getting all movies");
            String movies = dq.getAllMovies();
            res.status(200);
            
            return movies;
        });

        get("createUser/:userid/:password", (req, res) -> {
            System.out.println("Creating user: " + req.params(":userid"));
            System.out.println("Creating user with id: " + req.params(":userid") + " and password: " + req.params(":password"));
            boolean created = dq.createUser(req.params(":userid"), req.params(":password"));

            if(created) 
                res.status(200);
            else 
                res.status(400);

            return created;
        });

        get("validateUser/:userid/:password", (req, res) -> {
            System.out.println("Validating user: " + req.params(":userid"));
            System.out.println("Validating user: " + req.params(":userid") + " with password: " + req.params(":password"));
            boolean isValid = dq.validateUser(req.params(":userid"), req.params(":password"));
            res.status(200);
            return isValid;
        });
    }
}
