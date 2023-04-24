import React from "react";
import "../components/Home.css";
import { Link } from 'react-router-dom';

const Home = () => {

    const MapAreaLinks = ()=> {
        return (
            <map name="map">
                <Link to="/Seoul">
                    <area shape="rect" coords="10,10,300,300" />
                </Link>
                <Link to="/Busan">
                    <area shape="rect" coords="300,300,800,800" />
                </Link>
            </map>
        );
    }

    return(
        <div className="ImageDiv">
            <Link to="/">
                <img src="img/map1.png" useMap="#map" alt="Map image" />
            </Link>
            <MapAreaLinks />
        </div>
    )
}

export default Home;