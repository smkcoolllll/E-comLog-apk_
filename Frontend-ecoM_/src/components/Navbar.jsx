import React, { useState } from "react";
import "./Navbar.css";

const Navbar = ({ onOptionChange }) => {
  const [selectedOption, setSelectedOption] = useState(null);

  const handleLinkClick = (option) => {
    onOptionChange(option);
    setSelectedOption(option);

    // If the Ecom-cataLoG_🛒 link is clicked, redirect to landing section
    if (option === "ecomcatalog") {
      setSelectedOption(null); // Reset selectedOption to trigger the rendering of landing-section
    }
  };

  return (
    <div>
      <nav className="navbar">
        <ul className="navbar-list">
          <li className="navbar-item">
            <img
              src="https://cdn-icons-png.flaticon.com/128/2096/2096276.png"
              alt="Icon"
              className="navbar-icon"
            />
          </li>
          <li className="navbar-item">
            <span
              className="navbar-link"
              onClick={() => handleLinkClick("ecomcatalog")}
            >
              Ecom-cataLoG_🛒
            </span>
          </li>
          <li className="navbar-item" onClick={() => handleLinkClick("signup")}>
            <span className="navbar-link">SignuP_</span>
          </li>
          {/* <li className="navbar-item" onClick={() => handleLinkClick("admin")}>
            <span className="navbar-link">ADMIN_</span>
          </li>
          <li className="navbar-item" onClick={() => handleLinkClick("user")}>
            <span className="navbar-link">USER_</span>
          </li> */}
          
        </ul>
      </nav>
      {selectedOption !== "signup" && selectedOption !== "admin" && selectedOption !== "user" && (
        <section className="landing-section">
          <img
            src="https://images.unsplash.com/photo-1592503254549-d83d24a4dfab?q=80&w=1932&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            alt="Landing Page"
            className="landing-image"
          />
        </section>
      )}
    </div>
  );
};

export default Navbar;
