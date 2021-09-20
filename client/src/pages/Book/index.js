import React from 'react';
import {Link} from 'react-router-dom';
import {FiEdit, FiPower, FiTrash} from 'react-icons/fi'

import './style.css'
import logoImage from '../../assets/logo.svg'

export default function Book(){
    return (
        <div className="book-container">
           <header>
               <img src={logoImage} alt="Logo image"/>
               <span>Welcome, <strong>Murilo</strong>!</span>
               <Link className="button" to="book/new">Add New Book</Link>
               <button type="button">
                    <FiPower size={18} color="#251EFC5"/>
               </button>
           </header>
            <h1>Registered Books</h1>
            <ul>
                <li>
                    <strong>Title:</strong>
                    <p>Docker DeepDive</p>
                    <strong>Author:</strong>
                    <p>Murilo Pereira</p>
                    <strong>Release Date:</strong>
                    <p>12/07/2017</p>
                    <button type="button">
                        <FiEdit size={20} color="#251FC5"/>
                    </button>
                    <button type="button">
                        <FiTrash size={20} color="#251FC5"/>
                    </button>
                </li>
            </ul>
        </div>
    )
}