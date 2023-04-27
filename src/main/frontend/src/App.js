import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Map/Home";
import City from "./components/Map/city/City";
import Gu from "./components/Map/gu/Gu";
import Nav from "./components/Nav/nav";

function App() {
    return(
        <BrowserRouter>
            <Nav />
            <Routes>
                <Route path='/home' element = { <Home />}></Route>
                <Route path="/city/:code" element = { <City />} />
                <Route path="/city/:city_code/:gu_code" element = { <Gu />} />
            </Routes>
        </BrowserRouter>
    )
}

export default App;