import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AuthService from "../services/auth.service";
import { useParams } from "react-router-dom";

const Client = () => {
    const { id } = useParams();
    const [currentUserProperty, setCurrentProperty] = useState([]);
    const [findProperty, setFindProperty] = useState({});
    const [findClient, setFindClient] = useState([]);
    

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentProperty(user.properties);
            console.log(id);
            console.log(user.properties);

        }
    }, []);

    useEffect(() => {
        if (currentUserProperty) {
            setFindProperty(currentUserProperty.find(obj => obj.id == id));
            console.log(findProperty);
        }
    })

    useEffect(() => {
        if (findProperty) {
            setFindClient(findProperty.clients);
            console.log(findClient);
        }
    })


    return <div>
        <div className="container">
            <h1>Clients at {findProperty?.name} property</h1> <Link to={`/clientform/${id}`} className="navLinks"><button className="btn btn-info">Add Client</button></Link>
            <div className="row">
                <div className="col-sm-12">

                    <table className="table table-light table-striped">
                        <thead>
                            <tr>
                                <th scope="col">First name</th>
                                <th scope="col">Last name</th>
                                <th scope="col">Mobile No</th>
                                <th scope="col">Email</th>
                                <th scope="col">Rent Amount</th>
                                <th scope="col">Registration date</th>
                                <th scope="col">City</th>
                                <th scope="col">State</th>
                                <th scope="col">Country</th>
                                <th scope="col">Pincode</th>
                                <th scope="col">Delete</th>
                                <th scope="col">Edit</th>
                                <th scope="col">View</th>

                                {/* <th scope="col">Status</th> */}
                            </tr>
                        </thead>
                        <tbody>
                            {

                                findClient?.map((item) => (
                                    <tr key={item.id}>
                                        <td>{item.firstName}</td>
                                        <td>{item.lastName}</td>
                                        <td>{item.contactNo}</td>
                                        <td>{item.email}</td>
                                        <td>{item.amount}</td>
                                        <td>{item.enrollDate}</td>

                                        <td>{item.address.city}</td>
                                        <td>{item.address.state}</td>
                                        <td>{item.address.country}</td>
                                        <td>{item.address.pinCode}</td>

                                        <td>  <Link to="/" className="navLinks"> <button className="btn btn-danger btn-sm">Delete</button> </Link> </td>
                                        <td>  <Link to="/" className="navLinks">  <button className="btn btn-warning btn-sm">Edit</button> </Link> </td>
                                        <td> <Link to={`/client/view/${item.id}`} className="navLinks">  <button className="btn btn-success btn-sm">View Payments</button> </Link> </td>
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

export default Client;