import React, { useRef, useEffect } from "react";
import { useSelector} from "react-redux";
import {useCheckPointMutation, useGetPointsQuery, usePollPointsQuery} from "../../features/graph/graphApi.js";
import "../../styles/UserPage.css"

export const Graph = () => {
    const canvasRef = useRef(null);
    const r = useSelector(state => state.r.value);
    const { data: points = []} = useGetPointsQuery();
    const center = { x: 150, y: 150 };
    const [checkPoint] = useCheckPointMutation();

    usePollPointsQuery();


    useEffect(() => {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext("2d");
        const scale = 100 / r;
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        const sign = Math.sign(r) || 1;

        ctx.strokeStyle = "#ffffff";
        ctx.fillStyle = "#ffffff";
        ctx.lineWidth = 1;
        ctx.font = "12px Arial";
        ctx.textBaseline = "middle";

        ctx.beginPath();
        ctx.moveTo(0, center.y);
        ctx.lineTo(canvas.width, center.y);
        ctx.moveTo(center.x, 0);
        ctx.lineTo(center.x, canvas.height);
        ctx.stroke();

        ctx.beginPath();
        ctx.moveTo(center.x, 0); ctx.lineTo(center.x-6, 12); ctx.lineTo(center.x+6, 12); ctx.closePath(); ctx.fill();
        ctx.beginPath();
        ctx.moveTo(canvas.width, center.y); ctx.lineTo(canvas.width-12, center.y-6); ctx.lineTo(canvas.width-12, center.y+6); ctx.closePath(); ctx.fill();


        const ticks = [-r, -r/2, r/2, r];
        ctx.fillStyle = "#cccccc";
        ticks.forEach(t => {
            // OX
            ctx.beginPath();
            ctx.moveTo(center.x + t*scale, center.y-5);
            ctx.lineTo(center.x + t*scale, center.y+5);
            ctx.stroke();
            ctx.fillText(t.toString(), center.x + t*scale - 10, center.y + 15);

            // OY
            ctx.beginPath();
            ctx.moveTo(center.x-5, center.y - t*scale);
            ctx.lineTo(center.x+5, center.y - t*scale);
            ctx.stroke();
            ctx.fillText(t.toString(), center.x + 10, center.y - t*scale);
        });


        ctx.fillText("X", canvas.width - 15, center.y + 15);
        ctx.fillText("Y", center.x + 10, 15);

        ctx.fillStyle = "rgba(0, 180, 255, 0.3)";

        // rectangle (II четверть)
        ctx.fillRect(center.x, center.y, sign * (r/2) * scale, sign * r * scale);

        // triangle (III четверть)
        ctx.beginPath();
        ctx.moveTo(center.x, center.y);
        ctx.lineTo(center.x - sign * r*scale, center.y);
        ctx.lineTo(center.x, center.y + sign * r*scale);
        ctx.closePath();
        ctx.fill();

        // sector (I четверть)
        ctx.beginPath();
        ctx.moveTo(center.x, center.y);
        // ctx.arc(center.x, center.y, r*scale/2, Math.PI * 1.5, 0, false);

        if (r > 0) {
            ctx.arc(center.x, center.y, r*scale/2, 1.5 * Math.PI, 0, false);
        } else {
            ctx.arc(center.x, center.y, r*scale/2, 0.5 * Math.PI, Math.PI, false);
        }
        ctx.closePath();
        ctx.fill();

        points.forEach(p => {
            ctx.fillStyle = p.hit ? "#00bfff" : "#ff5050";
            ctx.beginPath();
            ctx.arc(center.x + p.x*scale, center.y - p.y*scale, 3, 0, 2*Math.PI);
            ctx.fill();
        });
    }, [r, points]);

    const handleClick = async (e) => {
        const rect = canvasRef.current.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;
        const scale = 100 / r;
        const xVal = (x - center.x) / scale;
        const yVal = (center.y - y) / scale;

        console.log("Клик по графику:", { x: xVal, y: yVal, r });

        const result = await checkPoint({
            x: xVal,
            y: yVal,
            r: r,
            source: "graph",
        }).unwrap();

    };

    return <canvas ref={canvasRef} width={300} height={300} onClick={handleClick} />;
};
