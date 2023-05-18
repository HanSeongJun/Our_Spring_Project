import * as React from 'react';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';

import './styles/FindUserId.css';

export default function FindUserId() {
    const [userId, setUserId] = React.useState('');

    React.useEffect(() => {
        const verifiedEmail = sessionStorage.getItem('verifiedEmail');

        fetch(`/user/findUserId?email=${verifiedEmail}`)
            .then((response) => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('User ID not found for the verified email');
                }
            })
            .then((data) => {
                setUserId(data || ''); // 빈 문자열로 설정하여 데이터가 없는 경우 처리
            })
            .catch((error) => {
                console.error(error);
                setUserId('');
            });
    }, []);
    return (
        <div className="container">
            <div className="containerWrapper">
                {userId ? (
                    <>
                        <h1>Your User ID</h1>
                        <p>{userId}</p>
                    </>
                ) : (
                    <p>No user ID found for the verified email.</p>
                )}
                <Link to="/home">
                    <Button variant="contained" size="large">
                        Home
                    </Button>
                </Link>
            </div>
        </div>
    );
}
