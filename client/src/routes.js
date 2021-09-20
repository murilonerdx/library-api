import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom'

import Login from './pages/Login'
import Book from './pages/Book'
import NewBook from './pages/NewBook'

export default function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Login}/>
                <Route path="/books" component={Book}/>
                <Route path="/book/new" component={NewBook}/>
            </Switch>
        </BrowserRouter>
    );
}