import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";

export const graphApi = createApi({
    reducerPath: "graphApi",
    baseQuery: fetchBaseQuery({
        baseUrl: "http://localhost:8080/backend-app/api",
        credentials: "include",
    }),
    tagTypes: ["Points"],
    endpoints: (builder) => ({
        getPoints: builder.query({
            query: () => "/graph/get",
            providesTags: ["Points"],
        }),

        checkPoint: builder.mutation({
            query: (body) => ({
                url: "/graph/check",
                method: "POST",
                body,
            }),
            invalidatesTags: ["Points"],
        }),

        deletePoint: builder.mutation({
            query: (point) => ({
                url: "/graph/deletepoint",
                method: "DELETE",
                body: point,
            }),
            invalidatesTags: ["Points"],
        }),

        pollPoints: builder.query({
            query: () => "/graph/poll",
            async onCacheEntryAdded(
                arg,
                { dispatch, cacheEntryRemoved }
            ) {
                try {
                    while (true) {
                        const response = await fetch(
                            "http://localhost:8080/backend-app/api/graph/poll",
                            {
                                credentials: "include",
                            }
                        );

                        if (response.status === 204) {
                            continue;
                        }

                        if (response.ok) {
                            const data = await response.json();

                            dispatch(
                                graphApi.util.updateQueryData(
                                    "getPoints",
                                    undefined,
                                    () => data
                                )
                            );
                        }
                    }
                } catch (e) {
                    console.error("poll error", e);
                }

                await cacheEntryRemoved;
            },
        }),

    }),
});

export const {useGetPointsQuery, useCheckPointMutation, usePollPointsQuery, useDeletePointMutation} = graphApi;
