import React, {Component, useEffect, useState} from 'react';
import Button from '@mui/material/Button';
import {Link, useParams} from "react-router-dom"
import BoardNav from './boardNav/BoardNav'
import BackButton from './BackButton';
import './styles/DetailBoard.css'


const DetailBoard = () => {

    const {city_code, gu_code, image_id} = useParams();

    console.log("parameters ", city_code, gu_code, image_id)


    return (
        <div>
            <BoardNav city_code = {city_code} gu_code = {gu_code} />
            <BackButton/>
            <div className='BoardContainer'>
                <div className='DetailPhotos'>
                    <h2 className="text-center">상세 이미지</h2>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>작성자 </th>
                                <th>좋아요수</th>
                            </tr>
                            </thead>
                            <tbody>
                            {

                            }
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    );

}

export default DetailBoard;
