import * as React from 'react';
import Button from '@mui/material/Button';
import {useNavigate} from "react-router-dom"

const MyHomeButton = () => {
    const navigate = useNavigate();
    const goBack = () => {
        navigate("/home");
    }

    return (
        <Button color = "inherit" onClick={goBack}>Home</Button>
    );

}

export default MyHomeButton;