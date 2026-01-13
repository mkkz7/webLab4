import { Navigate, Outlet } from "react-router-dom";
import {useMeQuery} from "../features/auth/authApi.js";

export const PrivateRoute = () => {
    const {isLoading, isError, isFetching} = useMeQuery();

    if(isLoading || isFetching){
        return <div>Checking session...</div>
    }

    if(isError){
        return <Navigate to="/" replace></Navigate>
    }

    return <Outlet/>
};
