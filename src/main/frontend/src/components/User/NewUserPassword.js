import React, {useEffect, useState} from 'react';
import { Box, TextField, Typography } from '@mui/material';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';

export default function NewUserPassword() {
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleConfirmPasswordChange = (event) => {
        setConfirmPassword(event.target.value);
    };

    const handleFormSubmit = (event) => {
        event.preventDefault();

        // Perform validation
        if (password !== confirmPassword) {
            setErrorMessage("Passwords don't match");
            return;
        }

        // Make API request to reset the password
        const email = localStorage.getItem('resetEmail');

        // Make the API call to update the password
        fetch(`/user/reset-password?email=${email}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: `${password}`, // Wrap newPassword in an object
        })
            .then((response) => {
                if (response.ok) {
                    // Password reset successfully
                    // Redirect to a success page
                    window.location.href = '/user/editUserComplete';
                } else {
                    // Handle the error response
                    setErrorMessage('Failed to reset the password. Please try again.');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                setErrorMessage('Failed to reset the password. Please try again.');
            });
    };

    return (
        <div className="container">
            <div className="containerWrapper">
                <Typography>새 비밀번호 설정</Typography>
                <Box className="field" sx={{ mb: 3 }}>
                    <TextField
                        id="password"
                        label="비밀번호"
                        type="password"
                        className="input"
                        required
                        value={password}
                        onChange={handlePasswordChange}
                    />
                </Box>
                <Box className="field" sx={{ mb: 3 }}>
                    <TextField
                        id="confirmPassword"
                        label="비밀번호 확인"
                        type="password"
                        className="input"
                        required
                        value={confirmPassword}
                        onChange={handleConfirmPasswordChange}
                    />
                </Box>
                {errorMessage && (
                    <Typography variant="body2" color="error" sx={{ mb: 3 }}>
                        {errorMessage}
                    </Typography>
                )}
                <Button
                    type="submit"
                    variant="contained"
                    size="large"
                    className="input"
                    sx={{ mb: 3 }}
                    onClick={handleFormSubmit}
                >
                    비밀번호 변경하기
                </Button>
            </div>
        </div>
    );
}
