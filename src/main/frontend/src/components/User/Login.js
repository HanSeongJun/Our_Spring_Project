import { useState } from 'react';
import { Container, TextField, Box } from '@mui/material';
import Button from '@mui/material/Button';
import { useNavigate } from "react-router-dom";

import './styles/Login.css';

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
                        </form>
                    </Box>
                </Container>
            </div>
        </div>
    );
}
