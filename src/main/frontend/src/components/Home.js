import React from "react";
import "../components/Home.css";
import { useNavigate } from "react-router-dom";

const Home = () => {

    let navigate = useNavigate();

    const Seoul = (e) => {
        e.preventDefault();
        navigate('/Seoul');
    }

    return(
        <div className="ImageDiv">
            <a onClick={Seoul}><img alt="map" src="img/map1.png" /></a>
        </div>
    )
}

export default Home;