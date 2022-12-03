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

    const [type, setType] = React.useState('Movies');

    const [movies, setMovies] = React.useState([]);

    const [tvShows, setTvShows] = React.useState([]);

    const [filter, setFilter] = React.useState('');

    useEffect(() => {

    }, [filter]);

    useEffect(() => {
        fetch("http://localhost:8080/movies", {
            method: 'GET'
        }).then(response => response.json())
            .then(data => {
                    let newMovies = [];
                    data.forEach((movie, index) => {
                        newMovies.push({
                            id: index + 1,
                            ...movie
                        });
                    });
                    setMovies(newMovies);
                }
            );

        fetch("http://localhost:8080/tv-shows", {
            method: 'GET'
        }).then(response => response.json())
            .then(data => {
                    let newTvShows = [];
                    data.forEach((tvShow, index) => {
                        newTvShows.push({
                            id: index + 1,
                            ...tvShow
                        });
                    });
                    setTvShows(newTvShows);
                    console.log(tvShows)
                }
            );
    }, []);

    const handleAddToWatchlist = (movie) => {
        fetch("http://localhost:8080/addToWatchlist/" + user + '/' + movie.Title + '/' + movie.ReleaseDate, {
            method: 'GET'
        })
            .then(response => response.text())
            .then(result => {
                    if (result === 'true') {
                        alert('Added to watchlist');
                    } else {
                        alert('Failed to add to watchlist, try logging in again');
                    }
                }
            );
    }

    return (
        <div>
            <Navbar/>
            <h2 style={{padding: '0 1rem'}}>Home</h2>
            <div style={{
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center',
                padding: '1rem 2rem',
                backgroundColor: 'lightgrey'
            }}>
                <button style={{
                    backgroundColor: type === 'Movies' ? 'lightblue' : 'white',
                    padding: ' 1% 20%',
                    borderRadius: '5px',
                    textAlign: 'center'
                }} onClick={() => setType('Movies')}>Movies
                </button>
                <button style={{
                    backgroundColor: type === 'TV Shows' ? 'lightblue' : 'white',
                    padding: ' 1% 20%',
                    borderRadius: '5px',
                    textAlign: 'center'
                }} onClick={() => setType('TV Shows')}>TVShows
                </button>
            </div>
            {type === 'Movies' ? (
                <>
                    <h2 style={{textAlign: 'center'}}>Movies</h2>
                    <div style={{
                        display: 'flex',
                        justifyContent: 'space-between',
                        alignItems: 'center',
                        padding: '1rem 2rem',
                        backgroundColor: 'lightgrey'
                    }}>
                        <button style={{padding: ' 1% 10%', borderRadius: '5px', textAlign: 'center'}}
                                onClick={() => {
                                    setMovies(movies.sort((a, b) => a.Rating - b.Rating));
                                    setFilter(filter + '1');
                                }}>Sort by Rating
                        </button>
                        <button style={{padding: ' 1% 10%', borderRadius: '5px', textAlign: 'center'}}
                                onClick={() => {
                                    setMovies(movies.sort((a, b) => new Date(a.ReleaseDate) - new Date(b.ReleaseDate)));
                                    setFilter(filter + '1');
                                }}>Sort by Release Date
                        </button>
                        <button style={{padding: ' 1% 10%', borderRadius: '5px', textAlign: 'center'}}
                                onClick={() => {
                                    setMovies(movies.sort((a, b) => a.Title.localeCompare(b.Title)));
                                    setFilter(filter + '1');
                                }}>Sort by Title
                        </button>
                    </div>

                    <div style={{
                        display: 'grid',
                        gridTemplateColumns: 'repeat(4, 1fr)',
                        gridGap: '1rem',
                        padding: '1rem'
                    }}>
                        {movies.map(movie => (
                            <div key={movie.id} style={{border: '1px solid black', padding: '1rem'}}>
                                <h2>{movie.Title}</h2>
                                <p>Release Date: {movie.ReleaseDate}</p>
                                <p>Rating: {movie.Rating}</p>
                                <p>Type: {movie.ReleaseType}</p>
                                <p>Release Type: {movie.ReleaseType}</p>
                                <p>Gross Revenue: {movie.GrossRevenue}</p>
                                {user ? <button onClick={event => {
                                    handleAddToWatchlist(movie)
                                }}>Add to Watchlist</button> : null}
                            </div>
                        ))}
                    </div>
                </>
            ) : (
                <>
                    <h2 style={{textAlign: 'center'}}>TV Shows</h2>
                    <div style={{
                        display: 'flex',
                        justifyContent: 'space-between',
                        alignItems: 'center',
                        padding: '1rem 2rem',
                        backgroundColor: 'lightgrey'
                    }}>
                        <button style={{padding: ' 1% 10%', borderRadius: '5px', textAlign: 'center'}}
                                onClick={() => {
                                    setTvShows(tvShows.sort((a, b) => a.Rating - b.Rating));
                                    setFilter(filter + '1');
                                }}>Sort by Rating
                        </button>
                        <button style={{padding: ' 1% 10%', borderRadius: '5px', textAlign: 'center'}}
                                onClick={() => {
                                    setTvShows(tvShows.sort((a, b) => new Date(a.ReleaseDate) - new Date(b.ReleaseDate)));
                                    setFilter(filter + '1');
                                }}>Sort by Release Date
                        </button>
                        <button style={{padding: ' 1% 10%', borderRadius: '5px', textAlign: 'center'}}
                                onClick={() => {
                                    setTvShows(tvShows.sort((a, b) => a.Title.localeCompare(b.Title)));
                                    setFilter(filter + '1');
                                }}>Sort by Title
                        </button>
                    </div>
                    <div style={{
                        display: 'grid',
                        gridTemplateColumns: 'repeat(4, 1fr)',
                        gridGap: '1rem',
                        padding: '1rem'
                    }}>
                        {tvShows.map(tvShow => (
                            <div key={tvShow.id} style={{border: '1px solid black', padding: '1rem'}}>
                                <h2>{tvShow.Title}</h2>
                                <p>Release Date: {tvShow.ReleaseDate}</p>
                                <p>Rating: {tvShow.Rating}</p>
                                <p>Number of Seasons: {tvShow.NumSeasons}</p>
                                <p>Number of Episodes: {tvShow.NumEpisodes}</p>
                                {user ? <button onClick={event => {
                                    handleAddToWatchlist(tvShow)
                                }}>Add to Watchlist</button> : null}
                            </div>
                        ))}
                    </div>
                </>
            )}
        </div>
    )
}

export default Home;
