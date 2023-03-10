import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import logo from '../Images/Screenshot 2023-03-08 214203.png';
import AuthService from "../services/auth.service";
import EventBus from "../common/EventBus";

const Navbar = () => {
    const [currentUser, setCurrentUser] = useState(undefined);
    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentUser(user);
            console.log(user)
        }

        EventBus.on("logout", () => {
            logOut();
        });

        return () => {
            EventBus.remove("logout");
        };
    }, []);

    const logOut = () => {
        AuthService.logout();
        setCurrentUser(undefined);
    };
    return <div>

        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="navbarContainer">


                <div className="leftNavbar">
                    <Link to="/" className="navbar-brand">
                        <img src={logo} alt="Logo" width="100" height="29" className="d-inline-block align-text-top" ></img>
                    </Link>
                    <Link to="/" className="navLinks">Home</Link>
                    <Link to="/about" className="navLinks">About Us</Link>
                    <Link to="/contact" className="navLinks"> Contact</Link>

                </div>

                {currentUser ? (
                    <div className="rightNavbar">
                        <Link to={"/property"} className="navLinks">
                       <b>hi! {currentUser.firstName}</b> 
                        </Link>
                        <Link to="/profile" className="navLinks"> Profile</Link>
                        <Link to="/" className="navLinks" onClick={logOut}> Logout</Link>
                    </div>
                ) : (


                    <div className="rightNavbar">

                        <Link to="/login" className="navLinks"> Login</Link>
                        <Link to="/signup" className="navLinks"> SignUp</Link>
                    </div>
                )}
            </div>
        </nav>
    </div>
}

export default Navbar;