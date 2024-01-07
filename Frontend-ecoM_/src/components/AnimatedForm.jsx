import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';

import './AnimatedForm.css';

const AnimatedForm = ({navigateUrl, setNavigateUrl}) => {
    
  const [isLoginActive, setLoginActive] = useState(true);
  
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // const navigate = useNavigate();

  const switchForm = () => {
    setLoginActive(!isLoginActive);
  };

  const handleLogin = async (event) => {
    event.preventDefault();
    const email = event.target.elements['login-email'].value;
    const password = event.target.elements['login-password'].value;

    try {

      if(email === 'admin@gmail.com' && password === 'admin') {
        setNavigateUrl('admin')
      }

      const response = await axios.post('http://localhost:8083/path/login', { email, password });
      console.log(response.data);
      setNavigateUrl('user')
      setIsLoggedIn(!isLoggedIn);
    } catch (error) {
      console.error('Login failed:', error);
    }
  };

  const handleSignup = async (event) => {
    event.preventDefault();
    const firstName = event.target.elements['signup-firstname'].value;
    const lastName = event.target.elements['signup-lastname'].value;
    const email = event.target.elements['signup-email'].value;
    const mobile = event.target.elements['signup-mobile'].value;
    const address = event.target.elements['signup-address'].value;
    const password = event.target.elements['signup-password'].value;

    try {
      const response = await axios.post('http://localhost:8082/register', {
        firstName,
        lastName,
        email,
        mobile,
        address,
        password,
      });
      console.log(response.data);
    } catch (error) {
      console.error('Signup failed:', error);
    }
  };

  return (
    <section className="forms-section">
      <h1 className="section-title">Register Here...</h1>
      <div className={`forms ${isLoginActive ? 'is-login-active' : ''}`}>
        <div className={`form-wrapper ${isLoginActive ? 'is-active' : ''}`}>
          <button type="button" className="switcher switcher-login" onClick={switchForm}>
            Login
            <span className="underline"></span>
          </button>
          <form className="form form-login" onSubmit={handleLogin}>
            <fieldset>
              <legend>Please, enter your email and password for login.</legend>
              <div className="input-block">
                <label htmlFor="login-email">E-mail</label>
                <input id="login-email" type="email" required />
              </div>
              <div className="input-block">
                <label htmlFor="login-password">Password</label>
                <input id="login-password" type="password" required />
              </div>
            </fieldset>
            <button type="submit" className="btn-login">
              Login
            </button>
          </form>
        </div>
        <div className={`form-wrapper ${isLoginActive ? '' : 'is-active'}`}>
          <button type="button" className="switcher switcher-signup" onClick={switchForm}>
            Sign Up
            <span className="underline"></span>
          </button>
          <form className="form form-signup" onSubmit={handleSignup}>
            <fieldset>
              <legend>Please, enter your details for sign up.</legend>
              <div className="input-block">
                <label htmlFor="signup-firstname">First Name</label>
                <input id="signup-firstname" type="text" required />
              </div>
              <div className="input-block">
                <label htmlFor="signup-lastname">Last Name</label>
                <input id="signup-lastname" type="text" required />
              </div>
              <div className="input-block">
                <label htmlFor="signup-email">E-mail</label>
                <input id="signup-email" type="email" required />
              </div>
              <div className="input-block">
                <label htmlFor="signup-mobile">Mobile Number</label>
                <input id="signup-mobile" type="tel" required />
              </div>
              <div className="input-block">
                <label htmlFor="signup-address">Address</label>
                <input id="signup-address" type="text" required />
              </div>
              <div className="input-block">
                <label htmlFor="signup-password">Password</label>
                <input id="signup-password" type="password" required />
              </div>
              {/* <div className="input-block">
                <label htmlFor="signup-password-confirm">Confirm password</label>
                <input id="signup-password-confirm" type="password" required />
              </div> */}
            </fieldset>
            <button type="submit" className="btn-signup">
              Sign Up
            </button>
          </form>
        </div>
      </div>
    </section>
  );
};

export default AnimatedForm;
