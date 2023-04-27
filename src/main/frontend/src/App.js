import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Nav from "./components/Nav/nav";
import City from "./components/city/City";

function App() {
    return(
        <BrowserRouter>
            <Nav />
            <Routes>
                <Route path='/' element = { <Home />}></Route>
                <Route path="/city/:code" element = { <City />} />
            </Routes>
        </BrowserRouter>
    )
}

export default App;