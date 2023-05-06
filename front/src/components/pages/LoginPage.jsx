import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import AuthService from "../../services/AuthService"
export default function LoginPage(props) {
  const formRef = React.createRef();
  const navigate = useNavigate();
  const [message, setMessage] = useState("");
  const [state, setState] = useState({
    login: "",
    password: ""
  });
  const handleChange = (event) => {
    setState({ ...state, [event.target.name]: event.target.value});
  };
  const handleSubmit = (event) =>{
    event.preventDefault();
    setMessage("");
    AuthService.login(state.login, state.password).then(
      () => {
        navigate("/");
        window.location.reload();
      },
      (error) => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setMessage(resMessage);
      }
    );
  }
  return (
    <div className="flex-shrink-0" style={{backgroundColor : 'rgb(255,255,255)'}}>
      <div className="d-flex justify-content-center">
        <form ref={formRef} className="w-50 ms-2" onSubmit={(e)=>handleSubmit(e)}>
          <h2 className="py-3">Вход</h2>
          <h4>Логин</h4>
          <input className="form-control my-2" name="login" value={state.login} onChange={(e)=>handleChange(e)} type="text" required />
          <h4>Пароль</h4>
          <input
            className="form-control my-2"
            type="password"
            name="password"
            value={state.password}
            onChange={(e)=>handleChange(e)}
            required
          />
          <div>
            <button className="btn btn-primary m-2" type="submit">
              Войти
            </button>
            <a href="/Signup" style={{marginTop: 1, marginLeft: 1}}>
              Зарегистрируйтесь, если нет аккаунта, здесь
            </a>
          </div>
        </form>
        {message && (
            <div className="form-group">
              <div className="alert alert-danger" role="alert">
                {message}
              </div>
            </div>
          )}
      </div>
    </div>
  );
}
