import * as React from 'react';
import {Link} from "react-router-dom";
import Button from "@mui/material/Button";

import './styles/FindUserId.css';

export default function FindUserId() {

    return (
        <div className="container">
            <div className="containerWrapper">
                <Link to="/home">
                    <Button variant="contained" size="large">
                        홈으로
                    </Button>
                </Link>
            </div>
        </div>
    );
}