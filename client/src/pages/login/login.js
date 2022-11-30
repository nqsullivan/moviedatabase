import React, {useContext, useEffect} from 'react';
import Navbar from "../../components/navbar/navbar.js";
import './styles.css';
import {UserContext} from "../../context/user-context.js";
import {useNavigate} from "react-router-dom";

/**
 * A React component that shows a login page
 * @returns {JSX.Element}
 * @author Nathaniel Sullivan
 * @version 11/17/2022
 */
const Login = () => {

    const {user, setUser} = useContext(UserContext);

    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');

    const navigate = useNavigate();

    const handleLoginSubmit = (event) => {
        // TODO: Send a request to the server to login
        setUser(username);
        // Navigate to the home page
        navigate('/');
    }

    return (
        <div>
            <Navbar />
            <div className={"login-container"}>
                <div className={"heading"}>Login</div>
                <div className={"login-form"}>
                    <div className={"login-form-label"}>Username</div>
                    <input type={"text"} name={"username"} className={"login-form-input"} value={username} onChange={(event) => setUsername(event.target.value)}/>
                    <div className={"login-form-label"}>Password</div>
                    <input type={"password"} name={"password"} className={"login-form-input"} value={password} onChange={(event) => setPassword(event.target.value)}/>
                    <button className={"login-form-button"} onClick={handleLoginSubmit}>Login</button>
                </div>
            </div>
        </div>
    )
}

export default Login;
