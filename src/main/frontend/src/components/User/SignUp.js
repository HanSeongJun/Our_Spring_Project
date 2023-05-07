import React, { useState } from 'react';
import { Container, TextField, Box, Button } from '@mui/material';
import './styles/SignUp.css';

const SignUp = () => {
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

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (formData.password !== formData.confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        try {
            const response = await fetch('/user/signUp', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            const data = await response.text();
            console.log(data);
            window.location.href = '/user/signUpComplete';
        } catch (error) {
            console.error(error);
        }
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
                            error={formData.password !== formData.confirmPassword}
                            helperText={
                                formData.password !== formData.confirmPassword
                                    ? '비밀번호가 일치하지 않습니다.'
                                    : ''
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
                        <Box className="email-field">
                            <TextField
                                label="인증번호"
                                name="emailConfirm"
                                className="input email-input"
                            />
                            <Button variant="contained" className="button email-verify">
                                인증
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
};

export default SignUp;
