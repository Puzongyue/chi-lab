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

let carouselInfo = [
    "https://www.qfaxing.cn/upload/20201121/74b807b455dd63e9ad15a279242e7a4d.jpg",
    "http://p1.meituan.net/movie/3b8dfbb74191487d65ec272d15561626532684.jpg"
]

let homeInfo = {
    "currentMoviesInfo": currentMoviesInfo,
    "carouselInfo": carouselInfo
}

export default homeInfo;