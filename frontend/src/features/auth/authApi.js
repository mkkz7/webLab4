import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const authApi = createApi({
    reducerPath: "authApi",
    baseQuery: fetchBaseQuery({ baseUrl: "http://localhost:8080/backend-app/api", credentials: "include" }),
    tagTypes: ["Auth"],
    endpoints: (builder) => ({
        register: builder.mutation({
            query:(body) => ({
                url: "/auth/registration",
                method: "POST",
                body
            })
        }),

        login: builder.mutation({
            query:(body) => ({
                url: "/auth/login",
                method: "POST",
                body
            }),
            invalidatesTags: ["Auth"]
        }),

        logout: builder.mutation({
            query:(body) => ({
                url: "/auth/logout",
                method: "POST",
                body: undefined,
            })
        }),
        clear: builder.mutation({
            query:(body) => ({
                url: "/auth/clear",
                method: "DELETE",
                body: undefined,
            })
        }),
        me: builder.query({
            query: () => "/auth/me",
            method: "GET",
            providesTags: ["Auth"],
        })
    })
})

export const {useRegisterMutation, useLoginMutation, useLogoutMutation, useClearMutation, useMeQuery} = authApi;