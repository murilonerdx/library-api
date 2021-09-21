import React, {useEffect, useState} from 'react';
import {Link, useHistory} from 'react-router-dom';
import {FiEdit, FiPower, FiTrash} from 'react-icons/fi'

import './style.css'
import logoImage from '../../assets/logo.svg'
import api from "../../services/api";

export default function Book(){

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const history = useHistory();

    const [books, setBooks] = useState([]);

    useEffect(() =>{
        api.get('api/book/v1',{
            headers: {
                Authorization: `Bearer ${accessToken}`
            }
        }).then(response => {
            setBooks(response.data._embedded.bookVoes)
        });
    })

    return (
        <div className="book-container">
           <header>
               <img src={logoImage} alt="Logo image"/>
               <span>Welcome, <strong>{username.toUpperCase()}</strong>!</span>
               <Link className="button" to="book/new">Add New Book</Link>
               <button type="button">
                    <FiPower size={18} color="#251EFC5"/>
               </button>
           </header>
            <h1>Registered Books</h1>
            <ul>
                {books.map(book=>(
                    <li>
                        <strong>Title:</strong>
                        <p>{book.title}</p>
                        <strong>Author:</strong>
                        <p>{book.author}</p>
                        <strong>Price:</strong>
                        <p>{Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(book.price)}</p>
                        <strong>Release Date:</strong>
                        <p>{Intl.DateTimeFormat('pt-BR').format(new Date(book.launchDate))}</p>
                        <button type="button">
                            <FiEdit size={20} color="#251FC5"/>
                        </button>
                        <button type="button">
                            <FiTrash size={20} color="#251FC5"/>
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    )
}