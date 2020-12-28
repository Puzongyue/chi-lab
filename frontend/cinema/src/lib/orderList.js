// order status:[0: 未付款, 1: 已付款, 2:已失效(未付款且超过15分钟) , 3: 已过期]
// 本系统唯一可活动用户 id = 0
export const orders = [
  //1号厅
  {
    id: 0,
    schedualId: 0,
    userId: 0,
    tickets: [
      [0, 0],
      [0, 1]
    ],
    placeTime: new Date(),
    status: 0
  },
  {
    id: 1,
    schedualId: 0,
    userId: 0,
    tickets: [
      [1, 0],
      [1, 1]
    ],
    placeTime: new Date(),
    status: 1
  },
  {
    id: 2,
    schedualId: 0,
    userId: 0,
    tickets: [[2, 0]],
    placeTime: new Date(),
    status: 2
  },
  {
    id: 3,
    schedualId: 50,
    userId: 0,
    tickets: [
      [3, 0],
      [3, 1],
      [3, 2],
      [3, 3],
      [3, 4],
      [3, 5]
    ],
    placeTime: new Date(),
    status: 3
  },
  //其他用户
  {
    id: 4,
    schedualId: 0,
    userId: 1,
    tickets: [
      [5, 6],
      [6, 5]
    ],
    placeTime: new Date(),
    status: 0
  },
  {
    id: 5,
    schedualId: 0,
    userId: 2,
    tickets: [
      [4, 6],
      [6, 4]
    ],
    placeTime: new Date(),
    status: 1
  },
  {
    id: 6,
    schedualId: 0,
    userId: 1,
    tickets: [
      [5, 8],
      [5, 7]
    ],
    placeTime: new Date(),
    status: 2
  },
  {
    id: 7,
    schedualId: 0,
    userId: 2,
    tickets: [
      [6, 7],
      [6, 8]
    ],
    placeTime: new Date(),
    status: 0
  }
];

export const Status = {
  unpaid: 0,
  unused: 1,
  finished: 3
};

export function getSoldSeats(scheduleId) {
  let ans = [];
  orders.forEach(item => {
    if (
      item.schedualId === scheduleId &&
      (item.status === 0 || item.status === 1)
    )
      ans.push(...item.tickets);
  });
  return ans;
}

export function getOrderById(id) {
  let order = orders.filter(item => item.id === id);
  return order[0];
}

export function addOrder(order) {
  coverUnfinishedOrder(order.userId);

  const id = orders[orders.length - 1].id + 1;
  order["id"] = id;
  orders.push(order);
  return id;
}

export function updateOrderStatus(orderId, status) {
  for (let i = 0; i < orders.length; i++) {
    const o = orders[i];
    if (o.id === orderId) o.status = status;
  }
}
export function deleteOrder(id) {
  const index = orders.findIndex(order => order.id === id);
  orders.splice(index, 1);
  console.log(orders)
}


export function coverUnfinishedOrder(userId) {
  for (let i = 0; i < orders.length; i++) {
    const o = orders[i];
    if (o.userId === userId && o.status === 0) {
      o.status = 2;
    }
  }
}