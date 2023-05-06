import * as React from 'react';
import { useState } from 'react';
import { Container, TextField, Box } from '@mui/material';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';

import './styles/SignUp.css';

export default function SignUp() {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        confirmPassword: '',
        nickname: '',
        email: '',
    });

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (formData.password !== formData.confirmPassword) {
            // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        // 회원가입 데이터를 백엔드로 전송
        fetch('/user/signUp', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        })
            .then((response) => response.text())
            .then((data) => {
                console.log(data);
                // 회원가입 완료 폼으로 이동
                window.location.href = '/user/signUpComplete';
            })
            .catch((error) => {
                console.error(error);
            });
    };

    return (
        <div className="container">
            <div className="container-wrapper">
                <Container>
                    <Box className="form">
                        <TextField
                            label="아이디"
                            name="username"
                            className="input"
                            value={formData.username}
                            onChange={handleInputChange}
                        />
                        <TextField
                            label="비밀번호"
                            name="password"
                            type="password"
                            className="input"
                            value={formData.password}
                            onChange={handleInputChange}
                        />
                        <TextField
                            label="비밀번호 확인"
                            name="confirmPassword"
                            type="password"
                            className="input"
                            value={formData.confirmPassword}
                            onChange={handleInputChange}
                            error={formData.password !== formData.confirmPassword} // 오류 상태 여부
                            helperText={
                                formData.password !== formData.confirmPassword
                                    ? '비밀번호가 일치하지 않습니다.'
                                    : '' // 오류 메시지
                            }
                        />
                        <TextField
                            label="닉네임"
                            name="nickname"
                            className="input"
                            value={formData.nickname}
                            onChange={handleInputChange}
                        />
                        <Box className="email-field">
                            <TextField
                                label="이메일"
                                name="email"
                                className="input email-input"
                                value={formData.email}
                                onChange={handleInputChange}
                            />
                            <Button variant="contained" className="button email-verify">
                                인증번호 받기
                            </Button>
                        </Box>
                        <Button
                            variant="contained"
                            size="large"
                            className="button submit-btn"
                            onClick={handleSubmit}
                        >
                            회원가입
                        </Button>
                    </Box>
                </Container>
            </div>
        </div>
    );
}