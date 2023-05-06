import { Outlet, Navigate } from 'react-router-dom'

const PrivateRoutes = () => {
    let getPermission = false;
    let userToken = localStorage.getItem("token");
    if(userToken){
        getPermission = true;
    }
    return(
        getPermission ? <Outlet/> : <Navigate to="/Login"/>
    )
}

export default PrivateRoutes