import React, {useState} from 'react';
import { useParams } from 'react-router-dom';
import Seoul from "../city/Seoul";
import "./styles/Gu.css";

const Gu = () => {

    const { city_code, gu_code } = useParams();

    //넘어온 코드에 따라서 다른 결과를 보여주는 로직을 작성해야함
    console.log("gu.js에 넘어온 city/gu code parameter-------->  ",city_code, gu_code);

    //gu_code를 통해서 backend에 spot data를 get 해서 보여주는 코드를 작성해야함
    const [spot, setSpot] = useState({
        spotId: 0,
        name: "spot name"
    });


    return (
        <div className="Gu">
            <div className="City">
                {city_code === "CD11" && gu_code === "CD11140" && <Seoul />}
                {city_code === "CD11" && gu_code === "CD11170" && <Seoul />}
            </div>
            <div className="Spot">
                <h1>Spot list</h1>
                <div>
                    Title:{spot.name}<br/>
                    Imagelist : image를 넣습니다.
                </div>
                <div>
                    Title:{spot.name}<br/>
                    Imagelist : image를 넣습니다.
                </div>
                <div>
                    Title:{spot.name}<br/>
                    Imagelist : image를 넣습니다.
                </div>
            </div>
        </div>
    )

}

export default Gu;
