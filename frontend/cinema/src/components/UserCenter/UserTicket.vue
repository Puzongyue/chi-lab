<template>
  <div class="user-order">
    <div class="order-list">
      <div
        class="order-item"
        v-for="(order, index) in orderList"
        :key="index"
      >
      <TicketOrderCard :order="order" />
      </div>
    </div>
  </div>
</template>

<script>
import TicketOrderCard from "../TicketOrderCard";
import { orders, Status } from "@/lib/orderList.js";
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
    this.getOrderList();
  },
  methods: {
    getOrderList() {
      const status = Status[this.$route.query.status];
      const orderList = orders.filter(order => {
        return order.userId === this.userId && order.status === status;
      });
      this.orderList = orderList;
    }
  }
};
</script>

<style scoped>
.user-order {
}
</style>
