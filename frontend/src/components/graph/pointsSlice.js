import { createSlice } from "@reduxjs/toolkit";

const pointsSlice = createSlice({
    name: "points",
    initialState: [],
    reducers: {
        setPoints: (state, action) => action.payload,
        addPoint: (state, action) => { state.push(action.payload) },
    },
});

export const { setPoints, addPoint } = pointsSlice.actions;
export default pointsSlice.reducer;
