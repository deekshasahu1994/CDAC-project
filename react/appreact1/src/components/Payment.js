import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AuthService from "../services/auth.service";
import { useParams } from "react-router-dom";

const Payment = () => {
    const { id } = useParams();
    const [currentUserClient, setCurrentClient] = useState([]);
    const [findClient, setFindClient] = useState({});
    const [findPayment, setFindPayment] = useState([]);
    

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentClient(user.clients);
            console.log(id);
            console.log(user.clients);

        }
    }, []);

    useEffect(() => {
        if (currentUserClient) {
            setFindClient(currentUserClient.find(obj => obj.id == id));
            console.log(findClient);
        }
    })

    useEffect(() => {
        if (findClient) {
            setFindPayment(findClient.payments);
            console.log(findPayment);
        }
    })


    return <div>
        <div className="container">
            <h1>Payments of Mr. {findClient?.firstName}</h1> <Link to={`/payemntform/${id}`} className="navLinks"><button className="btn btn-info">Add Payment</button></Link>
            <div className="row">
                <div className="col-sm-12">

                    <table className="table table-light table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Amount</th>
                                <th scope="col">Paid on</th>
                                <th scope="col">Due Date</th>
                                

                                {/* <th scope="col">Status</th> */}
                            </tr>
                        </thead>
                        <tbody>
                            {

                                findPayment?.map((item) => (
                                    <tr key={item.id}>
                                        <td>{item.amount}</td>
                                        <td>{item.date}</td>
                                        <td>{item.dueDate}</td>
                                    </tr>

                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
}

export default Payment;