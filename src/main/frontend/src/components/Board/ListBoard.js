import React, {Component, useEffect, useState} from 'react';
import Button from '@mui/material/Button';
import {Link, useParams} from "react-router-dom"
import BoardNav from './boardNav/BoardNav'
import BackButton from './BackButton';
import './styles/ListBoard.css'


const ListBoard = () => {

    const {city_code, gu_code,} = useParams();

    console.log("parameters ", city_code, gu_code)

    return (
        <div>
            {/* 일단 city_code, gu_code를 BoardNav로 전달 -> 추후에 해당 코드에 맞는 이름으로 바꿔줘야 함 */}
            <BoardNav city_code = {city_code} gu_code = {gu_code} />
            <BackButton/>
            <div className='BoardContainer'>
                <div className='MostLikedPhotos'>
                    <h2 className="text-center">베스트 이미지</h2>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>작성자 </th>
                            </tr>
                            </thead>
                            <tbody>
                            {

                            }
                            </tbody>
                        </table>
                    </div>
                </div>
                <div className='RecentPhotos'>
                    <h2 className="text-center">최근 이미지</h2>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>작성자 </th>
                            </tr>
                            </thead>
                            <tbody>
                            {

                            }
                            </tbody>
                        </table>
                    </div>
                </div>
                <Button><Link to={`/city/${city_code}/${gu_code}/spot/upload`} style={{ textDecoration: "none" }}>등록</Link></Button>
            </div>
        </div>
    );
}

export default ListBoard;
