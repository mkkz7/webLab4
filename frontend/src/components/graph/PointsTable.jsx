import {useDeletePointMutation, useGetPointsQuery, usePollPointsQuery} from "../../features/graph/graphApi.js";
import {useEffect, useState} from "react";
import "../../styles/UserPage.css"
import { createPortal } from "react-dom";


export const PointsTable = () => {
    const {data: points, isLoading, isError} = useGetPointsQuery();
    const [deletePoint] = useDeletePointMutation();
    const [contextMenu, setContextMenu] = useState(null);

    usePollPointsQuery();

    const handleRightClick = (e, point) => {
        e.preventDefault();

        setContextMenu({
            point: point,
            x: e.clientX,
            y: e.clientY,
        });
    };

    useEffect(() => {
        if (!contextMenu) return;

        const close = (e) => {
            if (e.target.closest(".context-menu")) return;
            setContextMenu(null);
        };

        window.addEventListener("click", close);

        return () => window.removeEventListener("click", close);
    }, [contextMenu]);

    const handleDelete = async () => {
        if (!contextMenu?.point) return;

        try {
            await deletePoint(contextMenu.point).unwrap();
            console.log('Point deleted successfully');
        } catch (error) {
            console.error('Delete failed:', error);
            alert(error?.data?.message || 'Cannot delete point');
        } finally {
            setContextMenu(null);
        }
    };

    if (isLoading) return <p>Loading...</p>;
    if (isError) return <p>Error while loading points</p>;
    if (!points || points.length === 0) return <p>-\0/-</p>;

    return (
        <div className="table-wrapper">
            <table className="points-table">
                <thead>
                <tr>
                    <th>user</th>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Hit</th>
                    <th>Exec Time</th>
                </tr>
                </thead>
                <tbody>
                {points.map((point) => (
                    <tr key={point.id} className={point.hit ? 'hit-row' : 'miss-row'} onContextMenu={(e) =>{
                        console.log("RIGHT CLICK", point);
                        e.preventDefault();
                        handleRightClick(e, point)
                    }}>
                        <td>{point.username}</td>
                        <td>{point.x}</td>
                        <td>{point.y}</td>
                        <td>{point.r}</td>
                        <td className={point.hit ? 'hit-yes' : 'hit-no'}>
                            {point.hit ? 'Y' : 'N'}
                        </td>
                        <td>{point.execTime.toFixed(4)}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            {contextMenu &&
                createPortal(
                    <div
                        className="context-menu"
                        onMouseDown={(e) => e.stopPropagation()}
                        style={{
                            position: "fixed",
                            top: contextMenu.y,
                            left: contextMenu.x,
                        }}
                    >
                        <button
                            className="context-menu-item delete"
                            onClick={handleDelete}
                        >
                            Delete This Point
                        </button>

                        <button
                            className="context-menu-item"
                            onClick={() => setContextMenu(null)}
                        >
                            ✕ Close
                        </button>
                    </div>,
                    document.body
                )
            }


            {points.length === 0 && (
                <div className="empty-table">
                    <p>No data!</p>
                </div>
            )}
        </div>
    );
}