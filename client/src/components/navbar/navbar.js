import React, {useEffect, useState} from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
// import {useDispatch} from "react-redux";

/**
 * A navigation bar that is displayed on the top of the page.
 * @returns {JSX.Element}
 * @author Nathaniel Sullivan
 * @version 11/17/2022
 */
const Navbar = () => {

    const navigate = useNavigate();
    // const dispatch = useDispatch();

    return (
        <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: '1rem 2rem', backgroundColor: 'lightgrey'}}>
            <h1>Fantasy Football Lineup Optimizer</h1>
            <div>
                <Link to={'/'} style={{marginRight: '2rem'}}>Home</Link>
                <Link to={'/watchlist'} style={{marginRight: '2rem'}}>WatchList</Link>
                <Link to={'/login'} style={{marginRight: '2rem'}}>Login</Link>
            </div>
        </div>
    );
}

export default Navbar;
