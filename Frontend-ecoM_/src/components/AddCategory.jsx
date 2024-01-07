import * as React from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import { useState, useEffect } from "react";

export default function AddCategory() {
  const [categoryName, setCategoryName] = useState("");

  const addCategory = () => {
    const category = {
      name: categoryName,
    };

    console.log("category ", category);

    fetch("http://localhost:8088/api/categories/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(category),
    })
      .then((response) => response.json())
      .then((res) => {
        alert("Category Added...");
      });
  };

  useEffect(() => {
    console.log("categoryName ", categoryName);
    setCategoryName(categoryName);
  }, [categoryName]);

  return (
    <Box
      component="form"
      sx={{
        "& > :not(style)": {
          m: 2,
          width: "25ch",
          textAlign: "center",
        },
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
      }}
      noValidate
      autoComplete="off"
    >
      <div>
        <h3>Add Category 🏷️</h3>
        <Stack direction="column">
          <TextField
            id="standard-basic"
            label="Category Name"
            variant="standard"
            value={categoryName}
            onChange={(e) => setCategoryName(e.target.value)}
            sx={{ mt: 2 }}
          />

          <Button variant="contained" onClick={addCategory} style={{ marginTop: "10px" }}>
            Add
          </Button>
        </Stack>
      </div>
    </Box>
  );
}
