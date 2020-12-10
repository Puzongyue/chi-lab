<template>
  <div class="main">
    <div class="step-bar">
      <el-steps :active="activeStep" 
                finish-status="success"
                align-center
      >
        <el-step title="选择座位"></el-step>
        <el-step title="15分钟内付款"></el-step>
        <el-step title="付款成功"></el-step>
      </el-steps>
    </div>

    <seat-selection :row="9" :column="18" :soldSeats="soldSeats"></seat-selection>
  </div>
</template>

<script>
import SeatSelection from "@/components/SeatSelection.vue";

export default {
  name: "TicketPurchase",

  components: { SeatSelection },

  data() {
    return {
      activeStep: 0,
      scheduleId: 0,
      soldSeats: [[1, 1], [8, 7]]
    }
  }
  
}
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