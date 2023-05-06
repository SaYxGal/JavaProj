import { Outlet, Navigate } from 'react-router-dom'

const PrivateRoutes = (props) => {
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