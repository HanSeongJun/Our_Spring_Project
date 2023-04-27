import * as React from 'react';
import { Container, TextField, Box } from '@mui/material';
import Button from '@mui/material/Button';

import styles from './styles/EditUser.css';
import Navbar from "../Navbar";
import {Link} from "react-router-dom";

export default function EditUser() {
    return (
        <div>
            <Navbar />
            <div className="container">
                <div className="containerWrapper">
                    <Container>
                        <Box className="form">
                            <TextField label="아이디" className="input"/>
                            <TextField label="비밀번호" className="input" />
                            <TextField label="비밀번호 확인" className="input" />
                            <TextField label="닉네임" className="input"/>
                            <TextField label="이메일"
                                       className="input"
                                       disabled
                                       defaultValue="gkstjdwns2@naver.com" />
                            <Link to="/user/myPage">
                                <Button variant="contained" size="large" className="button">
                                    변경하기
                                </Button>
                            </Link>
                        </Box>
                    </Container>
                </div>
            </div>
        </div>
    );
}
