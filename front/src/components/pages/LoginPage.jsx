import React, { useEffect } from "react";
import { useState } from "react";
export default function LoginPage(props) {
  const formRef = React.createRef();
  const [error, setError] = useState(false)
  const [state, setState] = useState({
    login: "",
    password: ""
  });
  const handleChange = (event) => {
    setState({ ...state, [event.target.name]: event.target.value});
  };
  const validateEmail = (email) => {
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
  };
  const handleSubmit = (event) =>{
    if (!validateEmail(state.login) || state.password.length <=0) {
      event.preventDefault();
      setError(true)
      event.stopPropagation();
    }
    else{
      setError(false)
    }
    }
  return (
    <div className="flex-shrink-0" style={{backgroundColor : 'rgb(255,255,255)'}}>
      <div className="d-flex justify-content-center">
        <form ref={formRef} noValidate className="w-50 ms-2 needs-validation" onSubmit={(e)=>handleSubmit(e)}>
          <h2 className="py-3">Вход</h2>
          <h4>Логин</h4>
          <input className="form-control my-2" name="login" value={state.login} onChange={(e)=>handleChange(e)} type="email" required />
          {
            error && !validateEmail(state.login)
            ?<div>
                Пожалуйста введите электронную почту вида: "*****@**.**"
              </div>
            :<div></div>
          }
          <h4>Пароль</h4>
          <input
            className="form-control my-2"
            type="password"
            name="password"
            value={state.password}
            onChange={(e)=>handleChange(e)}
            required
          />
          {
            error && state.password.length <=0
            ?<div>
                Пожалуйста введите пароль
              </div>
            :<div></div>
          }
          <div>
            <button className="btn btn-primary m-2" type="submit">
              Войти
            </button>
            <a href="" style={{marginTop: 1, marginLeft: 1}}>
              Зарегистрируйтесь, если нет аккаунта, здесь
            </a>
          </div>
        </form>
      </div>
    </div>
  );
}
