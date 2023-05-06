import { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import AuthService from "../../services/AuthService";

export default function Header(props) {
  const navigate = useNavigate();
  const[state, setState] = useState({
    text:""
  });
  const handleChange = (event) => {
    setState({ ...state, [event.target.id]: event.target.value });
  };
  const handleLogout = () =>{
    AuthService.logout();
    navigate("/Login");
    window.location.reload();
  };
  const handleSubmit = (event) => {
    event.preventDefault();
    props.setSearchValue(state.text)
  };
  return (
    <header style={{ backgroundColor: "rgb(235,185,185)" }}>
      <div className="container-fluid">
        <div className="row align-items-start">
          <div className="col">
            <img className="img-fluid p-3" src="i.png" />
          </div>
          <div className="col align-items-center">
            <nav className="navbar navbar-expand-lg">
              <div className="container-fluid d-flex flex-column">
                <button
                  className="navbar-toggler"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#navbarSupportedContent"
                  aria-controls="navbarSupportedContent"
                  aria-expanded="false"
                  aria-label="Toggle navigation"
                >
                  {" "}
                  <span className="navbar-toggler-icon"></span>
                </button>
                <h1 className="text-center">Онлайн-библиотека</h1>
                <div
                  className="collapse navbar-collapse"
                  id="navbarSupportedContent"
                >
                  <ul className="nav nav-tabs justify-content-center border-bottom-0">
                    {props.links.map((route) => (
                      <li key={route.path} className="nav-item">
                        {
                          (localStorage.getItem("role") === "ADMIN" || route.label !== "Пользователи") ?
                          <Link className="nav-link" to={route.path}>
                          {route.label}
                          </Link> : null
                        }
                      </li>
                    ))}
                  </ul>
                </div>
                <form className="w-100" onSubmit={handleSubmit}>
                  <input
                    className="p-2 form-control"
                    value={state.text}
                    id='text'
                    onChange={(e)=>handleChange(e)}
                    type="search"
                    placeholder="Найти"
                  />
                </form>
              </div>
            </nav>
          </div>
          <span className="col text-end">
            {typeof localStorage.getItem("role") === 'string' &&
              <Link className="nav-link" onClick={handleLogout} to={""}>
              {"Выход(" + localStorage.getItem("login") + ")"}
              </Link>
            }
          </span>
        </div>
      </div>
    </header>
  );
}
