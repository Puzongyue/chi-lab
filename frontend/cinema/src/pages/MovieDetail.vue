<template>
  <div>
    <div class="detail-wrap">
      <div
        class="detail-bg"
        data-bg="https://p1.meituan.net/movie/13cb85d810b0530b951625430c2f35044951112.jpg@464w_644h_1e_1c"
        style="opacity: 0.4;"
      >
        <img
          src="https://p1.meituan.net/movie/13cb85d810b0530b951625430c2f35044951112.jpg@464w_644h_1e_1c"
        />
      </div>
      <el-row class="movie-content">
        <el-col :span="8" id="movie-poster">
          <el-image
            style="width: 230px; height: 300px"
            :src="movie.poster"
            fit="contain"
          ></el-image>
        </el-col>
        <el-col :span="16" id="moive-detail">
          <div class="movie-title">{{ movie.name }}</div>
          <div class="detail">
            <div>导演：{{ movie.director }}</div>
            <div>
              主演：{{
                movie.stars
                  .map(star => star.star + "（ 饰" + star.role + " ）")
                  .join("、")
              }}
            </div>
            <div>类型：{{ movie.types.join(" / ") }}</div>
            <div>制片国家/地区：{{ movie.location }}</div>
            <div>
              上映时间：{{
                movie.startDay.toLocaleDateString().replaceAll("/", "-")
              }}
            </div>
            <div>时长：{{ movie.time }}分钟</div>
            <div>简介：{{ movie.description }}</div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="schedual">
      <div class="schedual-title">选座购票</div>
      <el-divider></el-divider>
      <div class="schedual-time">
        <span class="schedual-time-title">观影日期</span>
        <el-radio-group v-model="chosenDate" @change="dateChange">
          <el-radio-button
            v-for="schedual in schedualList"
            :key="schedual.date"
            :label="schedual.date"
          />
        </el-radio-group>
      </div>

      <div class="schedual-table">
        <el-table :data="chosenSchedual" border style="width: 90%">
          <el-table-column label="放映时间" class-name="time">
            <template slot-scope="scope">
              <span>{{
                new Date(scope.row.startTime).toTimeString().substring(0,5)
              }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="language" label="语言版本"> </el-table-column>
          <el-table-column prop="theater" label="放映厅"> </el-table-column>
          <el-table-column prop="prize" label="现价/影院价（元）">
          </el-table-column>
          <el-table-column label="选座购票"> </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import movieList from "@/lib/movieList";
import schedualList from "@/lib/schedualList";
export default {
  name: "MovieDetail",
  data() {
    const movie = movieList[0];
    const detailStyle = "background-image: url('" + movie.poster + "'); ";
    return {
      movieList,
      movie,
      detailStyle,
      originScheList: schedualList,
      chosenDate: "",
      chosenSchedual: []
    };
  },
  mounted: function() {
    this.schedualList = this.getScheList();
    this.chosenDate = this.schedualList[0].date;
    this.chosenSchedual = this.schedualList[0].schedual;
  },
  methods: {
    getScheList: function() {
      const curDate = new Date();
      const resMap = new Map();

      this.originScheList.forEach(schedual => {
        const scheDate = new Date(schedual.startTime).toLocaleDateString();
        if (schedual.startTime > curDate) {
          const sche = {
            ...schedual,
            time: {
              start: schedual.startTime,
              end: schedual.endTime
            }
          };
          if (resMap.get(scheDate) !== undefined) {
            resMap.get(scheDate).push(sche);
          } else {
            resMap.set(scheDate, [sche]);
          }
        }
      });

      const list = [];
      const weekMap = ["日", "一", "二", "三", "四", "五", "六"];
      resMap.forEach((value, key) => {
        const curDay = new Date();
        const date =
          key === curDay.toLocaleDateString()
            ? "（今天）"
            : key ===
              new Date(
                curDay.setTime(curDay.setDate(curDay.getDate() + 2))
              ).toLocaleDateString()
            ? "（后天）"
            : `（周${weekMap[new Date(value[0].startTime).getDay()]}）`;
        list.push({
          date: key.replaceAll("/", "-") + date,
          schedual: value
        });
      });

      list.sort((a, b) => {
        return a.startTime - b.startTime;
      });

      if (!list[0].date.includes("今")) {
        list.splice(0, 1, {
          date: curDate.toLocaleDateString().replaceAll("/", "-"),
          schedual: []
        });
      }
      this.chosenDate = schedualList[0].date;
      console.log(list);
      return list;
    },
    dateChange: function(curChosen) {
      this.chosenSchedual = this.schedualList.find(
        v => v.date === this.chosenDate
      ).schedual;
    }
  }
};
</script>

<style scoped>
.detail-wrap {
  width: 100%;
  height: auto;
  position: relative;
  background: #42383d;
  z-index: 1;
  overflow: hidden;
  padding-bottom: 20px;
}

.detail-wrap .detail-bg {
  width: 100%;
  height: 100%;
  position: absolute;
  overflow: hidden;
  top: 0;
  left: 0;
  opacity: 0;
  transition: opacity 2s ease;
  z-index: -1;
}
.detail-wrap .detail-bg img {
  display: block;
  float: left;
  position: relative;
  left: -10%;
  top: 0;
  height: auto;
  width: 120%;
  filter: blur(5px);
}

.movie-content {
  height: 400px;
  width: 100%;
  position: relative;
}
#movie-poster {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
#moive-detail {
  height: 100%;
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
  color: #fff;
}
.movie-title {
  font-size: 26px;
  margin-bottom: 20px;
}
.detail {
  font-size: 12px;
}
.schedual {
  width: 100%;
}

.schedual-title {
  font-size: 26px;
  margin-top: 45px;
  /* margin-bottom: 20px; */
}

.schedual-time {
  margin-bottom: 20px;
}

.schedual-time-title {
  font-size: 15px;
  margin-right: 30px;
}

.schedual .el-divider {
  margin: 20px 0;
}

.schedual .schedual-table {
  display: flex;
  justify-content: center;
}

.schedual-table .time .cell{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 18px;
}
</style>
