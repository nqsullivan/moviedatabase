import React, {useEffect, useContext} from 'react';
import Navbar from "../../components/navbar/navbar.js";
import {UserContext} from "../../context/user-context.js";

/**
 * A React component that renders a list of movies from the Movie Database
 * @returns {JSX.Element}
 * @author Nathaniel Sullivan
 * @version 11/17/2022
 */
const Home = () => {

    const {user, setUser} = useContext(UserContext);

    const [movies, setMovies] = React.useState([
        {
            id: 1,
            title: 'The Shawshank Redemption',
            releaseDate: '1994-09-23',
            rating: '8.7',
            type: 'movie',
            releaseType: 'theatrical',
            grossRevenue: '28,341,469'
        },
        {
            id: 2,
            title: 'The Godfather',
            releaseDate: '1972-03-24',
            rating: '9.2',
            type: 'movie',
            releaseType: 'theatrical',
            grossRevenue: '134,966,411'
        },
        {
            id: 3,
            title: 'The Godfather: Part II',
            releaseDate: '1974-12-20',
            rating: '9.0',
            type: 'movie',
            releaseType: 'theatrical',
            grossRevenue: '57,300,000'
        },
        {
            id: 4,
            title: 'The Dark Knight',
            releaseDate: '2008-07-18',
            rating: '9.0',
            type: 'movie',
            releaseType: 'theatrical',
            grossRevenue: '534,858,444'
        },
        {
            id: 5,
            title: 'Mad Max: Fury Road',
            releaseDate: '2015-05-15',
            rating: '8.1',
            type: 'movie',
            releaseType: 'theatrical',
            grossRevenue: '153,629,485'
        },
        {
            id: 6,
            title: 'Madaari',
            releaseDate: '2016-07-22',
            rating: '8.1',
            type: 'movie',
            releaseType: 'theatrical',
            grossRevenue: '0'
        }
    ]);

    useEffect(() => {
        // TODO: Fetch movies from the Movie Database
    });

    const handleAddToWatchlist = (movie) => {
        console.log(movie);

        // TODO: Add movie to user's watchlist
    }


    return(
        <div>
            <Navbar />
            <h1>Home</h1>
            <div style={{display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gridGap: '1rem', padding: '1rem'}}>
                {movies.map(movie => (
                    <div key={movie.id} style={{border: '1px solid black', padding: '1rem'}}>
                        <h2>{movie.title}</h2>
                        <p>Release Date: {movie.releaseDate}</p>
                        <p>Rating: {movie.rating}</p>
                        <p>Type: {movie.type}</p>
                        <p>Release Type: {movie.releaseType}</p>
                        <p>Gross Revenue: {movie.grossRevenue}</p>
                        {user ? <button onClick={event => {handleAddToWatchlist(movie)}}>Add to Watchlist</button> : null}
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Home;
