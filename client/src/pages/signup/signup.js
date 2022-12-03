import React, {useContext, useEffect} from 'react';
import Navbar from "../../components/navbar/navbar.js";
import './styles.css';
import {UserContext} from "../../context/user-context.js";
import {useNavigate} from "react-router-dom";

/**
 * A React component that shows a signup page
 * @returns {JSX.Element}
 * @author Nathaniel Sullivan
 * @version 11/17/2022
 */
const Signup = () => {

    const {user, setUser} = useContext(UserContext);

    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [confirmPassword, setConfirmPassword] = React.useState('');

    const navigate = useNavigate();

    const handleLoginSubmit = (event) => {
        if(password == confirmPassword) {
            // Send a request to the server to login
            fetch("http://localhost:8080/createUser/" + username + '/' + password, {
                method: 'GET'
            })
                .then(response => response.text())
                .then(result => {
                    console.log(result);
                    if (result === 'true') {
                        setUser(username);
                        navigate('/');
                    } else {
                        alert('Error creating user, please try again with a different username');
                    }
                })
        } else {
            alert('Passwords do not match');
        }
    }

    return (
        <div>
            <Navbar />
            <div className={"login-container"}>
                <div className={"heading"}>Sign Up</div>
                <div className={"login-form"}>
                    <div className={"login-form-label"}>Username</div>
                    <input type={"text"} name={"username"} className={"login-form-input"} value={username} onChange={(event) => setUsername(event.target.value)}/>
                    <div className={"login-form-label"}>Password</div>
                    <input type={"password"} name={"password"} className={"login-form-input"} value={password} onChange={(event) => setPassword(event.target.value)}/>
                    <div className={"login-form-label"}>Confirm Password</div>
                    <input type={"password"} name={"confirmPassword"} className={"login-form-input"} value={confirmPassword} onChange={(event) => setConfirmPassword(event.target.value)}/>
                    <button className={"login-form-button"} onClick={handleLoginSubmit}>Sign Up</button>
                </div>
            </div>
        </div>
    )
}

export default Signup;
