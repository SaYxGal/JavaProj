import { useEffect } from 'react';
import { Outlet, Navigate, useLocation } from 'react-router-dom'
import DataService from '../services/DataService';

const PrivateRoutes = (props) => {
    let location = useLocation()
    useEffect(()=>{
        try{
            DataService.readUser("http://localhost:8080","/users",localStorage.getItem("login")).then((data)=>{
            if(data.authorities[0] != localStorage.getItem("role")){
              throw new SyntaxError("Данные не совпадают")
            }
            }).catch((e) => {
                localStorage.clear();
                window.location.reload();
                console.log(e)
            })
        }
        catch(e){
            localStorage.clear();
            window.location.reload();
            console.log(e)
        }
    },[location])
    let getPermission = false;
    let userToken = localStorage.getItem("token");
    let userRole = localStorage.getItem("role");
    if(userToken && (props.role == userRole || userRole == "ADMIN")){
        getPermission = true;
    }
    return(
        getPermission ? <Outlet/> : <Navigate to="/Login"/>
    )
}

export default PrivateRoutes