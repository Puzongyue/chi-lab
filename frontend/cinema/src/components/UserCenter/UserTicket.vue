<template>
  <div class="user-order">
    <div class="order-list">
      <div class="order-item" v-for="order in orderList" :key="order.id">
        <TicketOrderCard :order="order" @deleteOrder="deleteOrder" />
      </div>
    </div>
  </div>
</template>

<script>
import TicketOrderCard from "../TicketOrderCard";
import { orders, Status,deleteOrder } from "@/lib/orderList.js";
export default {
  name: "UserTicket",
  components: {
    TicketOrderCard
  },
  data() {
    return {
      userId: 0,
      orderList: []
    };
  },
  mounted() {
    this.orderList = this.getOrderList(Status[this.$route.query.status]);
  },
  beforeRouteUpdate(to, from, next) {
    if (to.query.status !== from.query.status) {
      this.orderList = this.getOrderList(Status[to.query.status]);
    }
    next();
  },
  methods: {
    getOrderList(status) {
      const orderList = [];
      if (status === 0) {
        orders.forEach(order => {
          if (order.userId === this.userId && order.status === status) {
            if (new Date() - order.placeTime < 1000 * 60 * 15) {
              orderList.push(order);
            } else {
              order.status = 2;
            }
          }
        });
      } else if (status !== 2) {
        orders.forEach(order => {
          if (order.userId === this.userId && order.status === status) {
            orderList.push(order);
          }
        });
      }
      return orderList;
    },
    deleteOrder(id){
      deleteOrder(id);
      this.orderList = this.getOrderList(Status[this.$route.query.status]);
    }
  }
};
</script>

<style scoped>
.user-order {
}
</style>
