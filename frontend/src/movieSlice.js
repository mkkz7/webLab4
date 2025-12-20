import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    movies: [
        {id: 1, name: "Iron man" },
        {id: 2, name: "Iron man 2" }
    ]
}

const movieSlice = createSlice({
    name: "movies",
    initialState,
    reducers: {
        addMovie: (state, action) => {
            const newMovie = {
            id: state.movies[state.movies.length - 1] + 1,
            name: action.payload
            };

            state.movies.push(newMovie);
            console.log(initialState.movies)
        },
        removeMovie: (state, action) => {
            
        }
    }
});

export const {addMovie, removeMovie} = movieSlice.actions;
export default movieSlice.reducer;