import * as React from 'react';
import Button from '@mui/material/Button';
import {useNavigate} from "react-router-dom"

const BackButton = () => {
    const navigate = useNavigate();
    const goBack = () => {
        navigate(-1);
    }

    return (
        <Button color = "inherit" onClick={goBack}>BACK</Button>
    );

}

export default BackButton;