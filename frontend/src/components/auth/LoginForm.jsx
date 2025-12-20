import {React} from 'react'

export const LoginForm = () => {
    return (
        <div className="form-container">
            <form id="loginForm">
                <p type="hidden" id="err-message"></p>
                <fieldset>
                    <legend>Username</legend>
                    <input type="text" name="username" placeholder="username"/>
                </fieldset>
                <fieldset>
                    <legend>Password</legend>
                    <input type="text" id="password" placeholder="password"></input>
                </fieldset>
                <button id="enterButton" className="enter-button">Log in</button>
            </form>
        </div>
    );
}