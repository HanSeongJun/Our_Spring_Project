import { useState } from 'react';
import { Container, TextField, Box } from '@mui/material';
import Button from '@mui/material/Button';
import { useNavigate } from "react-router-dom";

import './styles/Login.css';

export default function Login() {

    const navigate = useNavigate();

    const [isLoggedIn, setIsLoggedIn] = useState(sessionStorage.getItem('loginUser') === 'true');

    const handleSubmit = async (event) => {
        event.preventDefault(); // 기본 동작 방지
        const formData = new FormData(event.target);
        const userDto = {
            username: formData.get('username'),
            password: formData.get('password'),
        };
        const response = await fetch('/user/logIn', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userDto), // 요청 데이터를 JSON 형태로 변환
        });
        const result = await response.json();
        if (result.message === 'login complete') {
            sessionStorage.setItem('loginUser', 'true');
            setIsLoggedIn(true);
            navigate('/home'); // 로그인 성공 시 /home으로 이동
        }
    };

    return (
        <div className="container">
            <div className="containerWrapper">
                <Container>
                    <Box className="form">
                        <form onSubmit={handleSubmit}>
                            <TextField label="아이디" name="username" className="input" />
                            <TextField label="비밀번호" name="password" type="password" className="input" />
                            <Button type="submit" variant="contained" size="large">
                                로그인
                            </Button>
                        </form>
                    </Box>
                </Container>
            </div>
        </div>
    );
}
