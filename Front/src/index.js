import React from 'react'
import { createRoot } from 'react-dom/client';
import { Link, Routes, Route, BrowserRouter as Router, Navigate } from 'react-router-dom';
import { Navbar, Nav, Container, Button } from 'react-bootstrap';
import Home from './components/Home';
import Login from './components/authorization/Login';
import { logout } from './services/auth';
import Products from './components/products/Products';
import AddProduct from './components/products/AddProduct';
import EditProduct from './components/products/EditProduct';

class App extends React.Component {

  render() {
    const jwt = window.localStorage['jwt'];

    if (jwt) {
      return (
        <>
          <Router>
            <Navbar expand bg="dark" variant="dark">
              <Navbar.Brand as={Link} to="/">
                Home
              </Navbar.Brand>
              <Nav>
                <Nav.Link as={Link} to="/products">
                  Proizvodi
                </Nav.Link>
                <Button onClick={() => logout()}>Logout</Button>
              </Nav>
            </Navbar>
            <Container style={{ paddingTop: "10px" }}>
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Navigate replace to='/' />} />
                <Route path="/products" element={<Products />} />
                <Route path="/products/add" element={<AddProduct/>} /> 
                <Route path="/products/edit/:id" element={<EditProduct/>} /> 
                <Route path='*' element={<Navigate replace to='/' />} />
              </Routes>
            </Container>
          </Router>
        </>
      );
    } else {
      return (
        <>
          <Router>
            <Navbar expand bg="dark" variant="dark">
              <Navbar.Brand as={Link} to="/">
                Home
              </Navbar.Brand>
              <Nav>
                <Nav.Link as={Link} to="/products">
                  Proizvodi
                </Nav.Link>
                <Nav.Link as={Link} to="/login">
                  Login
                </Nav.Link>
              </Nav>
            </Navbar>
            <Container style={{ paddingTop: "10px" }}>
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/products" element={<Products />} />
                <Route path='*' element={<Navigate replace to='/' />} />
              </Routes>
            </Container>
          </Router>
        </>
      );
    }
  }
};

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
  <App />,
);