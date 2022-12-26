import React from "react";
import { createRoot } from 'react-dom/client';
import { Route, Link, HashRouter as Router, Routes, Navigate } from 'react-router-dom';
import { Navbar, Nav, Container, Button} from 'react-bootstrap';
import NotFound from "./components/NotFound";
import Home from "./components/Home";
import Racuni from "./components/linije/Racuni";
import AddRacun from "./components/linije/AddRacun";
import EditRacun from "./components/linije/EditRacun";
import Prenos from "./components/linije/Prenos";
import Login from './components/authorization/Login';
import { logout } from './services/auth';


class App extends React.Component {

    render(){
        if(window.localStorage["jwt"]){
        return(
            <div>
                <Router>
                <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            BANKA
                        </Navbar.Brand>
                        <Nav>
                        <Nav.Link as={Link} to="/racuni">
                            Racuni
                        </Nav.Link>
                        <Nav.Link as={Link} to="/prenos">
                            Prenos
                        </Nav.Link>
                        <Button onClick={()=>logout()}>Log out</Button>
                        </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"10px"}}>
                    <Routes>
                        <Route path="/" element={<Home/>} />
                        <Route path="/login" element={<Navigate replace to="/racuni"/>} />
                        <Route path="/racuni" element={<Racuni/>}/>
                        <Route path="/racuni/add" element={<AddRacun/>}/>
                        <Route path="/racuni/edit/:id" element={<EditRacun/>}/>
                        <Route path="/prenos" element={<Prenos/>}/>
                        <Route path="*" element={<NotFound/>} />
                    </Routes>
                    </Container>
                </Router>
            </div>
        )
        }else{
            return(
                <div>
                    <Router>
                    <Navbar expand bg="dark" variant="dark">
                            <Navbar.Brand as={Link} to="/">
                                Banka
                            </Navbar.Brand>
                            <Nav>
                            <Nav.Link as={Link} to="/racuni">
                                Racuni
                            </Nav.Link>
                            <Nav.Link as={Link} to="/login">
                                Login
                            </Nav.Link>
                            </Nav>
                        </Navbar>
                        <Container style={{paddingTop:"10px"}}>
                        <Routes>
                            <Route path="/" element={<Home/>} />
                            <Route path="/login" element={<Login/>} />
                            <Route path="/racuni" element={<Racuni/>}/>
                            <Route path="*" element={<Navigate replace to = "/login"/>}/>
                        </Routes>
                        </Container>
                    </Router>
                </div>
            )
        }
    }

}

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App/>,
);
