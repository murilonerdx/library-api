import React, {useState} from 'react';
import {useHistory} from 'react-router-dom';

import logoImage from '../../assets/logo.svg'
import padlock from '../../assets/padlock.png'
import './style.css'
import api from '../../services/api'

export default function Login(){
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const history = useHistory();

    async function login(e){
        e.preventDefault();

        const data = {
            username,
            password
        };

        try{
            const response = await api.post('auth/signin', data);
            localStorage.setItem('username', username);
            localStorage.setItem('accessToken', response.data.token);

            history.push('/books');
        }catch(err){
            alert("Login failed! Try again");
        }
    };


    return (
        <div className="login-container">
            <section className="form">
                <img src={logoImage} alt="Login"/>
                <form onSubmit={login}>
                    <h1>Access your Account</h1>
                    <input value={username} onChange={e => setUsername(e.target.value)} placeholder="Username"/>
                    <input value={password} onChange={e => setPassword(e.target.value)} type="password" placeholder="Password"/>
                    <button type="submit">Login</button>
                </form>
            </section>
            <img src={padlock} alt="Login"/>
        </div>
    )
}