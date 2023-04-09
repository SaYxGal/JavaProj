import { NavLink } from "react-router-dom";
export default function Footer(props) {
  return (
    <footer className="mt-auto" style={{ backgroundColor: "rgb(235,185,185)" }}>
      <div className="container-fluid p-4 ms-0 border-bottom border-white">
        <div className="col-lg-3 col-md-6 mb-4 mb-md-0">
          <ul className="list-unstyled mb-0">
            <li>
              <NavLink className="nav-link" to={props.link.path}>
                Контакты
              </NavLink>
            </li>
            <li>
              <a className="nav-link" href="#">
                Политика приватности
              </a>
            </li>
          </ul>
        </div>
      </div>
      <div className="text-end pb-1 pe-1">Малин Данил, 2022</div>
    </footer>
  );
}
