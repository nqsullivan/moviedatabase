import React, {useContext} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { UserContext } from '../../context/user-context.js';

/**
 * A navigation bar that is displayed on the top of the page.
 * @returns {JSX.Element}
 * @author Nathaniel Sullivan
 * @version 11/17/2022
 */
const Navbar = () => {

    const navigate = useNavigate();
    const {user, setUser} = useContext(UserContext);

    return (
        <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: '1rem 2rem', backgroundColor: 'lightgrey'}}>
            <h1>Movie Database Project</h1>
            <div>
                <Link to={'/'} style={{marginRight: '2rem'}}>Home</Link>
                <Link to={'/watchlist'} style={{marginRight: '2rem'}}>WatchList</Link>
                {user ?
                    <>
                        <span style={{marginRight: '2rem'}}>Logged in as: {user}</span>
                        <button onClick={() => { setUser(null); navigate('/'); }}>Logout</button>
                    </>
                    :
                    <Link to={'/login'} style={{marginRight: '2rem'}}>Login/Signup</Link>
                }
            </div>
        </div>
    );
}

export default Navbar;
