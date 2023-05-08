import React, { useState, useEffect } from 'react';
import { Container, Paper, Typography, Box, Button, Grid } from '@mui/material';
import { Link } from 'react-router-dom';

import './styles/MyPage.css';

const MyPage = () => {
    const [user, setUser] = useState(null);

    const fetchUserData = async () => {
        try {
            const response = await fetch('/user/myPage');
            const userData = await response.json();
            if (userData === null) {
                console.log('로그인이 필요합니다.');
            } else {
                setUser(userData);
            }
        } catch (error) {
            console.error(error);
        }
    };

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
                            {user && <Typography variant="body1">{user.username}</Typography>}
                        </Grid>
                        <Grid item xs={6}>
                            <Typography variant="h6">비밀번호</Typography>
                            {user && <Typography variant="body1">{'*'.repeat(user.password.length)}</Typography>}
                        </Grid>
                        <Grid item xs={6}>
                            <Typography variant="h6">닉네임</Typography>
                            {user && <Typography variant="body1">{user.nickname}</Typography>}
                        </Grid>
                        <Grid item xs={6}>
                            <Typography variant="h6">이메일</Typography>
                            {user && <Typography variant="body1">{user.email}</Typography>}
                        </Grid>
                    </Grid>
                </Paper>
            </Container>
        </div>
    );
};

export default MyPage;
