import * as React from 'react';
import {Box, TextField, Typography} from "@mui/material";
import Button from "@mui/material/Button";
import {Link} from "react-router-dom";

export default function FindPassword() {

    return (
        <div className="container">
            <div className="containerWrapper">
                <Typography >비밀번호 찾기</Typography>
                <Box className="email-field" sx={{ mb: 3 }}>
                    <TextField label="아이디" name="username" className="input email-input"/>
                </Box>
                <Box className="email-field" sx={{ mb: 3 }}>
                    <TextField label="이메일" name="email" className="input email-input"/>
                    <Button variant="contained" className="button email-verify">
                        인증번호 받기
                    </Button>
                </Box>
                <Box className="email-field" sx={{ mb: 3 }}>
                    <TextField label="인증번호" name="emailConfirm" className="input email-input"/>
                    <Button variant="contained" className="button email-verify">
                        인증
                    </Button>
                </Box>
                <Link to="/user/FindUserId">
                    <Button type="submit" variant="contained" size="large" className="input">
                        비밀번호 찾기
                    </Button>
                </Link>
            </div>
        </div>
    );
}