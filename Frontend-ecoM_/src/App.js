import Dashboard from "./components/Dashboard";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import AddProduct from "./components/AddProduct";
import Products from "./components/Products";
import AddCategory from "./components/AddCategory";
import AnimatedForm from "./components/AnimatedForm";
import Admin from "./components/Admin";
// import UserProducts from './components/UserProducts';
import UserDboard from "./components/userDboard";
import Users from './components/Users';  
function App() {
  return (
    <div className="App">
      {/* <Navbar /> */}
      <Dashboard />
      <BrowserRouter>
        <Routes>
          <Route path="add-product" element={<AddProduct />} />
          <Route path="products" element={<Products />} />
          <Route path="add-category" element={<AddCategory />} />
          <Route path="signup" element={<AnimatedForm />} />
          <Route path="admin" element={<Admin />} />
          <Route path="user" element={<UserDboard />} />
          <Route path="users" element={<Users />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
