import * as React from 'react';
import { Container, Paper, Typography, Box, Button, Grid } from '@mui/material';
import {Link} from "react-router-dom";

import './styles/MyPage.css';

export default function MyPage() {
    return (
            <div>
                <Container sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                    <Paper elevation={3} className="paperStyle">
                        <Box className="titleWrapper">
                            <Typography variant="h5" >내 정보</Typography>
                            <Link to="/user/editUser">
                                <Button variant="contained" color="primary">
                                    변경
                                </Button>
                            </Link>
                        </Box>

                        <Grid container spacing={2}>
                            <Grid item xs={6}>
                                <Typography variant="h6">아이디</Typography>
                                <Typography variant="body1">gkstjdwns2</Typography>
                            </Grid>
                            <Grid item xs={6}>
                                <Typography variant="h6">비밀번호</Typography>
                                <Typography variant="body1">********</Typography>
                            </Grid>
                            <Grid item xs={6}>
                                <Typography variant="h6">닉네임</Typography>
                                <Typography variant="body1">John Doe</Typography>
                            </Grid>
                            <Grid item xs={6}>
                                <Typography variant="h6">이메일</Typography>
                                <Typography variant="body1">johndoe@example.com</Typography>
                            </Grid>
                        </Grid>
                    </Paper>
                </Container>
            </div>
    );
}