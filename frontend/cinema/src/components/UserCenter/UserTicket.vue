<template>
  <div class="user-ticket">
      <TicketOrderCard />
  </div>
</template>

<script>
import TicketOrderCard from "../TicketOrderCard";
export default {
  name: "UserTicket",
  components: {
    TicketOrderCard
  },
  data() {
    return {};
  }
};
</script>

<style scoped>
.user-ticket {
}
</style>
