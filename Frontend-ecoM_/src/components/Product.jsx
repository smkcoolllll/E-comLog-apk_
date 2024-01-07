import React from 'react';
import { Card, CardContent, Typography, Button, IconButton } from '@mui/material';

const Product = ({ name, price, description, onDelete, onUpdate }) => {
  return (
    <Card style={{width:'20%'}}>
      <CardContent>
        <Typography variant="h5" component="div">
          product 📥:{name}
        </Typography>
        <Typography variant="subtitle1" color="text.secondary">
          price 💰: {price}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          prodDesc ℹ️:{description}
        </Typography>
        
      </CardContent>
    </Card>
  );
};

export default Product;
