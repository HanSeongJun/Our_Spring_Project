import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';
import '/Users/zzoonsmac/Desktop/My_Project/Our_Spring_Project/src/main/frontend/src/components/Map/styles/Navbar.css'

export default function Navbar() {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" color="primary">
                <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
                    <Link to="/home" className="navbar-link">
                        <Button color="inherit">Home</Button>
                    </Link>
                    <div>
                        <Link to="/user/login" className="navbar-link">
                            <Button color="inherit">로그인</Button>
                        </Link>
                        <Link to="/user/signUp" className="navbar-link">
                            <Button color="inherit">회원가입</Button>
                        </Link>
                    </div>
                </Toolbar>
            </AppBar>
        </Box>
    );
}
