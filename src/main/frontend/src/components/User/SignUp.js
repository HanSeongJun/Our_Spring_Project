import React, { useState } from 'react';
import { Container, TextField, Box, Button } from '@mui/material';
import EmailValidator from 'email-validator';

import './styles/SignUp.css';

const SignUp = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        confirmPassword: '',
        nickname: '',
        email: '',
        emailConfirm: '',
    });

    const [emailConfirm, setEmailConfirm] = useState('');
    const [errorMsg, setErrorMsg] = useState('');
    const [textFieldColor, setTextFieldColor] = useState('primary');

    const validateEmail = (email) => {
        if (!EmailValidator.validate(email)) {
            setErrorMsg('올바른 이메일 형식이 아닙니다.');
            setTextFieldColor('error');
            return false;
        }
        return true;
    };

    const handleEmailVerification = () => {
        if (!validateEmail(formData.email.trim())) {
            return;
        }

        fetch(`/user/emailConfirm?email=${formData.email}`, {
            method: 'POST',
        })
            .then((response) => response.text())
            .then((data) => {
                setEmailConfirm(data);
                setErrorMsg('인증번호가 전송되었습니다.');
                setTextFieldColor('primary');
            })
            .catch((error) => {
                console.error(error);
                setErrorMsg('인증번호 전송에 실패했습니다.');
                setTextFieldColor('error');
            });
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });

        if (name === 'email' && value.trim() !== '') {
            if (!validateEmail(value)) {
                return;
            }

            fetch(`/user/checkEmail?email=${value}`)
                .then((response) => response.json())
                .then((data) => {
                    if (data.exists) {
                        setErrorMsg('이미 존재하는 이메일입니다.');
                        setTextFieldColor('error');
                    } else {
                        setErrorMsg('사용 가능한 이메일입니다.');
                        setTextFieldColor('success');
                    }
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (formData.password !== formData.confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        try {
            await fetch('/user/signUp', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

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
                            error={formData.password !== formData.confirmPassword}
                            helperText={
                                formData.password !== formData.confirmPassword
                                    ? '비밀번호가 일치하지 않습니다.'
                                    : ''
                            }
                            value={formData.confirmPassword}
                            onChange={handleInputChange}
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
                                color={textFieldColor}
                                error={errorMsg}
                                helperText={errorMsg}
                                value={formData.email}
                                onChange={handleInputChange}
                            />
                            <Button
                                variant="contained"
                                className="button email-verify"
                                onClick={handleEmailVerification}
                            >
                                인증번호 받기
                            </Button>
                        </Box>
                        <Box className="email-field">
                            <TextField
                                label="인증번호"
                                name="emailConfirm"
                                className="input email-input"
                            />
                            <Button
                                variant="contained"
                                className="button email-verify"
                            >
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
