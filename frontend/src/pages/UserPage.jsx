import {PointsTable} from "../components/graph/PointsTable.jsx";
import {CoordinatesForm} from "../components/graph/CoordinatesForm.jsx";
import {LogoutButton} from "../components/UI/LogoutButton.jsx";
import {Graph} from "../components/graph/Graph.jsx";
import {useDispatch} from "react-redux";
import {useGetPointsQuery} from "../features/graph/graphApi.js";
import {useEffect} from "react";
import {setPoints} from "../components/graph/pointsSlice.js";
import {ClearButton} from "../components/UI/ClearButton.jsx";

export const UserPage = () => {
    const dispatch = useDispatch();
    const { data: pointsData } = useGetPointsQuery();

    useEffect(() => {
        if (pointsData) {
            dispatch(setPoints(pointsData));
        }
    }, [pointsData, dispatch]);

    return (
        <div className="user-page-container">
            <div className="user-header-container">
                <LogoutButton/>
                <ClearButton/>
            </div>


            <div className="user-main-grid">
                <Graph />
                <CoordinatesForm />
                <PointsTable />
            </div>
        </div>
    );
}