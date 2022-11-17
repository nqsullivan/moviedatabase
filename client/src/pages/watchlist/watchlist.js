import React from 'react';
import Navbar from "../../components/navbar/navbar.js";

/**
 * A React component that renders a list of movies from a user's watchlist
 * @returns {JSX.Element}
 * @author Nathaniel Sullivan
 * @version 11/17/2022
 */
const Watchlist = () => {
    return (
        <div>
            <Navbar />
            <h1>Watch List</h1>
        </div>
    )
}

export default Watchlist;
