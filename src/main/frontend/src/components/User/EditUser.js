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
                        {user ? <TextField id="username" label="아이디" className="input" defaultValue={user.username} /> : null}
                        {user ? <TextField id="password" label="비밀번호" type="password" className="input" defaultValue={user.password} /> : null}
                        <TextField id="confirmPassword" label="비밀번호 확인" type="password" className="input"/>
                        {user ? <TextField id="nickname" label="닉네임" className="input" defaultValue={user.nickname} /> : null}
                        {user ? <TextField label="이메일" className="input" disabled defaultValue={user.email} /> : null}
                        <Button variant="contained" size="large" className="button" onClick={handleSubmit}>
                            변경하기
                        </Button>
                        <Link to="/user/myPage" className="link">
                            취소
                        </Link>
                    </Box>
                </Container>
            </div>
        </div>
    );
}