import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service"


const ClientForm = () => {
    const { id } = useParams();
    let navigate = useNavigate();
    const [message, setMessage] = useState("");
    const [clientReg, setclientReg] = useState({
        firstName: "",
        lastName: "",
        address: {
            city: "",
            state: "",
            country: "",
            pinCode: ""
        },
        contactNo: "",
        email: "",
        enrollDate: "",
        amount: 0,
        propertyId: 0
    });
    const [address, setAddress] = useState({ city: "", state: "", country: "", pinCode: "" });
    const [stopUseeffect, setStopUseeffect] = useState(true);

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setclientReg({ ...clientReg, ["propertyId"]: parseInt(id) });
            console.log(clientReg);
            setStopUseeffect(false);
        }
    }, [stopUseeffect]);


    const handleRegister = (e) => {
        e.preventDefault();
        console.log(clientReg);

        UserService.addClient(clientReg, address).then(
            (res) => {
                localStorage.setItem("currentUser", JSON.stringify(res.data));
                console.log(res.data);
                navigate("/property/view/"+id);
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
    console.log(clientReg);
    return <div className="ClientForm">
        <form onSubmit={handleRegister}>
            <div className="cfLeftContainer">
                <div className="form-group" >
                    <label htmlFor="firstName">First name</label>
                    <input type="text" className="form-control" name="firstName" placeholder="Enter firstName" onChange={e => (setclientReg({ ...clientReg, ["firstName"]: e.target.value }))}></input>
                </div>
                <div className="form-group">
                    <label htmlFor="lastName">Last name</label>
                    <input type="text" className="form-control" name="lastName" placeholder="Enter lastName" onChange={e => (setclientReg({ ...clientReg, ["lastName"]: e.target.value }))}></input>
                </div>
                <div className="form-group">
                    <label htmlFor="contactNo">Mobile No</label>
                    <input type="text" className="form-control" name="contactNo" placeholder="Enter contactNo" onChange={e => (setclientReg({ ...clientReg, ["contactNo"]: e.target.value }))}></input>
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input type="email" className="form-control" name="email" placeholder="Enter email" onChange={e => (setclientReg({ ...clientReg, ["email"]: e.target.value }))}></input>
                </div>
                <div className="form-group">
                    <label htmlFor="enrollDate">Registration date</label>
                    <input type="date" className="form-control" name="enrollDate" placeholder="Enter enrollDate" onChange={e => (setclientReg({ ...clientReg, ["enrollDate"]: e.target.value }))}></input>
                </div>
            </div>
            <div className="form-group">
                <label htmlFor="amount">Amount</label>
                <input type="number" className="form-control" name="amount" placeholder="Enter amount" onChange={e => (setclientReg({ ...clientReg, ["amount"]: e.target.value }))}></input>
            </div>


            <div className="cfRightContainer">
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
            </div>
            <br></br>
            <button type="submit" className="btn btn-primary">Submit</button>
        </form>


        <div id="messageRegDiv"></div>
    </div>

}

export default ClientForm;