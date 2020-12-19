<template>
  <div class="main">
    <div class="step-bar">
      <el-steps :active="activeStep" finish-status="success" align-center>
        <el-step title="选择座位"></el-step>
        <el-step title="15分钟内付款"></el-step>
        <el-step title="付款成功"></el-step>
      </el-steps>
    </div>

    <seat-selection v-if="activeStep === 0" :scheduleId="scheduleId" @confirm="confirmSeats"></seat-selection>
    <payment v-else-if="activeStep === 1" :orderId="orderId"></payment>
  </div>
</template>

<script>
import SeatSelection from "@/components/SeatSelection.vue";
import Payment from "@/components/Payment.vue";
import { addOrder } from "@/lib/orderList";

export default {
  name: "TicketPurchase",

  components: { SeatSelection, Payment },

  data() {
    return {
      activeStep: 0,
      scheduleId: 0,
      soldSeats: [],
      orderId: 0
    };
  },

  mounted() {
    this.scheduleId = parseInt(this.$route.query.id);
  },

  methods: {
    confirmSeats(seats) {
      this.activeStep = 1;
      let order = {};
      order["schedualId"] = this.scheduleId;
      order["userId"] = 0;
      order["tickets"] = seats;
      order["placeTime"] = new Date();
      order["status"] = 0;

      this.orderId = addOrder(order);
    },
  }
};
</script>

<style scoped>
.main .step-bar {
  margin: 30px auto;
  width: 100%;
}
</style>