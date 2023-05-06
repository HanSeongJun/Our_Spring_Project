import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { Link, useLocation } from 'react-router-dom';

import './Navbar.css'

export default function Navbar() {

    const [isLoggedIn, setIsLoggedIn] = React.useState(sessionStorage.getItem('loginUser') === 'true');
    const location = useLocation();

    React.useEffect(() => {
        setIsLoggedIn(sessionStorage.getItem('loginUser') === 'true');
    }, [location]);

    const handleLogout = () => {
        sessionStorage.removeItem('loginUser');
        setIsLoggedIn(false);
    };

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" color="primary">
                <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
                    <Link to="/home" className="navbar-link">
                        <Button color="inherit">Home</Button>
                    </Link>
                    {isLoggedIn ? (
                        <div>
                            <Link to="/user/myPage" className="navbar-link">
                                <Button color="inherit">마이페이지</Button>
                            </Link>
                            <Link to="/home" className="navbar-link">
                                <Button color="inherit" onClick={handleLogout}>로그아웃</Button>
                            </Link>
                        </div>
                    ) : (
                        <div>
                            <Link to="/user/login" className="navbar-link">
                                <Button color="inherit">로그인</Button>
                            </Link>
                            <Link to="/user/signUp" className="navbar-link">
                                <Button color="inherit">회원가입</Button>
                            </Link>
                        </div>
                    )}
                </Toolbar>
            </AppBar>
        </Box>
    );
}
