import React, { useEffect, useState } from 'react';
import AddProduct from './AddProduct';
import Products from './Products';
import Navbar from './Navbar';
import AddCategory from './AddCategory';
import AnimatedForm from './AnimatedForm';
import Admin from './Admin';
import UserDboard from './userDboard';

import Users from './Users';  

const Dashboard = () => {
  const [selectedOption, setSelectedOption] = useState(null);
  
  const [navigateUrl, setNavigateUrl] = useState('');

  const handleOptionChange = (option) => {
    setSelectedOption(option);
  };

  useEffect(() => {
    setSelectedOption(navigateUrl);
    console.log('url ', navigateUrl, selectedOption);
  }, [navigateUrl])


  return (
    <div>
      <Navbar onOptionChange={handleOptionChange} />
      {selectedOption === 'add-product' && <AddProduct />}
      {selectedOption === 'products' && <Products />}
      {selectedOption === 'add-category' && <AddCategory />}
      {selectedOption === 'signup' && <AnimatedForm navigateUrl={navigateUrl} setNavigateUrl={setNavigateUrl}/>}
      {selectedOption === 'admin' && <Admin />}
      {/* {(selectedOption === 'user' || navigateUrl === 'user') && <UserDboard />} */}
      {selectedOption === 'users' && <Users />}
      {selectedOption === 'user' && <UserDboard />}
      
    </div>
  );
};

export default Dashboard;
