import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/auth.service";

const Login = () => {
  let navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const onChangeEmail = (e) => {
    const email = e.target.value;
    setEmail(email);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleLogin = (e) => {

    e.preventDefault();
    AuthService.login(email, password).then(
      () => {
        navigate("/property");
        window.location.reload();
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
        document.getElementById("messageDiv").innerHTML=message;
      }
    );
  }



  return <div className="loginForm">
    <form onSubmit={handleLogin}>
      <div className="form-group">
        <label htmlFor="exampleInputEmail1">Email address</label>
        <input type="email" className="form-control" placeholder="Enter email" value={email}
          onChange={onChangeEmail}></input>
        <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
      </div>
      <br></br>
      <div className="form-group">
        <label htmlFor="exampleInputPassword1">Password</label>
        <input type="password" className="form-control" placeholder="Password" value={password}
          onChange={onChangePassword}></input>
      </div>
      <br></br>
      <button type="submit" className="btn btn-primary" >Login</button>
    </form>
    <div id="messageRegDiv"></div>
  </div>
}

export default Login;