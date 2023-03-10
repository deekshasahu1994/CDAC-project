import axios from "axios";
const API_URL = "http://localhost:8080/owners/";

const register = (userReg) => {
  return axios.post(API_URL + "signUp", userReg);
};

const login = (email, password) => {
  return axios
    .post(API_URL + "signIn", {
      email,
      password,

    })
    .then((response) => {
      console.log(response.data)
      if (response.data.token) {
        localStorage.setItem("user", JSON.stringify(response.data));
        localStorage.setItem("currentUser", JSON.stringify(response.data.user));
      }

      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem("user");
  localStorage.removeItem("currentUser");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("currentUser"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
};

export default AuthService;