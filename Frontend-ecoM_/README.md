          
                  E-commerce Product Catalog

● Objective: Build a Java application for managing an e-commerce product
catalog.
● Details: Key entities are Product and Category. Each product belongs to
one category, but a category can have many products.
● Relationships: OneToMany between Category and Product.
● Tasks: Develop functionalities to add new products under categories,
update product details, remove products, and list all products under a
specific category.


# EcomCataLog System

## Setup Instructions

### Prerequisites

Make sure you have the following installed on your machine:

- Node.js and npm for frontend development
- Java 8 or later for backend development
- MySQL Database

### Backend Setup

1. Open the `application.yml` file in the backend (`EcomcataLog_/src/main/resources/application.yml`).
2. Update the datasource section with your MySQL database configuration:

```yaml
datasource:
  url: jdbc:mysql://localhost:3306/EcomCataLog
  username: your-username
  password: your-password
  driver-class-name: com.mysql.cj.jdbc.Driver
```

3. Make sure your MySQL server is running.

### Frontend Setup

1. Open a terminal and navigate to the frontend directory (`EcomcataLog_-frontend`).
2. Run the following commands:

```bash
npm install
npm start
```

This will install the required dependencies and start the frontend server.

### Testing Endpoints

You can test the provided endpoints using tools like Postman, Insomnia, or even through a web application. Here are instructions for testing:

1. **Postman or Insomnia:**
   - Open your preferred API testing tool (e.g., Postman or Insomnia).
   - Import the provided [Postman Collection](#) to get all the endpoints and examples.
   - Update the base URL in the collection to match your local server (e.g., `http://localhost:8082`).
   - Start testing each endpoint by sending requests.

2. **Web Application:**
   - Ensure that the backend server is running (`npm start` for the frontend and Spring Boot application for the backend).
   - Create a web application (e.g., using React) or use an existing one.
   - Make API requests using tools like `fetch` in JavaScript or any HTTP client library.
   - Update the API URLs in your web application to match the local server.

Remember to replace `http://localhost:8082` with your actual server URL. If you're using a different port or domain, update accordingly. Feel free to explore and test different scenarios using the provided endpoints.



## API Endpoints

### Welcome Message

- **Endpoint:** `GET /api/products/print`
- **Response Example:**
  ```json
  {
    "message": "Hey! Welcome to Ecom-cataLoG_🛒"
  }
  ```

### Save Product under Specific Category

- **Endpoint:** `POST /api/products/save`
- **Request Example:**
  ```json
  {
    "name": "New Product",
    "price": 39.99,
    "categoryid": 1,
    "productDesc": "Description of the new product"
  }
  ```
- **Response Example:**
  ```json
  {
    "id": 1,
    "name": "New Product",
    "price": 39.99,
    "categoryId": 1,
    "productDesc": "Description of the new product"
  }
  ```

### Get All Products

- **Endpoint:** `GET /api/products/all`
- **Response Example:**
  ```json
  [
    {
      "id": 1,
      "name": "Product 1",
      "price": 29.99,
      "categoryId": 1,
      "productDesc": "Description of Product 1"
    },
    {
      "id": 2,
      "name": "Product 2",
      "price": 19.99,
      "categoryId": 2,
      "productDesc": "Description of Product 2"
    }
    // ... more products
  ]
  ```

### Get Details of Specific Product

- **Endpoint:** `GET /api/products/{productId}`
- **Response Example:**
  ```json
  {
    "id": 42,
    "name": "Product Name",
    "price": 29.99,
    "categoryId": 2,
    "productDesc": "Description of the product"
  }
  ```

### Post New Category

- **Endpoint:** `POST /api/categories/add`
- **Request Example:**
  ```json
  {
    "name": "Electronics"
  }
  ```
- **Response Example:**
  ```json
  {
    "id": 1,
    "name": "Electronics",
    "products": []
  }
  ```

### Get All Categories with IDs

- **Endpoint:** `GET /api/categories`
- **Response Example:**
  ```json
  [
    {
      "id": 1,
      "name": "Electronics",
      "products": []
    },
    {
      "id": 2,
      "name": "Clothing",
      "products": []
    }
    // ... more categories
  ]
  ```

### Get All Products under Specific Category

- **Endpoint:** `GET /api/products/category/{categoryId}`
- **Response Example:**
  ```json
  [
    {
      "id": 1,
      "name": "Product 1",
      "price": 29.99,
      "categoryId": 1,
      "productDesc": "Description of Product 1"
    },
    {
      "id": 2,
      "name": "Product 2",
      "price": 19.99,
      "categoryId": 1,
      "productDesc": "Description of Product 2"
    }
    // ... more products in the category
  ]
  ```

### Update Product Details

- **Endpoint:** `PUT /api/products/{productId}`
- **Request Example:**
  ```json
  {
    "name": "Updated Product Name",
    "price": 49.99,
    "productDesc": "Updated description of the product"
  }
  ```
- **Response Example:**
  ```json
  {
    "id": 28,
    "name": "Updated Product Name",
    "price": 49.99,
    "categoryId": 1,
    "productDesc": "Updated description of the product"
  }
  ```

### Delete Specific Product

- **Endpoint:** `DELETE /api/products/{productId}`
- **Response Example:**
  ```json
  {
    "message": "Product with ID 4 deleted successfully"
  }
  ```

### Delete All Products under Specific Category

- **Endpoint:** `DELETE /api/products/deleteByCategory/{categoryId}`
- **Response Example:**
  ```json
  {
    "message": "Products related to the category with ID 3 deleted successfully"
  }
  ```

Make sure to implement these endpoints in your `ProductController` and `ProductService` accordingly. The examples provided are for reference, and you can customize them based on your specific implementation.
