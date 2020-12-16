const curTime = new Date();

const schedualList = [
  {
    id: "0",
    movieId: 0,
    hallId: 0,
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 1)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 4)),
    language: "原版3D",
    prize: 33.0
  },
  {
    id: "1",
    movieId: 0,
    hallId: 2,
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 3)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 5)),
    language: "原版3D",
    prize: 34.0
  },
  {
    id: "2",
    movieId: 0,
    hallId: 2,
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 10)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 14)),
    language: "原版3D",
    prize: 35.0
  },
  {
    id: "3",
    movieId: 0,
    hallId: 2,
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 24)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 25)),
    language: "原版3D",
    prize: 36.0
  },
  {
    id: "4",
    movieId: 0,
    hallId: 1,
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 45)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 47)),
    language: "原版3D",
    prize: 37.0
  },
  {
    id: "5",
    movieId: 0,
    hallId: 0,
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 48)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 50)),
    language: "原版3D",
    prize: 38.0
  }
];

export default schedualList;
