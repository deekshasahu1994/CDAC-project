import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/auth.service";

const SignUp = () => {
  let navigate = useNavigate();
  const [message, setMessage] = useState("");
  const [userReg, setUserReg] = useState({ firstName: "", lastName: "", email: "", password: "", contactNo: "", role: "ADMIN" });



  const handleRegister = (e) => {

    e.preventDefault();
    AuthService.register(userReg).then(
      (res) => {
        navigate("/signup");
        console.log(message);
        setMessage(res.data.message);
        // window.location.reload();
        console.log(message);
        document.getElementById("messageRegDiv").innerHTML = message;
      },
      (error) => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();


        setMessage("Wrong credentials");
        console.log(resMessage);
        document.getElementById("messageRegDiv").innerHTML = message;
      }
    );
  }
  console.log(userReg);
  return <div className="signupForm">
      <form onSubmit={handleRegister}>
        <div className="form-group">
          <label htmlFor="firstName">First name</label>
          <input type="text" className="form-control" name="firstName" placeholder="Enter first name" onChange={e => (setUserReg({ ...userReg, ["firstName"]: e.target.value }))}></input>
        </div>
        <div class="form-group">
          <label htmlFor="lastName">Last name</label>
          <input type="text" className="form-control" name="lastame" placeholder="Enter last name" onChange={e => (setUserReg({ ...userReg, ["lastName"]: e.target.value }))}></input>
        </div>
        <div class="form-group">
          <label htmlFor="email">Email address</label>
          <input type="email" className="form-control" name="email" placeholder="Enter email" onChange={e => (setUserReg({ ...userReg, ["email"]: e.target.value }))}></input>
        </div>
        <div class="form-group">
          <label htmlFor="password">Password</label>
          <input type="password" className="form-control" name="password" placeholder="Enter password" onChange={e => (setUserReg({ ...userReg, ["password"]: e.target.value }))}></input>
        </div>
        <div class="form-group">
          <label htmlFor="contactno">Mobile No</label>
          <input type="text" className="form-control" name="contactno" placeholder="Enter Mobile number" onChange={e => (setUserReg({ ...userReg, "[contactNo]": e.target.value }))}></input>
        </div>
        <br></br>
        <button type="submit" className="btn btn-primary">Sign Up</button>
      </form>
  
  
      <div id="messageRegDiv"></div>
      </div>
      
}

export default SignUp;