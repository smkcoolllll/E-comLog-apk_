import * as React from "react";
import Box from "@mui/material/Box";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import { useEffect, useState } from "react";

export default function Categories({productCategory, setProductCategory}) {

  const [category, setCategory] = useState([]);

  const [categories, setCategories] = useState([]);

  const handleChange = (event) => {
    setCategory(event.target.value);
    setProductCategory(event.target.value);
    console.log('selectedcategory ', event.target.value)
  };

  useEffect(() => {
    fetch("http://localhost:8088/api/categories")
      .then((response) => response.json())
      .then((data) => {
        console.log("categories ", data);
        setCategories(data);
      });
  }, []);


  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Categories 🔽</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={category}
          label="categories"
          onChange={handleChange}
        >
          {categories && categories?.map((category) => {
            return <MenuItem value={category?.id}>{category?.name}</MenuItem>;
          })}
        </Select>
      </FormControl>
    </Box>
  );
}
