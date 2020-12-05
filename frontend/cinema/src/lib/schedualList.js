const curTime = new Date();

const schedualList = [
  {
    id: "0",
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 2)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 4)),
    language: "原版3D",
    theater: "1号厅",
    prize: 33.0
  },
  {
    id: "1",
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 3)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 5)),
    language: "原版3D",
    theater: "1号厅",
    prize: 33.0
  },
  {
    id: "2",
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 10)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 14)),
    language: "原版3D",
    theater: "1号厅",
    prize: 33.0
  },
  {
    id: "3",
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 24)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 25)),
    language: "原版3D",
    theater: "1号厅",
    prize: 33.0
  },
  {
    id: "4",
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 45)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 47)),
    language: "原版3D",
    theater: "1号厅",
    prize: 33.0
  },
  {
    id: "5",
    startTime: curTime.setTime(curTime.setHours(curTime.getHours() + 48)),
    endTime: curTime.setTime(curTime.setHours(curTime.getHours() + 50)),
    language: "原版3D",
    theater: "1号厅",
    prize: 33.0
  }
];

export default schedualList;
