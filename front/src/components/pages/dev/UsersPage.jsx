import DataService from "../../../services/DataService";
import { useState, useEffect } from "react";
export default function UsersPage(props) {
    const [users, setUsers] = useState([]);
    const [pageNumbers, setPageNumbers] = useState([]);
    const [pageNumber, setPageNumber] = useState();
    const usersUrl = "/users";
    const host = "http://localhost:8080/api/1.0";

    useEffect(() => {
        DataService.readUsersPage(host,usersUrl, 1).then((data)=>{
            setUsers(data.first.content);
            setPageNumbers(data.second);
            setPageNumber(1);
        });
    }, []);
    const pageButtonOnClick = function (page) {
        DataService.readUsersPage(host,usersUrl, page).then((data)=>{
            setUsers(data.first.content);
            setPageNumber(page);
        });
    }
    return (
        <>
            <div className="table-shell mb-3">
                <table className="table">
                    <thead>
                    <tr>
                        <th style={{ width: "15%" }} scope="col">ID</th>
                        <th style={{ width: "30%" }} scope="col">Логин</th>
                        <th style={{ width: "15%" }} scope="col">Роль</th>
                    </tr>
                    </thead>
                    <tbody>
                        {users.map((user, index) => (
                            <tr>
                                <td style={{ width: "15%" }}>{user.id}</td>
                                <td style={{ width: "30%" }}>{user.login}</td>
                                <td style={{ width: "15%" }}>{user.role}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <div>
                <p>
                    Pages:
                </p>
                <nav>
                    <ul className="pagination">
                        {pageNumbers.map((number) => (
                            <li className={`page-item ${number === pageNumber + 1 ? "active" : ""}`}
                                onClick={() => pageButtonOnClick(number)}>
                                <a className="page-link" href="#">{number}</a>
                            </li>
                        ))}
                    </ul>
                </nav>
            </div>
        </>
    );
}