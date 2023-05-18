import React from 'react';
import { useParams, Routes, Route, Link } from 'react-router-dom';
import Seoul from './Seoul';

const City = () => {

    let { code } = useParams();

    //넘어온 코드에 따라서 다른 결과를 보여주는 로직을 작성해야함
    console.log("city.js에 넘어온 city code parameter-------->  ",code);

    return (
        <div>
            {code === "CD11" && <Seoul />}
        </div>
    );
}

export default City;
