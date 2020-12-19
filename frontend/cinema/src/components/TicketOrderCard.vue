<template>
  <div class="ticket-card" shadow="hover">
    <div class="card-header">
      <span>2020-12-13</span>
      <i class="el-icon-delete"></i>
    </div>
    <div class="card-body">
      <el-row type="flex" align="middle" class="order">
        <el-col :span="6" class="order-detail">
          <div class="movie-poster">
            <el-image
              style="width: 100%; height: 100%"
              fit="contain"
              :src="orderAll.poster"
            ></el-image>
          </div>
          <div class="schedual-detail">
            <div class="movie-name">{{ orderAll.name }}</div>
            <div class="shcedual-hall">{{ orderAll.hall }}</div>
            <div class="movie-time">{{ orderAll.time }} 分钟</div>
            <div class="schedual-time">{{ orderAll.startTime }}</div>
            <el-button
              class="continue-pay"
              type="primary"
              v-if="orderAll.status === 0"
              size="mini"
              round
              >继续支付</el-button
            >
          </div>
        </el-col>
        <el-col :offset="1" :span="6" class="order-seats">
          <span v-for="(ticket, index) in orderAll.tickets" :key="index"
            >{{ ticket[0] + 1 }}排{{ ticket[1] + 1 }}列</span
          >
        </el-col>
        <el-col :offset="2" :span="4" class="order-prize"
          >￥{{ orderAll.prize * orderAll.tickets.length.toFixed(2) }}</el-col
        >
        <el-col :offset="2" :span="4" class="order-status">
          <div
            :style="{ color: statusList[orderAll.status].color }"
            class="status-content"
          >
            {{ statusList[orderAll.status].content }}
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import schedualList from "@/lib/schedualList";
import hallList from "@/lib/hallList";
import { movies } from "@/lib/movieList";

export default {
  name: "TicketOrderCard",
  data() {
    return {
      orderAll: {},
      statusList: [
        { content: "待支付", color: "#FF4500" },
        { content: "待使用", color: "#67C23A" },
        {},
        { content: "已完成", color: "#606266" }
      ]
    };
  },
  props: {
    order: Object
  },
  created() {
    const weekMap = ["日", "一", "二", "三", "四", "五", "六"];
    const schedual = schedualList[this.order.schedualId];
    const time = new Date(schedual.startTime);
    const movie = movies[schedual.movieId];

    this.orderAll = {
      ...this.order,
      name: movie.name,
      poster: movie.poster,
      startTime:
        time
          .toLocaleDateString()
          .substring(5)
          .replace("/", ".") +
        " " +
        "周" +
        weekMap[time.getDay()] +
        " " +
        time.toTimeString().substring(0, 5),
      time: movie.time,
      hall: hallList[schedual.hallId].name,
      prize: schedual.prize
    };
  },
  methods: {}
};
</script>

<style scoped>
.ticket-card .card-header {
  background-color: #f7f7f7;
  font-size: 14px;
  padding: 16px 20px;
  display: flex;
  justify-content: space-between;
}
.ticket-card .card-body {
  /* height: 100px; */
  padding: 20px 40px;
}
.order .order-seats,
.order .order-prize,
.order .order-status {
  font-size: 14px;
  color: #333;
  line-height: 20px;
}

.order .order-detail {
  display: flex;
}
.order .order-detail .movie-poster {
  border: 2px solid #fff;
  box-shadow: 0 1px 2px 0 hsla(0, 0%, 53%, 0.5);
  font-size: 0;
  width: 105px;
  height: 140px;
  margin-right: 20px;
}

.schedual-detail {
  position: relative;
}

.schedual-detail .movie-name {
  font-size: 18px;
  font-weight: 700;
  color: #333;
  margin: 4px 0 10px -6px;
}
.schedual-detail .shcedual-hall,
.schedual-detail .movie-time {
  font-size: 12px;
  color: #999;
  margin-bottom: 4px;
}
.schedual-detail .schedual-time {
  font-size: 12px;
  color: #ff4500;
  margin-bottom: 10px;
}
.schedual-detail .continue-pay {
  position: absolute;
  left: -4px;
  bottom: 1px;
}

.order .order-seats span {
  display: inline-block;
  width: 25%;
  margin-right: 5px;
}
</style>
