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
              src="https://p0.meituan.net/movie/59b5174b5eb82aeed2cb738eb261b18d797572.jpg@464w_644h_1e_1c"
            ></el-image>
          </div>
          <div class="schedual-detail">
            <div class="movie-name">{{ orderAll.name }}</div>
            <div class="shcedual-hall">{{ orderAll.hall }}</div>
            <div class="movie-time">{{ orderAll.time }}} 分钟</div>
            <div class="schedual-time">{{ orderAll.startTime }}</div>
          </div>
        </el-col>
        <el-col :offset="1" :span="6" class="order-seats">
          <span>5排7座</span>
          <span>5排7座</span>
        </el-col>
        <el-col :offset="1" :span="4" class="order-prize">￥68</el-col>
        <el-col :offset="1" :span="4" class="order-status">待支付</el-col>
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
      orderAll: {}
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
  width: 90px;
  height: 120px;
  margin-right: 20px;
}

.schedual-detail .movie-name {
  font-size: 16px;
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
}

.order .order-seats span {
  display: inline-block;
  margin-right: 5px;
}
</style>
