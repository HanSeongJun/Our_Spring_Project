import React, {useEffect, useState} from "react";
import "../styles/Home.css"
import "../styles/City.css"
import {useNavigate} from "react-router-dom";

const API_URL = 'http://localhost:8080/map/gumap';

const Seoul = () => {

    let navigate = useNavigate();

    const [data, setData] = useState([]);

    useEffect(() => {
        // Fetch data from the API
        const fetchData = async () => {
            try {
                const response = await fetch(API_URL+`?cityCode=CD11`);
                console.log(response);
                const json = await response.json();
                console.log(json);
                setData(json.data);

            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    const HandleTagClick = (id) => {
        console.log("서울시도에서 클릭한 구별 id값 -----> ", id);
        for(let i=0; i<data.length; i++){
            if(id===data[i]["guCode"]){
                navigate(`/city/CD11/${id}`);
            }
        }
    }

    const getColorByGrade = (id)  =>{

        for(let i=0; i<data.length; i++){
            if(data[i]["guCode"] === id) {
                if (data[i]["grade"] === 1) {
                    return '#B3E5FC'; // Change to the desired color for the highest grade
                } else if (data[i]["grade"] === 2) {
                    return '#BAE19AFF'; // Change to the desired color for intermediate grades
                } else if (data[i]["grade"] === 3) {
                    return '#f6945f'; // Change to the desired color for the lowest grade
                }else{
                    return '#EFB9B9FF';
                }
            }
        }


    }

    return(
        <div className="ImageDiv">
            <svg height="656" width="800" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <filter id="dropshadow">
                        <feGaussianBlur in="SourceAlpha" stdDeviation="7" />
                        <feOffset dx="0" dy="0" result="offsetblur" />
                        <feMerge>
                            <feMergeNode />
                            <feMergeNode in="SourceGraphic" />
                        </feMerge>
                    </filter>
                    <filter>
                        <feGaussianBlur in="SourceAlpha" stdDeviation="1.4" />
                        <feOffset dx="1" dy="1" result="offsetblur" />
                        <feMerge>
                            <feMergeNode />
                            <feMergeNode in="SourceGraphic" />
                        </feMerge>
                    </filter>
                </defs>
                <g className="Map">
                    {data.map((gu) => (
                        <path
                            key={gu.guCode}
                            id={gu.guCode}
                            className="OUTLINE"
                            style={{ fill: getColorByGrade(gu.guCode) }}
                            d={gu.vector}
                            onClick={(e)=>{HandleTagClick(e.target.id)}}
                        />
                    ))}
                </g>
                <g filter="url(#dropshadow2)"></g><g filter="url(#dropshadow2)">
                <text id="LCD11110" className="TEXT" x="399" y="277">종로구</text>
                <text id="LCD11140" className="TEXT" x="433" y="338">중구</text>
                <text id="LCD11170" className="TEXT" x="407" y="395">용산구</text>
                <text id="LCD11200" className="TEXT" x="505" y="356">성동구</text>
                <text id="LCD11215" className="TEXT" x="577" y="365">광진구</text>
                <text id="LCD11230" className="TEXT" x="528" y="294">동대문구</text>
                <text id="LCD11260" className="TEXT" x="589" y="270">중랑구</text>
                <text id="LCD11290" className="TEXT" x="466" y="258">성북구</text>
                <text id="LCD11305" className="TEXT" x="459" y="181">강북구</text>
                <text id="LCD11320" className="TEXT" x="494" y="125">도봉구</text>
                <text id="LCD11350" className="TEXT" x="560" y="153">노원구</text>
                <text id="LCD11380" className="TEXT" x="323" y="218">은평구</text>
                <text id="LCD11410" className="TEXT" x="342" y="302">서대문구</text>
                <text id="LCD11440" className="TEXT" x="293" y="339">마포구</text>
                <text id="LCD11470" className="TEXT" x="209" y="421">양천구</text>
                <text id="LCD11500" className="TEXT" x="156" y="334">강서구</text>
                <text id="LCD11530" className="TEXT" x="199" y="470">구로구</text>
                <text id="LCD11545" className="TEXT" x="279" y="537">금천구</text>
                <text id="LCD11560" className="TEXT" x="295" y="413">영등포구</text>
                <text id="LCD11590" className="TEXT" x="361" y="461">동작구</text>
                <text id="LCD11620" className="TEXT" x="353" y="529">관악구</text>
                <text id="LCD11650" className="TEXT" x="481" y="515">서초구</text>
                <text id="LCD11680" className="TEXT" x="533" y="464">강남구</text>
                <text id="LCD11710" className="TEXT" x="624" y="448">송파구</text>
                <text id="LCD11740" className="TEXT" x="675" y="358">강동구</text>
            </g></svg>
        </div>
    )
}

export default Seoul;