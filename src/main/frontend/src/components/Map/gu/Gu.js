import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import Seoul from "../city/Seoul";
import "../styles/Gu.css";
import "../styles/SideBoard.css";
import Button from '@mui/material/Button';
import { Box } from "@mui/material";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';

const API_URL = 'http://localhost:8080/map/spotlist';
//현재는 서울만 들어있음 -> test용 -> parameter를 city_code,gu_code로 변경예정

const Gu = () => {

    let navigate = useNavigate();

    const { city_code, gu_code } = useParams();
    const [data, setData] = useState([]);

    //넘어온 코드에 따라서 다른 결과를 보여주는 로직을 작성해야함
    console.log("gu.js에 넘어온 city/gu code parameter-------->  ",city_code, gu_code);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`${API_URL}?guCode=${gu_code}`);
                const json = await response.json();
                setData(json.data);
                console.log('Fetched data:', json.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };
        fetchData();
    }, []);

    const HandleButtonClick = () => {
        console.log("이전 gumap으로 이동");
        navigate(`/city/${city_code}`);
    }

    const [showSideboard, setShowSideboard] = useState(false);

    const toggleSideboard = () => {
        setShowSideboard(!showSideboard);
    };

    return (
        <div className="Gu">
            <div className="City">
                {city_code === "CD11" && <Seoul />}
            </div>
            <span className="Span"></span>
            <div className="Spot">
                <div>
                    <Button className="BackButton" onClick={()=>{HandleButtonClick()}}> back </Button>
                </div>

                {data.map((item, index) => (
                    <div key={index}>
                        <div>
                            <Button variant="text" size="large" color ="success" onClick={toggleSideboard}>Spot Name: {item.spotName}</Button>
                        </div>
                        <div>
                            <div className={`sideboard ${showSideboard ? 'show' : ''}`}>
                                <Box className="header-box">
                                    <div className="header-content">
                                        <button onClick={toggleSideboard}>
                                            <ArrowBackIcon fontSize="large" className="back-icon" />
                                        </button>
                                        <h2 className="board-title">Spot Name: {item.spotName}</h2>
                                    </div>
                                </Box>
                                <Box className="content-box">
                                    <div className="sideboard-content">
                                        <Card sx={{ maxWidth: 600, mb : 2}}>
                                            <CardActionArea>
                                                <CardContent>
                                                    <Typography gutterBottom variant="h5" component="div">
                                                        베스트사진
                                                    </Typography>
                                                    <CardMedia
                                                        component="img"
                                                        alt="베스트사진"
                                                        height="140"
                                                        image="src/main/frontend/public/images/명동역 .jpeg"
                                                    />
                                                </CardContent>
                                            </CardActionArea>
                                        </Card>

                                        <Card sx={{ maxWidth: 600, mb : 2}}>
                                            <CardActionArea>
                                                <CardContent>
                                                    <Typography gutterBottom variant="h5" component="div">
                                                        최근 사진
                                                    </Typography>
                                                    <CardMedia
                                                        component="img"
                                                        alt="최근 사진"
                                                        height="140"
                                                        image="frontend/src/components/TestBoard/images/명동역 .jpeg"
                                                    />
                                                </CardContent>
                                            </CardActionArea>
                                        </Card>
                                    </div>
                                </Box>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );

};

export default Gu;
