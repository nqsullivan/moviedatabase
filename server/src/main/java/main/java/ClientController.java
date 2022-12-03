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
            String watchList = dq.getWatchlistMovie(req.params(":userid"));
            res.status(200);
            return watchList;
        });
        
        get("/watchlistTV/:userid", (req, res) -> {
            String watchList = dq.getWatchlistTV(req.params(":userid"));
            res.status(200);
            return watchList;
        });
        
        get("addToWatchlist/:userid/:title/:releasedate", (req, res) -> {
        	boolean added = dq.addToWatchlist(req.params(":userid"), req.params(":title"), req.params(":releasedate"));
        	if(added) 	
        		res.status(200);
        	else		
        		res.status(400);
        	
        	return added;
        });
        
        get("removeFromWatchlist/:userid/:title/:releasedate", (req, res) -> {
        	boolean removed = dq.removeFromWatchlist(req.params(":userid"), req.params(":title"), req.params(":releasedate"));
        	if(removed)	
        		res.status(200);
        	else		
        		res.status(400);
        	
        	return removed;
        });

        get("/tv-shows", (req, res) -> {
            String tvshows = dq.getAllTvShows();
            res.status(200);
            
            return tvshows;
        });

        get("/movies", (req, res) -> {
            String movies = dq.getAllMovies();
            res.status(200);
            
            return movies;
        });

        get("createUser/:userid/:password", (req, res) -> {
            System.out.println("Creating user with id: " + req.params(":userid") + " and password: " + req.params(":password"));
            boolean created = dq.createUser(req.params(":userid"), req.params(":password"));

            if(created) 
                res.status(200);
            else 
                res.status(400);

            return created;
        });

        get("validateUser/:userid/:password", (req, res) -> {
            System.out.println("Validating user: " + req.params(":userid") + " with password: " + req.params(":password"));
            boolean isValid = dq.validateUser(req.params(":userid"), req.params(":password"));
            res.status(200);
            return isValid;
        });
        
        Runtime.getRuntime().addShutdownHook(new Thread(dq::closeConnection));
    }
}
