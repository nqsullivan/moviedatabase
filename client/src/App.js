import React from 'react';
import {UserContext} from "./context/user-context.js";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Home from "./pages/home/home.js";
import Login from "./pages/login/login.js";
import SignUp from "./pages/signup/signup.js";
import Watchlist from "./pages/watchlist/watchlist.js";

/**
 * A base App react component
 * @returns {JSX.Element}
 * @author Nathaniel Sullivan
 * @version
 */
const App = () => {

    const [user, setUser] = React.useState(null);

    const userContext = React.useMemo(() => ({
        user,
        setUser
    }), [user, setUser]);

    const router = createBrowserRouter([
        {
            path: '/',
            element: <Home/>
        },
        {
            path: '/login',
            element: <Login/>
        },
        {
            path: '/signup',
            element: <SignUp/>
        },
        {
            path: '/watchlist',
            element: <Watchlist/>
        }
    ])

    return(
        <UserContext.Provider value={userContext}>
            <RouterProvider router={router}/>
        </UserContext.Provider>
    )
}

export default App;
