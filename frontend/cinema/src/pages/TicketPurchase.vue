<template>
  <div class="main">
    <div class="step-bar">
      <el-steps :active="activeStep" finish-status="success" align-center>
        <el-step title="选择座位"></el-step>
        <el-step title="15分钟内付款"></el-step>
        <el-step title="付款成功"></el-step>
      </el-steps>
    </div>

  <router-view>
    <!-- <seat-selection v-if="activeStep === 0" :scheduleId="scheduleId" @confirm="confirmSeats"></seat-selection>
    <payment v-else-if="activeStep === 1" :orderId="orderId" @hasPaid="paySuccessfully"></payment>
    <payment-success v-else-if="activeStep === 2"></payment-success> -->
  </router-view>
  </div>
</template>

<script>
import SeatSelection from "@/components/SeatSelection.vue";
import Payment from "@/components/Payment.vue";
import PaymentSuccess from "@/components/PaymentSuccess.vue";

export default {
  name: "TicketPurchase",

  components: { SeatSelection, Payment, PaymentSuccess },

  data() {
    return {
      activeStep: 0,
      scheduleId: 0,
      soldSeats: [],
      orderId: 0,
      userId: 0
    };
  },
  
  mounted() {
    this.scheduleId = parseInt(this.$route.query.id);
    this.userId = parseInt(this.$route.userId);
    this.activeStep = this.$route.isContinued === undefined? 0 : 1;
  },

  updated() {
    const stepName = ["SeatSelection", "Payment", "PaymentSuccess"];
    const name = this.$route.name;
    this.activeStep = stepName.indexOf(name);
  }
};
</script>

<style scoped>
.main .step-bar {
  margin: 30px auto;
  width: 100%;
}
</style>