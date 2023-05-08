import * as React from 'react';
import { Container, Paper, Typography, Box, Button, Grid } from '@mui/material';
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';

import './styles/MyPage.css';

export default function MyPage() {
    const [user, setUser] = useState(null);

    async function fetchUserData() {
        const response = await fetch('/user/myPage');
        const userData = await response.json();
        if (userData === null) {
            // 로그인하지 않은 경우 처리
            console.log('로그인이 필요합니다.');
        } else {
            // 로그인한 경우, 사용자 정보를 불러옴
            setUser(userData);
        }
    }

    useEffect(() => {
        fetchUserData();
    }, []);

    return (
        <div>
            <Container sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <Paper elevation={3} className="paperStyle">
                    <Box className="titleWrapper">
                        <Typography variant="h5">내 정보</Typography>
                        <Link to="/user/editUser">
                            <Button variant="contained" color="primary">
                                변경
                            </Button>
                        </Link>
                    </Box>
                    <Grid container spacing={2}>
                        <Grid item xs={6}>
                            <Typography variant="h6">아이디</Typography>
                            {user ? <Typography variant="body1">{user.username}</Typography> : null}
                        </Grid>
                        <Grid item xs={6}>
                            <Typography variant="h6">비밀번호</Typography>
                            {/* 비밀번호는 노출되면 안 되므로 null 대신 *** 출력 */}
                            {user ? <Typography variant="body1">{'*'.repeat(user.password.length)}</Typography> : null}
                        </Grid>
                        <Grid item xs={6}>
                            <Typography variant="h6">닉네임</Typography>
                            {user ? <Typography variant="body1">{user.nickname}</Typography> : null}
                        </Grid>
                        <Grid item xs={6}>
                            <Typography variant="h6">이메일</Typography>
                            {user ? <Typography variant="body1">{user.email}</Typography> : null}
                        </Grid>
                    </Grid>
                </Paper>
            </Container>
        </div>
    );
}
