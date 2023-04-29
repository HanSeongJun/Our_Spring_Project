import React, {Component, useEffect, useState} from 'react';
import Button from '@mui/material/Button';
import {Link, useParams} from "react-router-dom"
import BoardNav from './boardNav/BoardNav'
import BackButton from './BackButton';
import './styles/UploadBoard.css'


const UploadBoard = () => {

    const {city_code, gu_code} = useParams();

    console.log("parameters ", city_code, gu_code)

    return (
        <div>
            <BoardNav city_code = {city_code} gu_code = {gu_code} />
            <BackButton/>
            <div className='BoardContainer'>
                <div className='UploadPhoto'>
                    <h2 className="text-center">이미지 올리기</h2>

                </div>

            </div>
        </div>
    );
}

export default UploadBoard;
