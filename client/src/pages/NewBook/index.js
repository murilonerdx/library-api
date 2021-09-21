import React, {useState} from 'react';

import logoImage from '../../assets/logo.svg'
import './style.css'
import {FiArrowLeft} from "react-icons/all";
import {Link, useHistory} from "react-router-dom";
import api from "../../services/api";

export default function NewBook(){
    const [id, setId] = useState(null);
    const [author, setAuthor] = useState('');
    const [launchDate, setLaunchDate] = useState('');
    const [price, setPrice] = useState('');
    const [title, setTitle] = useState('');

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const history = useHistory();

    async function createNewBook(e){
        e.preventDefault();
        const data = {
            title,
            author,
            launchDate,
            price
        }

        try{
            await api.post('api/book/v1',data, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            })
            history.push('/books')
        }catch(err){
            alert('Error while recording Book! Try again!')
        }
    }
    return (
        <div className="new-book-container">
            <div className="content">
                <section className="form">
                    <img src={logoImage} alt="Image"/>
                    <h1>Add New Book</h1>
                    <p>Enter the book information and click on 'Add'!</p>
                    <Link className="back-link" to="/books">
                        <FiArrowLeft size={16} color="#251fc5"/>
                        Home
                    </Link>
                </section>
                <form onSubmit={createNewBook}>
                    <input
                        onChange={e=> setTitle(e.target.value)}
                        value={title}
                        placeholder="Title"/>
                    <input
                        onChange={e=> setLaunchDate(e.target.value)}
                        value={launchDate}
                        placeholder="Author"/>
                    <input
                        onChange={e=> setAuthor(e.target.value)}
                        value={title}
                        type="date"/>
                    <input
                        onChange={e=> setPrice(e.target.value)}
                        value={price}
                        placeholder="Price"/>

                    <button className="button" type="submit">Add</button>
                </form>
            </div>
        </div>
    )
}