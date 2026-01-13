import {useState} from "react";
import {useCheckPointMutation} from "../../features/graph/graphApi.js";
import {useDispatch, useSelector} from "react-redux";
import { setR } from "./rSlice.js";
import "../../styles/UserPage.css"

let x = 0;

export const CoordinatesForm = () => {
    const dispatch = useDispatch();
    // const [x, setX] = useState(0);
    const [y, setY] = useState("");
    const r = useSelector(state => state.r.value);
    const [checkPoint, { isLoading, error }] = useCheckPointMutation();


    const handleSubmit = async (e) => {
        e.preventDefault();

        const yNum = Number(y);

        if (isNaN(yNum) || yNum < -5 || yNum > 3) {
            alert("Y должен быть числом от -5 до 3");
            return;
        }

        try {
            const result = await checkPoint({
                x: Number(x),
                y: yNum,
                r: Number(r),
                source: "form",
            }).unwrap();
        } catch (e) {
            console.error("Ошибка при отправке точки", e);
        }
    };

    return (
        <form className="coordinates-form" onSubmit={handleSubmit}>
            <div className="form-row">
                <span className="form-label">X:</span>
                <select
                    className="form-select"
                    value={x}
                    onChange={(e) => setX(e.target.value)}
                    onChange={() => { x = e.target.value }}
                >
                    {[-4,-3,-2,-1,0,1,2,3,4].map(v => (
                        <option key={v} value={v}>{v}</option>
                    ))}
                </select>
            </div>

            <div className="form-row">
                <span className="form-label">Y:</span>
                <input
                    className="form-input"
                    type="text"
                    value={y}
                    onChange={(e) => setY(e.target.value)}
                    placeholder="Введите от -5 до 3"
                />
            </div>

            <div className="form-row">
                <span className="form-label">R:</span>
                <select
                    className="form-select"
                    value={r}
                    onChange={(e) => dispatch(setR(Number(e.target.value)))}
                >
                    {[-4,-3,-2,-1,0,1,2,3,4].map(v => (
                        <option key={v} value={v}>{v}</option>
                    ))}
                </select>
            </div>

            <button
                type="submit"
                className="submit-button"
                disabled={isLoading}
            >
                {isLoading ? "Checking..." : "Check point"}
            </button>

            {error && (
                <div className="error-message">
                    {error.data?.message || "Произошла ошибка"}
                </div>
            )}
        </form>
    );
}