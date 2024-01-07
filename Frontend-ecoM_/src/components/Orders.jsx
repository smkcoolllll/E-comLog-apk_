import React, { useState, useEffect } from 'react';


const Orders = () => {
  const [selectedProducts, setSelectedProducts] = useState(JSON.parse(localStorage.getItem('user@gmail.com')) || []);
const [productsDetails, setProductsDetails] = useState([]);

const fetchProductDetails = async (productId) => {
  try {
    const response = await fetch(`http://localhost:8088/api/products/${productId}`);
    const productDetails = await response.json();
    console.log('Fetched Product Details:', productDetails);
    return productDetails;
  } catch (error) {
    console.error('Error fetching product details:', error);
    throw error;
  }
};

useEffect(() => {
  const fetchDetailsForSelectedProducts = async () => {
    try {
      const detailsPromises = selectedProducts.map(fetchProductDetails);
      const details = await Promise.all(detailsPromises);
      setProductsDetails(details);
    } catch (error) {
      // Handle errors if needed
    }
  };

  fetchDetailsForSelectedProducts();
}, [selectedProducts]);


  // useEffect(() => {
    // const prodctIds = JSON.parse(localStorage.getItem('user@gmail.com'));
    // setSelectedProducts(JSON.parse(localStorage.getItem('user@gmail.com')));

    // prodctIds?.map(productId => {
    //     console.log('productId ', typeof productId, productId)
    //     fetchProductDetails(productId)
    // })
  //  })

  // Render your component
  return (
    <div className="container mt-4 text-center">
      <h2 className="mb-4">Product List</h2>
      <div className="row justify-content-center">
        <div className="col-12 col-md-8">
          <div className="table-responsive">
            <table className="table table-bordered">
              <thead className="thead-light">
                <tr>
                  <th scope="col">Product ID</th>
                  <th scope="col">Product Name</th>
                  <th scope="col">Price</th>
                </tr>
              </thead>
              <tbody>
                {productsDetails.map((product, index) => (
                  <tr key={index}>
                    <td className="text-center">{product.id}</td>
                    <td className="text-center">{product.name}</td>
                    <td className="text-center">{product.price}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Orders;
