import {React} from 'react'

export const RegisterForm = () => {
    return (
        <div className="form-container">
            <form id="registerForm">
                <fieldset>
                    <legend>Username</legend>
                    <input type="text" name="username" placeholder="username"/>
                </fieldset>
                <fieldset>
                    <legend>Password</legend>
                    <input type="text" id="password" placeholder="password"></input>
                </fieldset>
                <fieldset>
                    <legend>Password</legend>
                    <input type="text" id="password" placeholder="repeat password"></input>
                </fieldset>
                <button id="enterButton" className="enter-button">Sign in</button>
            </form>
        </div>
    );
}