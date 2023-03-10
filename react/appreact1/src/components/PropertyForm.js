import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service"


const PropertyForm = () => {
  let navigate = useNavigate();
  const [message, setMessage] = useState("");
  const [propReg, setPropReg] = useState({ name: "", address: {}, ownerId: 0 });
  const [address, setAddress] = useState({ city: "", state: "", country: "", pinCode: "" });
  const [stopUseeffect, setStopUseeffect] = useState(true);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setPropReg({ ...propReg, ["ownerId"]: user.id });
      console.log(propReg);
      setStopUseeffect(false);
    }
  }, [stopUseeffect]);


  const handleRegister = (e) => {
    e.preventDefault();
    console.log(propReg);

    UserService.addProperty(propReg,address).then(
      (res) => {
        localStorage.setItem("currentUser", JSON.stringify(res.data));
        console.log(res.data);
        navigate("/property");
        window.location.reload();
        document.getElementById("messageRegDiv").innerHTML = "Property added succesfully";
      },
      (error) => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();


        setMessage("error occured");
        console.log(resMessage);
        document.getElementById("messageRegDiv").innerHTML = message;
      }
    );
  }
  console.log(propReg);
  return <div className="PropertyFormForm">
    <form onSubmit={handleRegister}>
      <div className="form-group">
        <label htmlFor="name">Name</label>
        <input type="text" className="form-control" name="name" placeholder="Enter name" onChange={e => (setPropReg({ ...propReg, ["name"]: e.target.value }))}></input>
      </div>
      <div className="form-group">
        <label htmlFor="city">City</label>
        <input type="text" className="form-control" name="city" placeholder="Enter city" onChange={e => (setAddress({ ...address, ["city"]: e.target.value }))}></input>
      </div>
      <div className="form-group">
        <label htmlFor="state">State</label>
        <input type="text" className="form-control" name="state" placeholder="Enter state" onChange={e => (setAddress({ ...address, ["state"]: e.target.value }))}></input>
      </div>
      <div className="form-group">
        <label htmlFor="country">Country</label>
        <input type="text" className="form-control" name="password" placeholder="Enter country" onChange={e => (setAddress({ ...address, ["country"]: e.target.value }))}></input>
      </div>
      <div className="form-group">
        <label htmlFor="pinCode">Pincode</label>
        <input type="text" className="form-control" name="pinCode" placeholder="Enter pincode" onChange={e => (setAddress({ ...address, ["pinCode"]: e.target.value }))}></input>
      </div>
      <br></br>
      <button type="submit" className="btn btn-primary">Submit</button>
    </form>


    <div id="messageRegDiv"></div>
  </div>

}

export default PropertyForm;