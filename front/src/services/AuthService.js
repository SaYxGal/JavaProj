import axios from "axios";
import UserSignUpDto from "../modelsDTO/UserSignUpDto";
import UserLoginDto from "../modelsDTO/UserLoginDto";

const API_URL = "http://localhost:8080";

const register = (username, password, passwordConfirm) => {
  return axios.post(API_URL + "/signup", new UserSignUpDto({login: username,
     password: password,
     passwordConfirm: passwordConfirm}));
};

const login = (username, password) => {
  return axios
    .post(API_URL + "/jwt/login", new UserLoginDto({login: username, password: password}))
    .then((response) => {
      if(response.status === 200){
        localStorage.setItem("token", response.data)
      }
    });
};

const logout = () => {
  localStorage.removeItem("token");
};
const AuthService = {
    register,
    login,
    logout,
  };

export default AuthService;