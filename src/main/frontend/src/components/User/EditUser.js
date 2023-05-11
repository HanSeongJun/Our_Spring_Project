import * as React from 'react';
import { Container, TextField, Box } from '@mui/material';
import Button from '@mui/material/Button';
import { Link } from "react-router-dom";

import './styles/EditUser.css';
import { useEffect, useState } from "react";

export default function EditUser() {

    const [user, setUser] = useState(null);

    useEffect(() => {
        async function UserData() {
            const response = await fetch('/user/myPage');
            const userData = await response.json();
            setUser(userData);
        }
        UserData();
    }, []);

    // 아이디
    const [username, setUsername] = useState('');
    const [isUsernameAvailable, setIsUsernameAvailable] = useState(true);
    const [usernameError, setUsernameError] = useState('');

    // 아이디 입력 필드의 값이 변경될 때마다 실행됨
    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    // useEffect를 사용하여 아이디 입력 값이 변경될 때마다 중복 여부를 요청함
    useEffect(() => {
        // 입력 값이 빈 문자열일 경우 중복 체크를 하지 않음
        if (username === '') {
            setIsUsernameAvailable(true);
            setUsernameError('');
            return;
        }

        // 중복 체크 요청
        fetch(`/user/checkUserName?username=${username}`)
            .then((response) => response.json())
            .then((data) => {
                setIsUsernameAvailable(!data.exists);
                setUsernameError(data.exists ? '이미 존재하는 아이디입니다.' : '');
            })
            .catch((error) => {
                console.error(error);
            });
    }, [username]);

    // 닉네임
    const [nickname, setNickname] = useState('');
    const [isNicknameAvailable, setIsNicknameAvailable] = useState(true);
    const [nicknameError, setNicknameError] = useState('');

    // 아이디 입력 필드의 값이 변경될 때마다 실행됨
    const handleNicknameChange = (event) => {
        setNickname(event.target.value);
    };

    // useEffect를 사용하여 아이디 입력 값이 변경될 때마다 중복 여부를 요청함
    useEffect(() => {
        // 입력 값이 빈 문자열일 경우 중복 체크를 하지 않음
        if (nickname === '') {
            setIsNicknameAvailable(true);
            setNicknameError('');
            return;
        }

        // 중복 체크 요청
        fetch(`/user/checkNickName?nickname=${nickname}`)
            .then((response) => response.json())
            .then((data) => {
                setIsNicknameAvailable(!data.exists);
                setNicknameError(data.exists ? '이미 존재하는 아이디입니다.' : '');
            })
            .catch((error) => {
                console.error(error);
            });
    }, [nickname]);

    const handleSubmit = async (event) => {
        event.preventDefault();

        const data = {
            username: document.getElementById("username").value,
            password: document.getElementById("password").value,
            confirmPassword: document.getElementById("confirmPassword").value,
            nickname: document.getElementById("nickname").value,
            email: user.email, // user 객체에서 email 값을 가져와서 추가합니다.
        };

        if (data.password !== data.confirmPassword) {
            // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        try {
            const response = await fetch("/user/update", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });

            if (response.ok) {
                window.location.href = "/user/editUserComplete";
            } else {
                alert("회원 정보 수정에 실패했습니다.");
            }
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container">
            <div className="containerWrapper">
                <Container>
                    <Box className="form">
                        {user ? <TextField
                            id="username"
                            label="아이디"
                            type="text"
                            defaultValue={user.username}
                            className="input"
                            error={!!usernameError}
                            helperText={usernameError}
                            color={isUsernameAvailable ? 'primary' : 'error'}
                            onChange={handleUsernameChange}
                            required
                        /> : null}
                        {user ? <TextField id="password" label="비밀번호" type="password" defaultValue={user.password} className="input" required/> : null}
                        <TextField id="confirmPassword" label="비밀번호 확인" type="password" className="input"
                                   required/>
                        {user ? <TextField
                            id="nickname"
                            label="닉네임"
                            type="text" defaultValue={user.nickname}
                            className="input"
                            error={!!nicknameError}
                            helperText={nicknameError}
                            color={isNicknameAvailable ? 'primary' : 'error'}
                            onChange={handleNicknameChange}
                            required/> : null}
                        {user ? <TextField label="이메일" type="text" defaultValue={user.email} className="input" disabled/> : null}
                        <Button variant="contained" size="large" className="button" onClick={handleSubmit}>
                            변경하기
                        </Button>
                        <Link to="/user/myPage" className="button">
                            <Button variant="contained" size="large" className="button">
                                취소
                            </Button>
                        </Link>
                    </Box>
                </Container>
            </div>
        </div>
    );
}