import * as React from 'react';

import './styles/NewUserPassword.css';
import {Box, TextField, Typography} from "@mui/material";
import Button from "@mui/material/Button";
import {Link} from "react-router-dom";

export default function NewUserPassword() {

    return (
        <div className="container">
            <div className="containerWrapper">
                <Typography >새 비밀번호 설정</Typography>
                <Box className="field" sx={{ mb: 3 }}>
                    <TextField id="password" label="비밀번호" type="password" className="input" required/>
                </Box>
                <Box className="field" sx={{ mb: 3 }}>
                    <TextField id="confirmPassword" label="비밀번호 확인" type="password" className="input" required/>
                </Box>
                <Link to="/user/editUserComplete">
                    <Button type="submit" variant="contained" size="large" className="input" sx={{ mb: 3 }}>
                        비밀번호 변경하기
                    </Button>
                </Link>
            </div>
        </div>
    );
}