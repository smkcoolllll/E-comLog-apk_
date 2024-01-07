import * as React from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import { useState, useEffect } from "react";
import Categories from "./Categories";

export default function AddProduct() {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");
  const [productCategory, setProductCategory] = useState();

  const addProduct = () => {

    const product = {
      name: name,
      price: Number(price),
      categoryId: productCategory,
      productDesc: description,
    };

    console.log("product ", product);

    fetch("http://localhost:8088/api/products/save", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(product),
    }).then(response => response.json())
    .then(res => {
        alert('Product Added...')
    })
  };

  useEffect(() => {
    console.log('productCategory ', productCategory)
    setProductCategory(productCategory)
  }, [productCategory])

  return (
    <Box
      component="form"
      sx={{
        "& > :not(style)": { m: 2, width: "25ch", textAlign: "center" },
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
      }}
      noValidate
      autoComplete="off"
    >
      <div>
        <h3>Add Product 🛒</h3>
        <Stack direction="column">
          <TextField
            id="standard-basic"
            label="Product Name"
            variant="standard"
            value={name}
            onChange={(e) => setName(e.target.value)}
            sx={{ mt: 2 }}
          />
          <TextField
            id="standard-basic"
            label="Product Price"
            variant="standard"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            sx={{ mt: 2 }}
          />
          <TextField
            id="standard-basic"
            label="Product Description"
            variant="standard"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            sx={{ mt: 2 , marginBottom:2 }}
          />

          <Categories productCategory={productCategory} setProductCategory={setProductCategory}  sx={{ marginTop: 2 }}/>

          <Button variant="contained" onClick={addProduct} style={{ marginTop: '10px' }}>
            Add
          </Button>
        </Stack>
      </div>
    </Box>
  );
}
