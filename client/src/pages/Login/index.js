import React from 'react';

import logoImage from '../../assets/logo.svg'
import padlock from '../../assets/padlock.png'
import './style.css'

export default function Login(){
    return (
        <div className="login-container">
            <section className="form">
                <img src={logoImage} alt="Login"/>
                <form>
                    <h1>Access your Account</h1>
                    <input placeholder="Username"/>
                    <input type="password" placeholder="Password"/>
                    <button type="submit">Login</button>
                </form>
            </section>
            <img src={padlock} alt="Login"/>
        </div>
    )
}