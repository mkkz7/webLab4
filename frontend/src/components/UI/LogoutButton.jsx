import {useLogoutMutation} from "../../features/auth/authApi.js";
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import "../../styles/UserPage.css"

export const LogoutButton = () => {
    const [logoutMutation] = useLogoutMutation();
    const navigate = useNavigate();

    const handleClick = async (e) => {
        e.preventDefault();
        try {
            await logoutMutation().unwrap();
            navigate("/");
        } catch (err) {
            console.error("Ошибка logout:", err);
        }
    };

    return (
        <a href={"/"} onClick={handleClick} className="logout-button">
            Log out
        </a>
    )
}