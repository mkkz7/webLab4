import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    isAuth: false,
    isLoading: false,
    error: null
}

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers:{
        loginSuccess(state){
            state.isAuth = true;
        },
        logout(state){
            state.isAuth = false;
        }
    }
});

export const {loginSuccess, logout} = authSlice.actions;
export default authSlice.reducer;