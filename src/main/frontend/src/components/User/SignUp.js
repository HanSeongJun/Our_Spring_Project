import * as React from 'react';
import { useState } from 'react';
import { Container, TextField, Box } from '@mui/material';
import Button from '@mui/material/Button';
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

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    // 아이디
    const [userNameErrorMsg, setUserNameErrorMsg] = useState('');
    const [userNameTextFieldColor, setUserNameTextFieldColor] = useState('primary');

    const handleUserNameInputChange = (event) => {
        const { name: username, value } = event.target;
        setFormData({ ...formData, [username]: value });

        // 아이디 중복 체크
        if (username === 'username' && value.trim() !== '') {
            fetch(`/user/checkUserName?username=${value}`)
                .then((response) => response.json())
                .then((data) => {
                    if (data.exists) {
                        setUserNameErrorMsg('이미 존재하는 아이디입니다.');
                        setUserNameTextFieldColor('error');
                    } else {
                        setUserNameErrorMsg('사용 가능한 아이디입니다.');
                        setUserNameTextFieldColor('success');
                    }
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    };

    // 닉네임
    const [nickNameErrorMsg, setNickNameErrorMsg] = useState('');
    const [nickNameTextFieldColor, setNickNameTextFieldColor] = useState('primary');

    const handleNickNameInputChange = (event) => {
        const { name: nickname, value } = event.target;
        setFormData({ ...formData, [nickname]: value });

        // 닉네임 중복 체크
        if (nickname === 'nickname' && value.trim() !== '') {
            fetch(`/user/checkNickName?nickname=${value}`)
                .then((response) => response.json())
                .then((data) => {
                    if (data.exists) {
                        setNickNameErrorMsg('이미 존재하는 닉네임입니다.');
                        setNickNameTextFieldColor('error');
                    } else {
                        setNickNameErrorMsg('사용 가능한 닉네임입니다.');
                        setNickNameTextFieldColor('success');
                    }
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    };

    // 이메일
    const [emailConfirm, setEmailConfirm] = useState('');
    const [emailErrorMsg, setEmailErrorMsg] = useState('');
    const [emailTextFieldColor, setEmailTextFieldColor] = useState('primary');
    const [emailVerificationDisabled, setEmailVerificationDisabled] = useState(false);

    // 이메일 형식 검사
    const validateEmail = (email) => {
        if (!EmailValidator.validate(email)) {
            setEmailErrorMsg('올바른 이메일 형식이 아닙니다.');
            setEmailTextFieldColor('error');
            return false;
        }
        return true;
    };

    const handleEmailInputChange = (event) => {
        const { name: email, value } = event.target;
        setFormData({ ...formData, [email]: value });

        // 이메일 중복 체크
        if (email === 'email' && value.trim() !== '') {
            if (!validateEmail(value)) {
                return;
            }
            fetch(`/user/checkEmail?email=${value}`)
                .then((response) => response.json())
                .then((data) => {
                    if (data.exists) {
                        setEmailErrorMsg('이미 존재하는 이메일입니다.');
                        setEmailTextFieldColor('error');
                        setEmailVerificationDisabled(true);
                    } else {
                        setEmailTextFieldColor('success');
                        setEmailErrorMsg('사용 가능한 이메일입니다.');
                        setEmailVerificationDisabled(false);
                    }
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    }

    const handleEmailVerification = () => {
        if (!validateEmail(formData.email.trim())) {
            return;
        }

        fetch(`/user/emailConfirm?email=${formData.email}`, {
            method: 'POST',
        })
            .then((response) => response.text())
            .then((data) => {
                if (data.message === 'Invalid email format') {
                    setEmailErrorMsg('잘못된 이메일 형식입니다.');
                    setEmailTextFieldColor('error');
                } else {
                    const response = JSON.parse(data);
                    if (response.exists) {
                        setEmailVerificationDisabled(true); // 버튼 비활성화
                    } else {
                        setEmailConfirm(response.confirm);
                        setEmailErrorMsg('인증번호가 전송되었습니다.');
                        setEmailTextFieldColor('primary');
                    }
                }
            })
            .catch((error) => {
                console.error(error);
                setEmailErrorMsg('인증번호 전송에 실패했습니다.');
                setEmailTextFieldColor('error');
            });
    };

    // 인증번호 검증
    const [emailConfirmErrorMsg, setEmailConfirmErrorMsg] = useState('');
    const [emailConfirmTextFieldColor, setEmailConfirmTextFieldColor] = useState('primary');

    const handleEmailConfirm = () => {
        if (formData.emailConfirm !== emailConfirm) {
            setEmailConfirmErrorMsg('인증번호가 일치하지 않습니다.');
            setEmailConfirmTextFieldColor('error');
        } else {
            setEmailConfirmErrorMsg('인증이 완료되었습니다.');
            setEmailConfirmTextFieldColor('primary');
        }
    };

    // 회원가입 버튼
    const handleSubmit = async (event) => {
        event.preventDefault();

        // 이메일 인증 확인
        if (!emailConfirm) {
            alert('이메일 인증이 완료되지 않았습니다.');
            return;
        }

        if (formData.emailConfirm !== emailConfirm) {
            alert('이메일 인증이 완료되지 않았습니다.');
            return;
        }

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
                            error={userNameErrorMsg === '이미 존재하는 아이디입니다.'}
                            helperText={userNameErrorMsg}
                            color={userNameTextFieldColor}
                            value={formData.username}
                            onChange={handleUserNameInputChange}
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
                            error={formData.password !== formData.confirmPassword} // 오류 상태 여부
                            helperText={
                                formData.password !== formData.confirmPassword
                                    ? '비밀번호가 일치하지 않습니다.'
                                    : '' // 오류 메시지
                            }
                            value={formData.confirmPassword}
                            onChange={handleInputChange}
                        />
                        <TextField
                            label="닉네임"
                            name="nickname"
                            className="input"
                            error={nickNameErrorMsg === '이미 존재하는 닉네임입니다.'}
                            helperText={nickNameErrorMsg}
                            color={nickNameTextFieldColor}
                            value={formData.nickname}
                            onChange={handleNickNameInputChange}
                        />
                        <Box className="email-field">
                            <TextField
                                label="이메일"
                                name="email"
                                className="input email-input"
                                error={emailErrorMsg === '이미 존재하는 이메일입니다.' || emailErrorMsg === '올바른 이메일 형식이 아닙니다.'}
                                helperText={emailErrorMsg}
                                color={emailTextFieldColor}
                                value={formData.email}
                                onChange={handleEmailInputChange}
                            />
                            <Button
                                variant="contained"
                                className="button email-verify"
                                disabled={emailVerificationDisabled}
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
                                onChange={(event) => setFormData({ ...formData, emailConfirm: event.target.value })}
                                error={!!emailConfirmErrorMsg}
                                helperText={emailConfirmErrorMsg}
                                color={emailConfirmTextFieldColor}
                            />
                            <Button
                                variant="contained"
                                className="button email-verify"
                                onClick={handleEmailConfirm}
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