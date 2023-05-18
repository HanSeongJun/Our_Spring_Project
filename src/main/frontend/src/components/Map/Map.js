import React, {useEffect, useState} from "react";
import "./styles/Home.css";
import { useNavigate } from 'react-router-dom';
import "./styles/Map.css";
import {Rating} from "@mui/material";

const API_URL = 'http://localhost:8080/map/citymap';

const Map = () => {

    let navigate = useNavigate();

    const [data, setData] = useState([]);
    const [apidata, setApiData] = useState([]);

    //backend grade를 기준으로 3단계로 영역마다 색을 칠해야함. -> 우선 서울만 진행

    useEffect(() => {
        // Fetch data from the API
        const fetchData = async () => {
            try {
                const response = await fetch(API_URL);
                console.log(response);
                const json = await response.json();
                console.log(json);
                setData(json.city_data["city_data"]);
                setApiData(json.api_data["api_data"]);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    const HandleTagClick = (id) => {
        console.log("전국지도에서 클릭한 city의 id값 -----> ", id);

        for(let i=0; i<data.length; i++){
            if(id===data[i]["cityCode"]){
                navigate(`/city/${id}`);
            }
        }
    }

    const getColorByGrade = (id)  =>{

        for(let i=0; i<data.length; i++){
            if(data[i]["cityCode"] === id){
                if (data[i]["grade"] === 1) {
                    return '#B3E5FC'; // Change to the desired color for the highest grade
                } else if (data[i]["grade"] === 2) {
                    return '#BAE19AFF'; // Change to the desired color for intermediate grades
                } else if (data[i]["grade"] === 3) {
                    return '#D0A684FF'; // Change to the desired color for the lowest grade
                }else{
                    return '#EFB9B9FF';
                }
            }
        }
    }

    return(
        <div className="Map">

            <div className="Weather">
                <p>오늘의 하늘상태와 강수형태는?</p>
                <p>{apidata.informSky}</p>
                <p>{apidata.informPty}</p>

            </div>

            <div className="ParticulateMater">
                <p>오늘의 미세먼지🍀 상태는? </p>
                <p>{apidata.informCause}</p>
                <p>{apidata.informOverall}</p>

            </div>
            <br />

            <div className="Grade3_1">
                <div>
                    <p className="Grade1">1등급(매우좋음)</p>
                    <p className="Grade2">2등급(보통)</p>
                    <p className="Grade3">3등급(나쁨)</p>
                </div>
            </div>
            <div className="Grade3_2">
            <svg className="Svg"  xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <filter id="dropshadow">
                        <feGaussianBlur in="SourceAlpha" stdDeviation="3"/>
                        <feOffset dx="2" dy="2" result="offsetblur"/>
                        <feMerge>
                            <feMergeNode/>
                            <feMergeNode in="SourceGraphic"/>
                        </feMerge>
                    </filter>
                </defs>
                <g filter="url(#dropshadow)">
                    {data.map((city) => (
                        <path
                            key={city.cityCode}
                            id={city.cityCode}
                            className="OUTLINE"
                            style={{ fill: getColorByGrade(city.cityCode) }}
                            d={city.vector}
                            onClick={(e)=>{HandleTagClick(e.target.id)}}
                        />
                    ))}
                </g>
                <g>
                    <text id="LCD11" className="TEXT" x="156" y="214">서울특별시</text>
                    <text id="LCD26" className="TEXT" x="503" y="695">부산광역시</text>
                    <text id="LCD27" className="TEXT" x="418" y="567">대구광역시</text>
                    <text id="LCD28" className="TEXT" x="67" y="177">인천광역시</text>
                    <text id="LCD29" className="TEXT" x="127" y="707">광주광역시</text>
                    <text id="LCD30" className="TEXT" x="221" y="463">대전광역시</text>
                    <text id="LCD31" className="TEXT" x="531" y="622">울산광역시</text>
                    <text id="LCD36" className="TEXT" x="199" y="418">세종특별자치시</text>
                    <text id="LCD41" className="TEXT" x="216" y="245">경기도</text>
                    <text id="LCD42" className="TEXT" x="370" y="179">강원도</text>
                    <text id="LCD43" className="TEXT" x="294" y="381">충청북도</text>
                    <text id="LCD44" className="TEXT" x="105" y="449">충청남도</text>
                    <text id="LCD45" className="TEXT" x="179" y="592">전라북도</text>
                    <text id="LCD46" className="TEXT" x="138" y="764">전라남도</text>
                    <text id="LCD47" className="TEXT" x="447" y="460">경상북도</text>
                    <text id="LCD48" className="TEXT" x="367" y="672">경상남도</text>
                    <text id="LCD50" className="TEXT" x="76" y="1070">제주특별자치도</text>
                </g>
            </svg>
            </div>
        </div>
    )
}

export default Map;