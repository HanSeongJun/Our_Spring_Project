import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Nav from "./components/Nav/nav";
import Seoul from "./components/city/Seoul";

function App() {
    return(
        <BrowserRouter>
            <Nav />
            <Routes>
                <Route path='/' element = { <Home />}></Route>
                <Route path='/seoul' element = { <Seoul />}></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default App;