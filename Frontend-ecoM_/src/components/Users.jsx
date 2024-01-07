import React, { useState, useEffect } from 'react';
import { Card, CardContent, Typography, Grid } from '@mui/material';

const Users = () => {
  const [users, setUsers] = useState([]);

  const fetchUsers = async () => {
    try {
      const response = await fetch('http://localhost:8082/getAllUsers');

      if (!response.ok) {
        throw new Error(`Failed to fetch data: ${response.status} ${response.statusText}`);
      }

      const data = await response.json();
      setUsers(data);
    } catch (error) {
      console.error('Error fetching users:', error.message);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div style={{ marginTop: '50px', textAlign: 'center'  }}>
      <div className="container" style={{ width: '90%', display: 'inline-block', textAlign: 'left' }}>
        <h2 style={{  textAlign: 'center' }}>USERS_</h2>
        <Grid container spacing={3}>
          {users.map((user) => (
            <Grid item key={user.email} xs={12} sm={6} md={4}>
              <Card>
                <CardContent>
                  <Typography variant="h5" component="div">
                    {user.firstName} {user.lastName}
                  </Typography>
                  <Typography color="text.secondary">Email: {user.email}</Typography>
                  <Typography color="text.secondary">Mobile: {user.mobile}</Typography>
                  <Typography color="text.secondary">Address: {user.address}</Typography>
                  <Typography color="text.secondary">Active: {user.active ? 'Yes' : 'No'}</Typography>
                  {/* <Typography color="text.secondary">OTP: {user.otp || 'N/A'}</Typography>
                  <Typography color="text.secondary">
                    OTP Generated Time: {user.otpGeneratedTime || 'N/A'}
                  </Typography> */}
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
      </div>
    </div>
  );
};

export default Users;
