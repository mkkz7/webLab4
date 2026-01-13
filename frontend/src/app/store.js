import { configureStore } from "@reduxjs/toolkit";
import { authApi } from "../features/auth/authApi.js";
import authReducer from "../features/auth/authSlice.js"
import {graphApi} from "../features/graph/graphApi.js";
import rReducer from "../components/graph/rSlice.js";
import pointsReducer from "../components/graph/pointsSlice.js"

export const store = configureStore({
  reducer: {
    auth: authReducer,
    r: rReducer,
    points: pointsReducer,
    [authApi.reducerPath]: authApi.reducer,
    [graphApi.reducerPath]: graphApi.reducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware()
        .concat(authApi.middleware)
        .concat(graphApi.middleware)
});
