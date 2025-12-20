import { useState } from 'react';
import { addMovie } from '../movieSlice';
import { useDispatch } from 'react-redux';
 
export const MovieInput = () => {
    const [newMovie, setNewMovie] = useState("");

    const dispatch = useDispatch();

    const handleNewMovie = () =>{
        if(newMovie){
            dispatch(addMovie(newMovie));
            setNewMovie("");
        }
    }
    return (
        <>
            <input 
                onChange={(event) => setNewMovie(event.target.value)}
                value={newMovie}/>
            <button onClick={handleNewMovie}>Add movie</button>
        </>
    );
};