import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service"


const PaymentForm = () => {
    const { id } = useParams();
    let navigate = useNavigate();
    const [message, setMessage] = useState("");
    const [paymentReg, setPaymentReg] = useState({
        amount: 0,
        date: ""
    });
    const [stopUseeffect, setStopUseeffect] = useState(true);

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setPaymentReg({ ...paymentReg, ["clientId"]: parseInt(id) });
            console.log(paymentReg);
            setStopUseeffect(false);
        }
    }, [stopUseeffect]);


    const handleRegister = (e) => {
        e.preventDefault();
        console.log(paymentReg);

        UserService.addPayment(paymentReg).then(
            (res) => {
                localStorage.setItem("currentUser", JSON.stringify(res.data));
                console.log(res.data);
                navigate("/client/view/" + parseInt(id));
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
    console.log(paymentReg);
    return <div className="PaymentForm">
        <form onSubmit={handleRegister}>

            <div className="form-group" >
                <label htmlFor="amount">Amount</label>
                <input type="number" className="form-control" name="amount" placeholder="Enter amount" onChange={e => (setPaymentReg({ ...paymentReg, ["amount"]: parseInt(e.target.value) }))}></input>
            </div>
            <div className="form-group">
                <label htmlFor="date">Last Date</label>
                <input type="date" className="form-control" name="date" placeholder="Enter date" onChange={e => (setPaymentReg({ ...paymentReg, ["date"]: e.target.value }))}></input>
            </div>
            <br></br>
            <button type="submit" className="btn btn-primary">Submit</button>
        </form>


        <div id="messageRegDiv"></div>
    </div>

}

export default PaymentForm;