import { useState, useEffect } from "react";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";

const UpdateModal = ({
  isUpdateModalOpen,
  handleUpdateModalClose,
  handleUpdateProduct,
  selectedProduct,
}) => {
  const modalStyle = {
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    position: "absolute",
    width: 500,
    boxShadow: 14,
    backgroundColor: "grey", // Background color set to black
    color: "white", // Text color set to white
    padding: 4,
    textAlign: "center", // Center the content
  };

  // Initialize state directly from the selectedProduct prop
  const [updatedProduct, setUpdatedProduct] = useState({
    name: selectedProduct?.name || "",
    price: selectedProduct?.price || "",
    description: selectedProduct?.productDesc || "",
    // productCategory: selectedProduct?.categoryId || "",
  });

  // Update state when selectedProduct changes
  useEffect(() => {
    setUpdatedProduct({
      name: selectedProduct?.name || "",
      price: selectedProduct?.price || "",
      productDesc: selectedProduct?.productDesc || "",
      // productCategory: selectedProduct?.categoryId || "",
    });
  }, [selectedProduct]);

  const handleUpdate = () => {
    // Pass the updated product to the handleUpdateProduct function
    handleUpdateProduct(updatedProduct);
    console.log('updatedProduct ', updatedProduct)
    handleUpdateModalClose();
  };

  return (
    <Modal
      open={isUpdateModalOpen}
      onClose={handleUpdateModalClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <div style={modalStyle}>
        <h2 id="modal-modal-title">UPDATE-product_🎨</h2>

        <div>
          <Stack direction="column">
            <TextField
              id="standard-basic"
              label="product 📥"
              variant="standard"
              value={updatedProduct.name}
              onChange={(e) =>
                setUpdatedProduct((prev) => ({
                  ...prev,
                  name: e.target.value,
                }))
              }
              InputProps={{
                style: { color: "white", marginLeft: "30px" }, 
              }}
              InputLabelProps={{
                style: { color: "white",marginLeft: "15px" }, 
              }}
            />
            <TextField
              id="standard-basic"
              label="price 💰"
              variant="standard"
              value={updatedProduct.price}
              onChange={(e) =>
                setUpdatedProduct((prev) => ({
                  ...prev,
                  price: e.target.value,
                }))
              }
              InputProps={{
                style: { color: "white",marginLeft: "30px" }, 
              }}
              InputLabelProps={{
                style: { color: "white",marginLeft: "15px" }, 
              }}
            />
            <TextField
              id="standard-basic"
              label="prodDesc ℹ️"
              variant="standard"
              value={updatedProduct.productDesc}
              onChange={(e) =>
                setUpdatedProduct((prev) => ({
                  ...prev,
                  productDesc: e.target.value,
                }))
              }
              InputProps={{
                style: { color: "white",marginLeft: "30px" }, 
              }}
              InputLabelProps={{
                style: { color: "white",marginLeft: "15px" }, 
              }}
            />

            {/* <Categories
              productCategory={updatedProduct.productCategory}
              setProductCategory={(value) =>
                setUpdatedProduct((prev) => ({
                  ...prev,
                  productCategory: value,
                }))
              }
            /> */}
          </Stack>
        </div>

        <div style={{ marginTop: "5%", marginBottom:"5%" }}>
          <Button
            onClick={handleUpdate}
            variant="contained"
            color="primary"
            style={{ marginRight: "5%" }}
          >
            Update
          </Button>
          <Button
            onClick={handleUpdateModalClose}
            variant="contained"
            color="secondary"
          >
            Close
          </Button>
        </div >
      </div>
    </Modal>
  );
};

export default UpdateModal;
