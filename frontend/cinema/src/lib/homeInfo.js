import {movies} from "../lib/movieList";
// import movies from "../lib/movieList";

let currentMoviesInfo  = [];
let pos = 0;
movies.forEach(item=>{
    if(item.score > -1){
        currentMoviesInfo.push({
            id: item.id,
            name: item.name,
            poster: item.poster,
            score: item.score,
            position: pos
        });
        pos += 1;
    }
});

export default currentMoviesInfo;