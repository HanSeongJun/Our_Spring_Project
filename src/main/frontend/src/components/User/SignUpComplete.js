import * as React from 'react';
import Button from '@mui/material/Button';
import Navbar from "../Navbar";
import {Link} from "react-router-dom";

import './styles/SignUpComplete.css';

export default function SignUpComplete() {
    return (
        <div>
            <Navbar />
            <div className="container">
                <h1 className="title">회원가입이 완료되었습니다.</h1>
                <Link to="/home">
                    <Button variant="contained" size="large">
                        홈으로
                    </Button>
                </Link>
            </div>
        </div>
    );
}