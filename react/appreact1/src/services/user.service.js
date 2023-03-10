import axios from "axios";
import authHeader from "./auth-header";

const OWNER_API_URL = 'http://localhost:8080/owners/';
const PROPERTY_API_URL = 'http://localhost:8080/properties';
const CLIENT_API_URL = 'http://localhost:8080/clients';
const Payment_API_URL = 'http://localhost:8080/payments';



const getUser = (id) => {
    return axios.get(OWNER_API_URL + id, { headers: authHeader() });
}
const addProperty = (userProp, address) => {
    userProp.address = address;
    console.log("nikhil");
    console.log(userProp);
    return axios.post(PROPERTY_API_URL, userProp, { headers: authHeader() });
}

const addClient = (userClient, address) => {
    userClient.address = address;
    console.log(userClient);
    return axios.post(CLIENT_API_URL, userClient, { headers: authHeader() });
}

const addPayment = (userPayment) => {
    console.log(userPayment);
    return axios.post(Payment_API_URL, userPayment, { headers: authHeader() });
}

const deleteProperty = (id) => {
    console.log(id);
    return axios.delete(PROPERTY_API_URL + "/" + id, { headers: authHeader() });
}

const UserService = {
    getUser,
    addProperty,
    addClient,
    addPayment,
    deleteProperty
};

export default UserService;