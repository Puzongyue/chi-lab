const curTime = new Date();

const schedualList = [
  {
    id: 0,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-28 10:45"),
    endTime: new Date("2020-12-28 12:53"),
    language: "原版3D",
    prize: 33.0
  },
  {
    id: 1,
    movieId: 0,
    hallId: 1,
    startTime:new Date("2020-12-28 11:28"),
    endTime: new Date("2020-12-28 13:36"),
    language: "原版3D",
    prize: 34.0
  },
  {
    id: 2,
    movieId: 0,
    hallId: 2,
    startTime:new Date("2020-12-28 11:45"),
    endTime:new Date("2020-12-28 13:53"),
    language: "原版3D",
    prize: 35.0
  },
  {
    id: 3,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-28 13:10"),
    endTime: new Date("2020-12-28 15:18"),
    language: "原版3D",
    prize: 36.0
  },
  {
    id: 4,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-28 14:10"),
    endTime: new Date("2020-12-28 16:18"),
    language: "原版3D",
    prize: 37.0
  },
  {
    id: 5,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2020-12-28 15:35"),
    endTime: new Date("2020-12-28 17:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 6,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-28 16:35"),
    endTime: new Date("2020-12-28 18:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 7,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-28 17:15"),
    endTime: new Date("2020-12-28 19:23"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 8,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2020-12-28 19:00"),
    endTime: new Date("2020-12-28 21:08"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 9,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-28 21:25"),
    endTime: new Date("2020-12-28 23:33"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 10,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-29 10:45"),
    endTime: new Date("2020-12-29 12:53"),
    language: "原版3D",
    prize: 33.0
  },
  {
    id: 11,
    movieId: 0,
    hallId: 2,
    startTime:new Date("2020-12-29 11:28"),
    endTime: new Date("2020-12-29 13:36"),
    language: "原版3D",
    prize: 34.0
  },
  {
    id: 12,
    movieId: 0,
    hallId: 0,
    startTime:new Date("2020-12-29 11:45"),
    endTime:new Date("2020-12-29 13:53"),
    language: "原版3D",
    prize: 35.0
  },
  {
    id: 13,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-29 13:10"),
    endTime: new Date("2020-12-29 15:18"),
    language: "原版3D",
    prize: 36.0
  },
  {
    id: 14,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2020-12-29 14:10"),
    endTime: new Date("2020-12-29 16:18"),
    language: "原版3D",
    prize: 37.0
  },
  {
    id: 15,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-29 15:35"),
    endTime: new Date("2020-12-29 17:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 16,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-29 16:35"),
    endTime: new Date("2020-12-29 18:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 17,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2020-12-29 17:15"),
    endTime: new Date("2020-12-29 19:23"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 18,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-29 19:00"),
    endTime: new Date("2020-12-29 21:08"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 19,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-29 21:25"),
    endTime: new Date("2020-12-29 23:33"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 20,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-30 10:45"),
    endTime: new Date("2020-12-30 12:53"),
    language: "原版3D",
    prize: 33.0
  },
  {
    id: 21,
    movieId: 0,
    hallId: 1,
    startTime:new Date("2020-12-30 11:28"),
    endTime: new Date("2020-12-30 13:36"),
    language: "原版3D",
    prize: 34.0
  },
  {
    id: 22,
    movieId: 0,
    hallId: 2,
    startTime:new Date("2020-12-30 11:45"),
    endTime:new Date("2020-12-30 13:53"),
    language: "原版3D",
    prize: 35.0
  },
  {
    id: 23,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-30 13:10"),
    endTime: new Date("2020-12-30 15:18"),
    language: "原版3D",
    prize: 36.0
  },
  {
    id: 24,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-30 14:10"),
    endTime: new Date("2020-12-30 16:18"),
    language: "原版3D",
    prize: 37.0
  },
  {
    id: 25,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2020-12-30 15:35"),
    endTime: new Date("2020-12-30 17:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 26,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-30 16:35"),
    endTime: new Date("2020-12-30 18:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 27,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-30 17:15"),
    endTime: new Date("2020-12-30 19:23"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 28,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2020-12-30 19:00"),
    endTime: new Date("2020-12-30 21:08"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 29,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-30 21:25"),
    endTime: new Date("2020-12-30 23:33"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 30,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-31 10:45"),
    endTime: new Date("2020-12-31 12:53"),
    language: "原版3D",
    prize: 33.0
  },
  {
    id: 31,
    movieId: 0,
    hallId: 2,
    startTime:new Date("2020-12-31 11:28"),
    endTime: new Date("2020-12-31 13:36"),
    language: "原版3D",
    prize: 34.0
  },
  {
    id: 32,
    movieId: 0,
    hallId: 0,
    startTime:new Date("2020-12-31 11:45"),
    endTime:new Date("2020-12-31 13:53"),
    language: "原版3D",
    prize: 35.0
  },
  {
    id: 33,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-31 13:10"),
    endTime: new Date("2020-12-31 15:18"),
    language: "原版3D",
    prize: 36.0
  },
  {
    id: 34,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2020-12-31 14:10"),
    endTime: new Date("2020-12-31 16:18"),
    language: "原版3D",
    prize: 37.0
  },
  {
    id: 35,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-31 15:35"),
    endTime: new Date("2020-12-31 17:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 36,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-31 16:35"),
    endTime: new Date("2020-12-31 18:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 37,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2020-12-31 17:15"),
    endTime: new Date("2020-12-31 19:23"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 38,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2020-12-31 19:00"),
    endTime: new Date("2020-12-31 21:08"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 39,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2020-12-31 21:25"),
    endTime: new Date("2020-12-31 23:33"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 40,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2021-1-1 10:45"),
    endTime: new Date("2021-1-1 12:53"),
    language: "原版3D",
    prize: 33.0
  },
  {
    id: 41,
    movieId: 0,
    hallId: 1,
    startTime:new Date("2021-1-1 11:28"),
    endTime: new Date("2021-1-1 13:36"),
    language: "原版3D",
    prize: 34.0
  },
  {
    id: 42,
    movieId: 0,
    hallId: 2,
    startTime:new Date("2021-1-1 11:45"),
    endTime:new Date("2021-1-1 13:53"),
    language: "原版3D",
    prize: 35.0
  },
  {
    id: 43,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2021-1-1 13:10"),
    endTime: new Date("2021-1-1 15:18"),
    language: "原版3D",
    prize: 36.0
  },
  {
    id: 44,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2021-1-1 14:10"),
    endTime: new Date("2021-1-1 16:18"),
    language: "原版3D",
    prize: 37.0
  },
  {
    id: 45,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2021-1-1 15:35"),
    endTime: new Date("2021-1-1 17:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 46,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2021-1-1 16:35"),
    endTime: new Date("2021-1-1 18:43"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 47,
    movieId: 0,
    hallId: 1,
    startTime: new Date("2021-1-1 17:15"),
    endTime: new Date("2021-1-1 19:23"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 48,
    movieId: 0,
    hallId: 2,
    startTime: new Date("2021-1-1 19:00"),
    endTime: new Date("2021-1-1 21:08"),
    language: "原版3D",
    prize: 38.0
  },
  {
    id: 49,
    movieId: 0,
    hallId: 0,
    startTime: new Date("2021-1-1 21:25"),
    endTime: new Date("2021-1-1 23:33"),
    language: "原版3D",
    prize: 38.0
  },
];

export default schedualList;

export function getScheduleById(id) {
  let schedule = schedualList.filter(item => item.id === id);
  return schedule[0];
}