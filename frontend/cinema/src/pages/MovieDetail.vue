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
        <el-col :span="16" id="movie-detail">
          <div class="title">{{ movie.name }}</div>
          <div class="detail">
            <div>导演：{{ movie.director.join("、") }}</div>
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
            <div class="movie-description">
              <div :class="descriptionClose ? 'close' : 'open'">
                简介：{{
                  isExpand
                    ? movie.description
                    : movie.description.substring(0, 100) + "……"
                }}
              </div>
              <span
                @click="
                  () => {
                    isExpand = !isExpand;
                  }
                "
                >展开>>></span
              >
            </div>
          </div>
          <div class="movie-statics">
            <div>
              <span class="number">{{ movie.like }}</span
              ><span class="tip">人想看</span>
            </div>
            <div>
              <span class="number">{{ movie.score }}</span
              ><span class="tip">分</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="schedual">
      <div class="schedual-title">选座购票</div>
      <el-divider></el-divider>
      <div class="schedual-date">
        <span class="schedual-date-title">观影日期：</span>
        <el-radio-group size="small" v-model="chosenDate" @change="dateChange">
          <el-radio-button
            v-for="schedual in schedualList"
            :key="schedual.date"
            :label="schedual.date"
          />
        </el-radio-group>
      </div>
      <div class="schedual-table">
        <el-table
          :data="chosenSchedual"
          stripe
          style="width: 100%; text-align: center"
          :header-cell-style="{ ...tableStyle, 'background-color': '#f7f7f7' }"
          :cell-style="tableStyle"
        >
          <el-table-column label="放映时间">
            <template slot-scope="scope">
              <div class="schedual-time">
                <div class="start-time">
                  {{
                    new Date(scope.row.startTime).toTimeString().substring(0, 5)
                  }}
                </div>
                <div class="end-time">
                  预计{{
                    new Date(scope.row.endTime).toTimeString().substring(0, 5)
                  }}散场
                </div>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="language" label="语言版本"> </el-table-column>
          <el-table-column prop="theater" label="放映厅"> </el-table-column>
          <el-table-column prop="prize" label="售价（元）"> </el-table-column>
          <el-table-column label="选座购票"
            ><template slot-scope="scope">
              <el-button
                el-button
                type="primary"
                round
                size="small"
                @click="buyTicket(scope.row.id)"
                >选座购票</el-button
              >
            </template></el-table-column
          >
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
// import { movies as movieList} from "@/lib/movieList";
import movies from "@/lib/movieList";
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
      chosenSchedual: [],
      tableStyle: {
        "text-align": "center",
        color: "#333",
        "font-size": "15px"
      },
      isExpand: false
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
            ? "今天"
            : key ===
              new Date(
                curDay.setTime(curDay.setDate(curDay.getDate() + 1))
              ).toLocaleDateString()
            ? "后天"
            : `周${weekMap[new Date(value[0].startTime).getDay()]}`;
        list.push({
          date: date + "  " + key.replaceAll("/", "-").substring(5),
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
    },
    buyTicket: function(id) {
      console.log("schedual id", id);
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
  margin-bottom: 20px;
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
  height: 350px;
  width: 100%;
  position: relative;
}
#movie-poster {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
#movie-detail {
  height: 100%;
  /* display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center; */
  color: #fff;
}

#movie-detail .title {
  font-size: 26px;
  margin-bottom: 20px;
}

#movie-detail .detail {
  font-size: 12px;
}

.movie-description .close {
  display: -webkit-inline-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
}

.movie-statics {
  display: flex;
  align-content: flex-end;
  height: 100%;
}
.movie-statics .number {
  color: #ffb400;
  font-size: 26px;
}
.movie-statics .tip {
  color: #fff;
  font-size: 12px;
}

.schedual {
  width: 100%;
}
.schedual-title {
  font-size: 26px;
  margin-top: 45px;
}

.schedual-date {
  margin-bottom: 20px;
}

.schedual-date-title {
  font-size: 15px;
  margin-right: 10px;
}

.schedual .el-divider {
  margin: 20px 0;
}

.schedual-table .schedual-time .start-time {
  font-size: 20px;
  font-weight: 800;
  margin-bottom: 5px;
}
.schedual-table .schedual-time .end-time {
  font-size: 12px;
  color: #999;
}
</style>
