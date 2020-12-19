<template>
  <div class="main">
    <el-card>
      <el-row>
        <el-col :offset="3" :span="13" class="seat">
          <div class="seat-tip">
            <div class="seat-tip-item">
              <icon-base icon-name="empty-seat" width="28" height="28">
                <icon-empty-seat />
              </icon-base>
              <span>可选座位</span>
            </div>
            <div class="seat-tip-item">
              <icon-base icon-name="sold-seat" width="28" height="28">
                <icon-sold-seat />
              </icon-base>
              <span>已售座位</span>
            </div>
            <div class="seat-tip-item">
              <icon-base icon-name="chosen-seat" width="28" height="28">
                <icon-chosen-seat />
              </icon-base>
              <span>已选座位</span>
            </div>
          </div>
          <div class="screen-center">银幕中央</div>
          <div class="seats">
              <div class="row" v-for="r in hall.row" :key="r">
                <span class="row-id">{{ r }}</span>
                <div class="seats-container" :style="dynamicPadding">
                  <span class="single-seat" :style="dynamicWidth" v-for="c in hall.column" :key="c" @click="chooseSeat(r, c)">
                  <icon-base width="28" height="28">
                    <icon-sold-seat v-if="isInSoldSeats(r, c)" />
                    <icon-chosen-seat v-else-if="chosenSeats[r - 1][c - 1] === 1"/>
                    <icon-empty-seat v-else />
                  </icon-base>
                  </span>
                </div>
              </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="movie-info">
            <div class="movie">
              <div class="poster">
                <img :src="movie.poster" alt="">
              </div>
              <div class="title-and-description">
                <p class="title">{{ movie.name }}</p>
                <div class="info-item">
                  <span>类型：</span>
                  <span class="value">{{ movie.types.join(" / ") }}</span>
                </div>
                <div class="info-item">
                  <span>时长：</span>
                  <span class="value">{{ movie.time }}分钟</span>
                </div>
              </div>
            </div>
            <div class="location">
              <div class="info-item">
                <span>影院：</span>
                <span class="value">NJU-SE</span>
              </div>
              <div class="info-item">
                <span>影厅：</span>
                <span class="value">{{ hall.name }}</span>
              </div>
              <div class="info-item">
                <span>版本：</span>
                <span class="value">{{ schedule.language }}</span>
              </div>
              <div class="info-item">
                <span>场次：</span>
                <span class="value time">{{ formattedDate }}</span>
              </div>
              <div class="info-item">
                <span>票价：</span>
                <span class="value">￥{{ schedule.prize }} / 张</span>
              </div>
            </div>
            <div class="ticket-info">
              <div class="no-ticket" v-if="chosenSeatsCompressed.length === 0">
                <p>座位：一次最多选4个座位</p>
                <p>请<span>点击左侧</span>座位图选择座位</p>
              </div>
              <div class="has-ticket" v-else>
                <span class="text">座位：</span>
                <div class="chosen-seats">
                  <span class="ticket" v-for="(seat, index) in chosenSeatsCompressed" :key="index">{{ seat[0] }}排{{ seat[1] }}座</span>
                </div>
              </div>
              <div class="total-price">
                <span>总价：
                  <span class="price">￥{{ totalPrice }}</span>
                </span>
              </div>
            </div>
            <div class="confirm">
            <el-button type="primary" @click="confirm" :disabled="chosenSeatsCompressed.length === 0">确认选座</el-button>
            </div>
          </div>
          
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import IconBase from "@/components/Icon/IconBase.vue";
import IconEmptySeat from "@/components/Icon/IconEmptySeat.vue";
import IconSoldSeat from "@/components/Icon/IconSoldSeat.vue";
import IconChosenSeat from "@/components/Icon/IconChosenSeat.vue";
import { getScheduleById } from "../lib/schedualList";
import { getMovieById } from "../lib/movieList";
import { getSoldSeats } from "../lib/orderList";
import { getHallById } from "../lib/hallList";
import { addOrder } from "@/lib/orderList";

export default {
  name: "SeatSelection",

  components: { IconBase, IconEmptySeat, IconSoldSeat, IconChosenSeat },
  
  computed: {
    dynamicWidth: function() {
      const style = {};
      style.width = 500 / this.hall.column + "px";
      return style;
    },

    dynamicPadding: function() {
      const style = {};
      switch(this.hall.column) {
        case 18:
          style["padding-left"] = 50 + "px";
          break;
        case 8:
          style["padding-left"] = 60 + "px";
          break;
        case 12:
          style["padding-left"] = 55 + "px";
          break;
        default:
          break;
      }

      return style;
    },

    formattedDate: function() {
      let t = new Date(this.schedule.startTime).toTimeString().split(":"),
          d = new Date(this.schedule.startTime).toLocaleDateString().split("/");

      t.length = 2;
      t = t.join(":");
      d = d[0] + "年" + d[1] + "月" + d[2] + "日";

      return d + " " + t;
    }
  },

  data() {
    return {
      scheduleId: 0,
      userId: 0,
      chosenSeats: [[]],
      chosenSeatsCompressed: [],
      hall: {},
      schedule: {},
      soldSeats: [],
      movie: {},
      totalPrice: 0,
      orderId: 0
    };
  },

  created() {
    this.scheduleId = parseInt(this.$route.query.id);
    this.userId = parseInt(this.$route.query.userId);
    this.schedule = getScheduleById(this.scheduleId);
    this.movie = getMovieById(this.schedule.movieId);
    this.soldSeats = getSoldSeats(this.scheduleId);
    this.hall = getHallById(this.schedule.hallId);

    this.chosenSeats = new Array(this.hall.row);

    for (let i = 0; i < this.hall.row; i++) this.chosenSeats[i] = new Array(this.hall.column).fill(0);

    // console.log(this.schedule, this.movie, this.soldSeats, this.hall);
  },

  methods: {
    isInSoldSeats(r, c) {
      return this.soldSeats.some(item => item[0] === r - 1 && item[1] === c - 1);
    },

    chooseSeat(r, c) {
      let tmp = this.chosenSeats[r - 1];
      if (tmp[c - 1] === 1) {
        this.cancelSeat(r, c);
        tmp[c - 1] = 0;
      }
      else {
        if(this.chosenSeatsCompressed.length >= 4) {
          this.openMsgBox();
          return;
        }

        tmp[c - 1] = 1;
        this.chosenSeatsCompressed.push([r, c]);
        this.totalPrice += this.schedule.prize;
      }

      this.$set(this.chosenSeats, r - 1, tmp);
    },

    cancelSeat(r, c) {
      this.chosenSeatsCompressed = this.chosenSeatsCompressed.filter(item => item[0] !== r || item[1] !== c);
      this.totalPrice -= this.schedule.prize;
    },

    openMsgBox() {
      this.$alert('一次最多购买4张票', '提醒', {
        confirmButtonText: '我知道了'
      });
    },

    confirm() {
      let order = {};
      order["schedualId"] = this.scheduleId;
      order["userId"] = this.userId;
      order["tickets"] = this.chosenSeatsCompressed;
      order["placeTime"] = new Date();
      order["status"] = 0;

      this.orderId = addOrder(order);
      this.$router.push({ path:"/purchase/payment", query: { id: this.orderId }});

    }
  }
};
</script>

<style scoped>
.main .seat .screen-center{
  width: 650px;
  padding-top: 50px;
  text-align: center;
  font-size: 16px;
  color: #666;
  background: url(data:image/png;base64,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) no-repeat;
  background-position-x: center;
}

.main .seat .seat-tip {
  margin-top: 20px;
  display: flex;
  justify-content: space-around;
  width: 500px;
  padding-left: 45px;
}
/* .main .seat .seat-tip .seat-tip-item {
  margin-right: 15px;
} */

.main .seat .seat-tip .seat-tip-item span {
  display: inline-block;
  vertical-align: top;
  margin-top: 3px;
}

.main .seats .row {
  width: 700px;
}

.main .seats .row .row-id {
  display: inline-block;
  margin-right: 15px;
  vertical-align: top;
  margin-top: 5px;
  font-size: 16px;
  color: #999;
  text-align: center;
}

.main .seats .row .seats-container {
  display: inline-block;
  /* padding-left: 55px; */
  width: 600px;
}

.main .seats .single-seat {
  display: inline-block;
}

.main .movie-info {
  background-color: #f9f9f9;
  padding: 20px;
}

.main .movie-info .movie .poster {
  display: inline-block;
  width: 115px;
  height: 158px;
  border: 2px solid #fff;
  box-shadow: 0 2px 7px 0 hsla(0,0%,53%,.5);
}

.main .movie-info .movie .poster img {
  width: 100%;
  height: 100%;
}

.main .movie-info .movie .title-and-description {
  display: inline-block;
  vertical-align: top;
  margin-left: 30px;
}

.main .movie-info .movie .title-and-description .title {
  font-size: 20px;
  font-weight: 700;
  color: #333;
  margin: 0 0 14px;
}

.main .movie-info .info-item {
  font-size: 12px;
  color: #999;
  margin-bottom: 4px;

}

.main .movie-info .info-item .value {
  color: #151515;
  margin-left: 2px;
}

.main .movie-info .location {
  margin-top: 20px;
}

.main .movie-info .location .info-item {
  margin-bottom: 9px;
}

.main .movie-info .location .info-item .time {
  color: #FF7B00;
}

.main .movie-info .location .info-item > span {
  display: inline-block;
  /* vertical-align: top; */
  font-size: 14px;
}

.main .movie-info .ticket-info {
  padding: 20px 0 10px;
  border-top: 1px dashed #e5e5e5;
  border-bottom: 1px dashed #e5e5e5;
}

.main .movie-info .ticket-info .no-ticket p:first-child {
  font-size: 14px;
  color: #999;
  margin: 0;
}

.main .movie-info .ticket-info .no-ticket p:last-child {
  font-size: 14px;
  color: #333;
  text-align: center;
  margin: 28px 0 39px;
}

.main .movie-info .ticket-info .no-ticket p:last-child span {
  color: #FF7B00;
}

.main .movie-info .ticket-info .has-ticket .text {
  font-size: 14px;
  color: #999;
}

.main .movie-info .ticket-info .has-ticket .chosen-seats {
  display: inline-block;
  margin-bottom: 20px;
}

.main .movie-info .ticket-info .has-ticket .ticket {
  cursor: default;
  position: relative;
  font-size: 12px;
  color: #FF7B00;
  display: inline-block;
  width: 60px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  margin: 0 12px 10px 0;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAAeCAYAAABwmH1PAAAAAXNSR0IArs4c6QAAAXlJREFUWAlj/Oho7cXAzLTg/89fogzDGDCys71m+PsvgeGji+2r38eP/h/u4Pexo/9BfmUCxSyLhdUwjluI11gsrRhAfmUa9j5F8+Coh9ECZNhxR2N42EUpmodGZgx/TYz9+nvzhn9ogTEsuUysDGxS/548jP4+qe/0l6zUL//fvh2WHoV5ihHGALa0GD872rQxamrm8EybzQMTH070RzsLBngeZmRk/M+7/0jVv9s3rw7n5A33MCgmQZ4GRnn7r3Vrvw+nmEX2C4qHQRLMPxlO/X38kA1Z0XBiY3h4OHkOm18wPPyXncGMWVb+FzbFw0EMxcOgkvo/A0MlW1Aw53DwHDY/wD0Mq5aYVNW1WX0D4OLYNA1lMZavtraSoGT8ydWukklTU5u7uWNY1sGwSGL5zfDrGYuM6ldQMh7OMQv3MIjBPX8xN0xguNPDNq/iirhRD+MKmeEiPhrDwyUmcflj5MUwaM7lz/FjuAJk2IiD/AjyK+NIm0wDAACxUs8MaULTAAAAAElFTkSuQmCC) no-repeat;
}


.main .movie-info .ticket-info .total-price {
  font-size: 14px;
  color: #333;
}

.main .movie-info .ticket-info .total-price .price {
  color: #FF7B00;
  font-size: 24px;
}
.main .movie-info .confirm {
  padding: 20px 0;
  text-align: center;
}
</style>