import {useClearMutation} from "../../features/auth/authApi.js";
import {useDispatch} from "react-redux";
import {setPoints} from "../graph/pointsSlice.js";
import { graphApi } from "../../features/graph/graphApi";
import "../../styles/UserPage.css"

export const ClearButton = () => {
    const [clearMutation, {isLoading}] = useClearMutation();
    const dispatch = useDispatch();

    const handleClick = async (e) => {
        e.preventDefault();
        try {
            await clearMutation().unwrap();
            dispatch(setPoints([]));
            dispatch(graphApi.util.resetApiState());
        } catch (err) {
            console.error("Ошибка clear:", err);
        }
    };

    return (
        <button
            onClick={handleClick}
            className="clear-button"
            disabled={isLoading}
        >
            {isLoading ? 'Clearing...' : 'Clear'}
        </button>
    )
}