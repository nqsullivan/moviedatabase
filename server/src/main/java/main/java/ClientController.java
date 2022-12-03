package main.java;

import static spark.Spark.*;

public class ClientController {

    public static void main(String[] args) {

        port(8080);

        DataQuery dq = new DataQuery();

        post("/watchlist/:userid", (req, res) -> dq.getWatchlist(":userid"));

        get("/tv-shows", (req, res) -> dq.getAllTvShows());

        get("/movies", (req, res) -> dq.getAllMovies());

        post("createUser/:userid/:password", (req, res) -> dq.createUser(":userid", ":password"));

        post("validateUser/:userid/:password", (req, res) -> dq.validateUser(":userid", ":password"));
    }
}
