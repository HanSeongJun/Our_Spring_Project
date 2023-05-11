import {Container, TextField, Box, IconButton} from '@mui/material';
import Button from '@mui/material/Button';
import {Link, useNavigate} from "react-router-dom";

import './styles/Login.css';
import * as React from "react";

export default function Login() {

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault(); // 기본 동작 방지
        const formData = new FormData(event.target);
        const userDto = {
            username: formData.get('username'),
            password: formData.get('password'),
        };
        try {
            const response = await fetch('/user/logIn', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userDto),
            });

            const result = await response.json();

            if (response.ok) {
                sessionStorage.setItem('loginUser', 'true');
                navigate('/home');
            } else {
                alert(result.message);
            }
        } catch (error) {
            console.error(error);
            alert('로그인에 실패하였습니다.');
        }
    };

    return (
        <div className="container">
            <div className="containerWrapper">
                <Container>
                    <Box className="form">
                        <form onSubmit={handleSubmit}>
                            <TextField label="아이디" name="username" className="input" sx={{ mb: 2 }}/>
                            <TextField label="비밀번호" name="password" type="password" className="input" sx={{ mb: 2 }}/>
                            <Button type="submit" variant="contained" size="large" className="input">
                                로그인
                            </Button>
                            <div className="buttonWrapper">
                                <Link to="/forgotUsername">
                                    <Button variant="text">
                                        아이디 찾기
                                    </Button>
                                </Link>
                                <Link to="/forgotPassword">
                                    <Button variant="text">
                                        비밀번호 찾기
                                    </Button>
                                </Link>
                                <Link to="/user/signUp">
                                    <Button variant="text" sx={{ ml: 17 }}>
                                        회원가입
                                    </Button>
                                </Link>
                            </div>
                        </form>
                    </Box>
                </Container>
            </div>
        </div>
    );
}
