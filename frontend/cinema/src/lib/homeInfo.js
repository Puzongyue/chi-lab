import { sort } from "semver";
import { movies } from "../lib/movieList";
// import movies from "../lib/movieList";

let test = [
    {
        poster: "https://www.qfaxing.cn/upload/20201121/74b807b455dd63e9ad15a279242e7a4d.jpg",
        id: 9
    },
    {
        poster: "http://p1.meituan.net/movie/3b8dfbb74191487d65ec272d15561626532684.jpg",
        id: 1
    }
];

let currentMovies = [];
let incomingMovies = [];

movies.forEach(item => {
    if (item.score > -1) {
        currentMovies.push(item);
    }
    else {
        incomingMovies.push(item);
    }
});

currentMovies.sort((a, b) => b.boxOffice - a.boxOffice);

let currentMoviesInfo = [];
let incomingMoviesInfo = [];
let currentPos = 0;
let incomingPos = 0;

currentMovies.forEach(item => {
    if (currentPos < 9) {
        currentMoviesInfo.push({
            id: item.id,
            name: item.name,
            poster: item.poster,
            score: item.score,
            position: currentPos,
            boxOffice: item.boxOffice
        });
        currentPos += 1;
    }
});

incomingMovies.forEach(item => {
    if (incomingPos < 9) {
        incomingMoviesInfo.push({
            id: item.id,
            name: item.name,
            poster: item.poster,
            position: incomingPos
        });
        incomingPos += 1;
    }
});

let movieRankInfo = [];
let rank = 1;
currentMovies.forEach(item => {
    if (rank < 6) {
        movieRankInfo.push({
            id: item.id,
            name: item.name,
            boxOffice: item.boxOffice,
            rank: rank,
            poster: item.poster,
            types: item.types
        });
        rank += 1;
    }
});

movieRankInfo[0].poster = "https://p.pstatp.com/origin/1384800017fb9b6fe226a";
movieRankInfo[1].poster = "https://p1.meituan.net/movie/80a0afab60ba5bc548a0c606635fef291479960.jpg@120w_80h_1e_1c";
movieRankInfo[2].poster = "https://p.pstatp.com/origin/13748000322ff83d56861";

let homeInfo = {
    currentMoviesInfo: currentMoviesInfo,
    incomingMoviesInfo: incomingMoviesInfo,
    movieRankInfo: movieRankInfo,
    test: test
}

export default homeInfo;