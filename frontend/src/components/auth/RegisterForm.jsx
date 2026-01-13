import {React} from 'react'
import { useState } from 'react';
import { useRegisterMutation } from '../../features/auth/authApi.js';
import {useNavigate} from "react-router-dom";
import {Header} from "../UI/Header.jsx";
import "../../styles/RegisterPage.css"

export const RegisterForm = () => {
    const navigate = useNavigate()
    const [register, {isLoading, error, isSuccess}] = useRegisterMutation();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");



    const submit = async (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            alert("Пароли не совпадают!");
            return;
        }

        try {
            await register({ username, password }).unwrap();
            navigate("/user");
        } catch (e) {
            console.log(e);
        }
    }

    return (
        <div className="register-page-container">

            <Header />

            <div className="register-form-container">
                <h2 className="register-title">Registration</h2>

                <form id="registerForm" onSubmit={submit}>
                    <fieldset>
                        <legend>Username</legend>
                        <div className="input-wrapper">
                            <input
                                type="text"
                                name="username"
                                placeholder="Username"
                                value={username}
                                onChange={e => setUsername(e.target.value)}
                                required
                                disabled={isLoading}
                            />
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend>Password</legend>
                        <div className="input-wrapper">
                            <input
                                type="password"
                                id="password"
                                placeholder="Password"
                                value={password}
                                onChange={e => setPassword(e.target.value)}
                                required
                                disabled={isLoading}
                            />
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend>Confirm password</legend>
                        <div className="input-wrapper">
                            <input
                                type="password"
                                id="confirmPassword"
                                placeholder="Repeat the password"
                                value={confirmPassword}
                                onChange={e => setConfirmPassword(e.target.value)}
                                required
                                disabled={isLoading}
                            />
                            {password === confirmPassword && (
                                <span className="password-match visible">✓</span>
                            )}
                        </div>
                    </fieldset>

                    <button
                        className="register-button"
                        type="submit"
                        disabled={isLoading}
                    >
                        {isLoading ? (
                            <>
                                <span className="loading-spinner"></span>
                                Registration...
                            </>
                        ) : (
                            'Create!'
                        )}
                    </button>

                    {error && (
                        <div className="register-message register-error">
                            {error?.data?.message || error?.error || "Something went wrong..."}
                        </div>
                    )}

                    {isSuccess && (
                        <div className="register-message register-success">
                            Redirecting...
                        </div>
                    )}
                </form>

                <div className="login-link">
                    <a href="/login">Log in</a>
                </div>
            </div>
        </div>
    );
}