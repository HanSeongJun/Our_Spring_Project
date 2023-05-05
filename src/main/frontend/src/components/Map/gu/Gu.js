import React, {useEffect, useState} from 'react';
import { useParams } from 'react-router-dom';
import Seoul from "../city/Seoul";
import "./styles/Gu.css";

const API_URL = 'http://localhost:8080/map/spotlist';
//현재는 서울만 들어있음 -> test용 -> parameter를 city_code,gu_code로 변경예정

const Gu = () => {

    const { city_code, gu_code } = useParams();
    const [data, setData] = useState([]);

    //넘어온 코드에 따라서 다른 결과를 보여주는 로직을 작성해야함
    console.log("gu.js에 넘어온 city/gu code parameter-------->  ",city_code, gu_code);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`${API_URL}?guCode=${gu_code}`);//url에 city_code, gu_code를 넘기는 것으로 변경예정
                const json = await response.json();
                setData(json.data);
                console.log('Fetched data:', json.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };
        fetchData();
    }, []);

    return (
        <div className="Gu">
            <div className="City">
                {city_code === "CD11" && gu_code === "CD11140" && <Seoul />}
                {city_code === "CD11" && gu_code === "CD11170" && <Seoul />}
            </div>
            <div className="Spot">
                {data.map((item, index) => (
                    <div key={index}>
                        <p>Spot Name: {item.spotName}</p>
                        <p>Comment: {item.comment}</p>
                    </div>
                ))}
            </div>
        </div>
    );

};

export default Gu;
