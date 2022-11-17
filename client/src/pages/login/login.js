import React from 'react';
import Navbar from "../../components/navbar/navbar.js";
import './styles.css';

/**
 * A React component that shows a login page
 * @returns {JSX.Element}
 * @author Nathaniel Sullivan
 * @version 11/17/2022
 */
const Login = () => {
    return (
        <div>
            <Navbar />
            <div className={"login-container"}>
                <div className={"heading"}>Login</div>
                <div className={"login-form"}>
                    <div className={"login-form-label"}>Username</div>
                    <input className={"login-form-input"} type={"text"} placeholder={"Username"} />
                    <div className={"login-form-label"}>Password</div>
                    <input className={"login-form-input"} type={"password"} placeholder={"Password"} />
                    <div></div>
                    <button className={"login-form-button"}>Login</button>
                </div>
            </div>
        </div>
    )
}

export default Login;
