import * as React from 'react';

import './styles/FindId.css';
import {Box, TextField, Typography} from "@mui/material";
import Button from "@mui/material/Button";

export default function FindId() {

    return (
        <div className="container">
            <div className="containerWrapper">
                <Typography >이메일 인증하기</Typography>
                <Box className="email-field" sx={{ mb: 3 }}>
                    <TextField label="이메일" name="email" className="input email-input"/>
                    <Button
                        variant="contained" className="button email-verify" sx={{ marginLeft: 2 }}>
                        인증번호 받기
                    </Button>
                </Box>
                <Box className="email-field" sx={{ mb: 3 }}>
                    <TextField label="인증번호" name="emailConfirm" className="input email-input" />
                    <Button variant="contained" className="button email-verify" sx={{ marginLeft: 2 }}>
                        인증
                    </Button>
                </Box>
                <Button type="submit" variant="contained" size="large" className="input">
                    아이디 찾기
                </Button>
            </div>
        </div>
    )
};
