// Products.js
import React, { useState, useEffect } from 'react';
import Product from './Product';
import { Modal, Button, Stack } from '@mui/material';
import Categories from './Categories';
import UpdateModal from './UpdateModal';

const Products = () => {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState({});
  const [isUpdateModalOpen, setUpdateModalOpen] = useState(false);
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [productCategory, setProductCategory] = useState();

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

  const handleUpdateClick = (product) => {
    setSelectedProduct(product);
    setUpdateModalOpen(true);
  };

  const handleDeleteClick = async (productId) => {
    try {
      const response = await fetch(`http://localhost:8088/api/products/${productId}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error(`Failed to delete product: ${response.status} ${response.statusText}`);
      }

      setProducts((prevProducts) => prevProducts.filter((product) => product.id !== productId));
    } catch (error) {
      console.error('Error deleting product:', error.message);
    }
  };

  const handleUpdateModalClose = () => {
    setUpdateModalOpen(false);
  };

  const handleUpdateProduct = async (updatedProduct) => {
    try {
      const response = await fetch(`http://localhost:8088/api/products/${selectedProduct.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedProduct),
      });

      if (!response.ok) {
        throw new Error(`Failed to update product: ${response.status} ${response.statusText}`);
      }

      fetchProducts();
      console.log('Product updated successfully:', selectedProduct);
      alert('Product updated successfully:', selectedProduct);

      setUpdateModalOpen(false);
    } catch (error) {
      console.error('Error updating product:', error.message);
    }
  };

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

  return (
    <div style={{ marginTop: '50px', textAlign: 'center' }}>
      <div className="container" style={{ width: '90%', display: 'inline-block', textAlign: 'left' }}>
        <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '20px' }}>
          <h2 style={{ marginRight: '90px' }}>Products 📦</h2>
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
            {/* Adjust the margin as needed */}
            <Button onClick={() => handleUpdateClick(product)} variant="outlined" color="primary">
              Update ✍️
            </Button>
          </div>
            <Button onClick={() => handleDeleteClick(product.id)} variant="outlined" color="secondary">
              Delete 🗑️
            </Button>
          </Stack>
        ))}
        <UpdateModal
          isUpdateModalOpen={isUpdateModalOpen}
          handleUpdateModalClose={handleUpdateModalClose}
          handleUpdateProduct={handleUpdateProduct}
          selectedProduct={selectedProduct}
        />
      </div>
    </div>
  );
};

export default Products;
