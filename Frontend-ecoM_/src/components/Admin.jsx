// Admin.jsx

import React, { useState } from "react";
import "./Admin.css";
import { FaFacebook, FaTwitter, FaInstagram } from "react-icons/fa";

import AddCategory from "./AddCategory";
import AddProduct from "./AddProduct";
import Products from "./Products";
import Users from "./Users";
import Orders from "./Orders";

const links = [
  {
    link: "/add-category",
    text: "ADD_CATAGORY",
    component: <AddCategory />,
  },
  {
    link: "/add-product",
    text: "ADD_PRODUCTS",
    component: <AddProduct />,
  },
  {
    link: "/products",
    text: "PRODUCTS",
    component: <Products />,
  },
  {
    link: "/users",
    text: "USERS",
    component: <Users />,
  },
  {
    link: "/products",
    text: "ORDERS",
    component: <Orders />,
  },
];

const SideDraw = ({ show, click }) => {
  let drawClasses = "sidebar";
  if (show) {
    drawClasses = "sidebar active";
  }
  return (
    <nav className={drawClasses}>
      <button className="close-btn" onClick={() => click(null)}>
        X
      </button>
      <ul className="sidebar-ul">
        {links.map((objLink, i) => (
          <li key={i}>
            <a href="#" onClick={() => click(objLink.component)}>
              {objLink.text}
            </a>
          </li>
        ))}
      </ul>
    </nav>
  );
};

const BackDrop = ({ click }) => (
  <div className="backDrop" onClick={() => click(null)} />
);

const SideDrawBtn = ({ click }) => (
  <button className="toggle-Btn" onClick={click}>
    <div className="btn-line"></div>
    <div className="btn-line"></div>
    <div className="btn-line"></div>
  </button>
);

const Toolbar = ({ drawClickHandler }) => (
  <header className="toolbar">
    <nav className="toolbar-nav">
      <div>
        <SideDrawBtn click={drawClickHandler} />
      </div>
      <div className="logo">
        <a href="/">
          <img
            src="https://cdn-icons-png.flaticon.com/128/2345/2345338.png"
            alt="Logo"
          />
        </a>
        <h2>Admin Dashboard</h2>
      </div>
      <div className="spacer"></div>
      <div>
        <ul></ul>
      </div>

      <div className="search-bar">
        <input type="text" placeholder="Search..." />
        <i className="fa fa-search" aria-hidden="true"></i>
      </div>

      <div className="social-icons-container">
        <FaFacebook className="social-icon" />
        <FaTwitter className="social-icon" />
        <FaInstagram className="social-icon" />

        <div className="notification-icon">
          <i className="fa fa-bell" aria-hidden="true"></i>
          <span className="notification-count">3</span>
        </div>
      </div>
    </nav>
  </header>
);

const Admin = () => {
  const [sideDrawOpen, setSideDrawOpen] = useState(false);
  const [selectedComponent, setSelectedComponent] = useState(null);

  const drawToggleHandler = () => {
    setSideDrawOpen((prevState) => !prevState.sideDrawOpen);
  };

  const backDropClickHandler = () => {
    setSideDrawOpen(false);
    setSelectedComponent(null);
  };

  const handleLinkClick = (component) => {
    setSelectedComponent(component);
    setSideDrawOpen(false);
  };

  return (
    <div className="main-container">
      <Toolbar drawClickHandler={drawToggleHandler} />
      <SideDraw click={handleLinkClick} show={sideDrawOpen} />
      {sideDrawOpen && <BackDrop click={backDropClickHandler} />}
      <main>
        {selectedComponent || (
          <img
            src="https://miro.medium.com/v2/resize:fit:679/1*uzVtquVT0jGfbNAf7Qc1UQ.gif"
            alt="Admin Dashboard GIF"
            className="background-img"
          />
        )}
      </main>
    </div>
  );
};

export default Admin;
