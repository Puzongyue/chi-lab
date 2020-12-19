const halls = [
  {
    id: 0,
    name: "1号厅",
    row: 9,
    column: 12
  },

  {
    id: 1,
    name: "2号厅",
    row: 6,
    column: 8
  },

  {
    id: 2,
    name: "3号厅",
    row: 12,
    column: 18
  }
];

export default halls;

export function getHallById(id) {
  let ans = halls.filter(item => item.id === id);
  return ans[0];
}