import {createSlice} from "@reduxjs/toolkit";

const rSlice = createSlice({
    name: "r",
    initialState: {value: 1},
    reducers: {
        setR: (state, action) => {
            state.value = action.payload;
        },
    },
})

export const { setR } = rSlice.actions;
export default rSlice.reducer;