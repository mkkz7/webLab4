import {React} from 'react'
import { useState } from 'react';
import {useLoginMutation} from '../../features/auth/authApi.js';
import {useNavigate} from "react-router-dom";
import {Header} from "../UI/Header.jsx";
import "../../styles/LoginPage.css"

export const LoginForm = () => {
    const navigate = useNavigate();
    const [login, {isLoading, error, isSuccess}] = useLoginMutation();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const submit = async (e) => {
        e.preventDefault();
        try {
            await login({ username, password }).unwrap();
            navigate("/user")
        } catch (e) {
            console.log(e);
        }
    }

    return (
        <div className="login-page-container">


            <Header />

            <div className="login-form-container">
                <h2 className="form-title">Log in</h2>

                <form id="loginForm" onSubmit={submit}>
                    <p type="hidden" id="err-message"></p>

                    <fieldset>
                        <legend>Username</legend>
                        <input
                            type="text"
                            name="username"
                            placeholder="Введите имя пользователя"
                            value={username}
                            onChange={e => setUsername(e.target.value)}
                            required
                            disabled={isLoading}
                        />
                    </fieldset>

                    <fieldset>
                        <legend>Password</legend>
                        <input
                            type="password"
                            id="password"
                            placeholder="Введите пароль"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                            required
                            disabled={isLoading}
                        />
                    </fieldset>

                    <button
                        id="enterButton"
                        className="enter-button"
                        type="submit"
                        disabled={isLoading}
                    >
                        {isLoading ? (
                            <>
                                <span className="loading-spinner"></span>
                                Loading...
                            </>
                        ) : (
                            'Log in'
                        )}
                    </button>

                    {error && (
                        <div className="error-message">
                            {error?.data?.message || error?.error || "Something went wrong..."}
                        </div>
                    )}

                    {isSuccess && (
                        <div className="success-message">
                            Redirecting...
                        </div>
                    )}
                </form>

                <div className="register-link">
                    <a href="/register">Sign up</a>
                </div>
            </div>
        </div>
    );
}