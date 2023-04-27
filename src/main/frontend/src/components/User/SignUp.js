import * as React from 'react';
import { Container, TextField, Box } from '@mui/material';
import Button from '@mui/material/Button';
import {Link} from "react-router-dom";

import './styles/SignUp.css';

export default function SignUp() {
    return (
            <div className="container">
                <div className="container-wrapper">
                    <Container>
                        <Box className="form">
                            <TextField label="아이디" className="input" />
                            <TextField label="비밀번호" className="input" />
                            <TextField label="비밀번호 확인" className="input" />
                            <TextField label="닉네임" className="input" />
                            <Box className="email-field">
                                <TextField label="이메일" className="input email-input" />
                                <Button variant="contained" className="button email-verify">
                                    인증번호 받기
                                </Button>
                            </Box>
                            <Link to="/user/signUpComplete">
                                <Button variant="contained" size="large" className="button submit-btn">
                                    회원가입
                                </Button>
                            </Link>
                        </Box>
                    </Container>
                </div>
            </div>
    );
}
