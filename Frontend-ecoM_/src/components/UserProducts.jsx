import React, { useState, useEffect } from 'react';
import Product from './Product';
import { Stack, Button } from '@mui/material';
import Categories from './Categories';

const UserProducts = () => {
  const [products, setProducts] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [productCategory, setProductCategory] = useState();
  
  const [selectedProducts, setSelectedProducts] = useState([]);

  const fetchProducts = async () => {
    try {
      const response = await fetch('http://localhost:8088/api/products/all');

      if (!response.ok) {
        throw new Error(`Failed to fetch data: ${response.status} ${response.statusText}`);
      }

      const data = await response.json();
      setProducts(data);
    } catch (error) {
      console.error('Error fetching products:', error.message);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  const handleCategoryChange = (event) => {
    setSelectedCategory(event.target.value);
  };

  const filteredProducts = selectedCategory === 'All'
    ? products
    : products.filter((product) => product.category === selectedCategory);

  useEffect(() => {
    const filterProducts = async () => {
      try {
        const response = await fetch(`http://localhost:8088/api/products/category/${productCategory}`);
        
        if (!response.ok) {
          throw new Error(`Failed to fetch data: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();
        setProducts(data);
      } catch (error) {
        console.error('Error fetching products:', error.message);
      }
    };

    filterProducts();
  }, [productCategory]);

  const handleBuyClick = async (productId) => {
    try {
      const response = await fetch(`http://localhost:8088/api/products/${productId}`);
      
      if (!response.ok) {
        throw new Error(`Failed to fetch product details: ${response.status} ${response.statusText}`);
      }

      const productDetails = await response.json();

      const orderNotificationResponse = await fetch('http://localhost:8088/api/products/send-order-notification', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          recipientEmail: 'dipakghatge849@gmail.com', 
          productName: productDetails.name,
          productPrice: productDetails.price,
        }),
      });

      if (!orderNotificationResponse.ok) {
        throw new Error(`Failed to send order notification: ${orderNotificationResponse.status} ${orderNotificationResponse.statusText}`);
      }
    // if (orderNotificationResponse.ok) {
    //     // if(localStorage.getItem('user@gmail.com')) {
    //         // const products = [];
    //         // products.push(productId)
    //         // setSelectedProducts(selectedProducts.push(productId))
    //         setSelectedProducts(selectedProducts=> [...selectedProducts, productId]);
    //         localStorage.setItem("user@gmail.com", products.toString());
    //         console.log('products', products);
    //     // }
    // }

    if (orderNotificationResponse.ok) {
        const existingProducts = localStorage.getItem("user@gmail.com");
        const currentSelectedProducts = existingProducts ? JSON.parse(existingProducts) : [];
    
        // Assuming productId is a variable containing the product id
        const newSelectedProducts = [...currentSelectedProducts, productId];
    
        setSelectedProducts(newSelectedProducts);
        localStorage.setItem("user@gmail.com", JSON.stringify(newSelectedProducts));
        console.log('selectedProducts', newSelectedProducts);
    }
    
    

      console.log(`Order notification sent successfully for product with ID ${productId}`);
      alert(`Order notification sent successfully for product with ID ${productId}`);
    } catch (error) {
      console.error('Error handling buy click:', error.message);
    }
  };

  return (
    <div style={{ marginTop: '50px', textAlign: 'center' }}>
      <div className="container" style={{ width: '90%', display: 'inline-block', textAlign: 'left' }}>
        <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '20px' }}>
          <h2 style={{ marginRight: '90px' }}>Your Products 📦</h2>
          <Categories  productCategory={productCategory} setProductCategory={setProductCategory} style={{ marginLeft: '20px' }} />
        </div>
        {filteredProducts.map((product) => (
          <Stack key={product.id} spacing={2} direction="row" alignItems="center" justifyContent="center" style={{ marginTop:  '30px'  }}>
            <Product
              name={product.name}
              price={product.price}
              description={product.productDesc}
            />
            <div style={{ margin: '0 20px' }}>
              <Button onClick={() => handleBuyClick(product.id)} variant="outlined" color="primary">
                Buy 🛒
              </Button>
            </div>
          </Stack>
        ))}
      </div>
    </div>
  );
};

export default UserProducts;
