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

export default {
  name: "TicketPurchase",

  components: { SeatSelection, Payment},

  data() {
    return {
      activeStep: 0,
      scheduleId: 0,
      soldSeats: [],
      orderId: 0
    };
  },

  mounted() {
    this.scheduleId = this.$route.query.id;
    
  },

  methods: {
    // TODO: 生成订单并将订单id传给payment
    confirmSeats(seats) {
      this.activeStep = 1;
      console.log("seats: ", seats);
    },
  }
};
</script>

<style scoped>
/* .main {
  position: relative;
} */

/* .main .step-bar {
  position: absolute;
  width: 100%;
  left: 50%;
  transform: translateX(-50%);
} */

.main .step-bar {
  margin: 30px auto;
  width: 100%;
}
</style>