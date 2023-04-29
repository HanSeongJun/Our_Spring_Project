import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import MyHomeButton from "./HomeButton";

export default function Navbar(props) {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" color="primary">
                <Toolbar sx={{ display: 'flex'}}>
                    <MyHomeButton />
                    <Box className = "SpotName" sx={{flexGrow: 1, display: 'flex', justifyContent: 'center' }}>
                        <p>[{props.city_code}] {props.gu_code}</p>
                    </Box>
                </Toolbar>
            </AppBar>
        </Box>
    );
}