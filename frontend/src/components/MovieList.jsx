import React from 'react'
import { useSelector } from 'react-redux'

export const MovieList = () => {
    const movies = useSelector((state) =>state.moviesReducer.movies)
    return (
        <div>
          {" "}
          <h1>Movie List</h1>
          {movies.map((movie) => (
            <div id="movie-name" key={movie.id}>{movie.name}</div>
          ))}
        </div>
      );
}