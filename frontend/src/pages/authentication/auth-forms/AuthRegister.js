import React, { useState } from 'react';
import { TextField, Container, Button } from '@mui/material';
import { fetchPostData } from 'clients/client';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';

const AuthRegister = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState({ email: '', password: '' });
  const [loginError, setLoginError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const isLoggedIn = localStorage.getItem('token');
    if (isLoggedIn) {
      navigate('/');
      window.location.reload();
    }
  }, [navigate]);

  const validateEmail = () => {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
  };

  const validatePassword = () => {
    return password.length >= 6 && password.length <= 20;
  };

  const handleLogin = async () => {
    setErrors({ email: '', password: '' });

    // Validate email
    if (!validateEmail()) {
      setErrors((prevErrors) => ({ ...prevErrors, email: 'Invalid email address' }));
      return;
    }

    // Validate password
    if (!validatePassword()) {
      setErrors((prevErrors) => ({ ...prevErrors, password: 'Password must be at least 6 characters' }));
      return;
    }

    fetchPostData('/auth/users/add', { email, password })
      .then(() => {
        setLoginError('');
        navigate('/login');
         window.location.reload();
      })
      .catch((error) => {
        console.error('Login error:', error);
        setLoginError('Login failed. Please check your credentials.');
      });
  };

  return (
    <Container component="main" maxWidth="xs">
      <TextField
        variant="outlined"
        margin="normal"
        fullWidth
        label="email"
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        error={!!errors.email}
        helperText={errors.email}
      />
      <TextField
        variant="outlined"
        margin="normal"
        fullWidth
        label="password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        error={!!errors.password}
        helperText={errors.password}
      />
      <Button variant="contained" color="primary" fullWidth onClick={handleLogin}>
        Register
      </Button>
      {loginError && <p style={{ color: 'red' }}>{loginError}</p>}
    </Container>
  );
};
export default AuthRegister;
