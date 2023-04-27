import * as React from 'react';
import { Container, TextField, Box } from '@mui/material';
import Button from '@mui/material/Button';
import {Link} from "react-router-dom";

import './styles/Login.css';

export default function LogIn() {
    return (
            <div className="container">
                <div className="containerWrapper">
                    <Container>
                        <Box className="form">
                            <TextField label="아이디" className="input" />
                            <TextField label="비밀번호" className="input" />
                            <Link to="/home" width='100%'>
                                <Button variant="contained" size="large">
                                    로그인
                                </Button>
                            </Link>
                        </Box>
                    </Container>
                </div>
            </div>
    );
}
