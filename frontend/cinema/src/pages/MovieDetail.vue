<template>
  <div>
    <div class="detail-wrap">
      <div class="detail-bg" :data-bg="movie.poster" style="opacity: 0.4;">
        <img :src="movie.poster" />
      </div>
      <el-row class="movie-content" v-if="isDisplay">
        <el-col :span="6" id="movie-poster">
          <el-image
            style="width: 230px; height: 300px"
            :src="movie.poster"
            fit="contain"
          ></el-image>
        </el-col>
        <el-col :span="18" id="movie-detail">
          <el-row style="height: 100%">
            <el-col :span="18" class="movie-info">
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
                  <div :class="isExpand ? 'close' : 'open'">
                    简介：{{
                      isExpand
                        ? movie.description
                        : movie.description.substring(0, 100) + "……"
                    }}
                  </div>
                  <span
                    class="description-expand"
                    @click="
                      () => {
                        isExpand = !isExpand;
                      }
                    "
                    >{{ isExpand ? "收起" : "展开" }}>>></span
                  >
                </div>
              </div>
            </el-col>

            <el-col
              :offset="1"
              :span="5"
              style="height: 100%; position: relative"
            >
              <div
                @click="
                  () => {
                    isDisplay = !isDisplay;
                  }
                "
              >
                切换电影
              </div>
              <div class="movie-statics">
                <div class="static-info">
                  <span class="number">{{ movie.like }}</span>
                  <span class="tip">人想看</span>
                  <el-button
                    circle
                    plain
                    size="mini"
                    @click="
                      () => {
                        movie.like = movie.like + (isLike ? -1 : +1);
                        isLike = !isLike;
                      }
                    "
                    ><icon-base
                      icon-name="like"
                      width="18"
                      height="18"
                      :iconColor="isLike ? '#f82f15' : '#bfbfbf'"
                      ><icon-heart /></icon-base
                  ></el-button>
                </div>
                <div class="static-info">
                  <span class="number">{{ movie.score }}</span>
                  <span class="tip">分</span>
                  <el-rate v-model="value2" :colors="colors"> </el-rate>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
      <div v-else class="choose">
        <div class="choose-wrap">
          <div class="choose-content">
            <ul class="movie-list">
              <li v-for="movie in movieList" :key="movie.id">
                <el-image
                  style="width: 180px; height: 240px"
                  :src="movie.poster"
                  fit="fill"
                ></el-image>
              </li>
            </ul>
          </div>
        </div>
      </div>
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
import { movies as movieList } from "@/lib/movieList";
import schedualList from "@/lib/schedualList";

import IconBase from "@/components/Icon/IconBase.vue";
import IconHeart from "@/components/Icon/IconHeart.vue";
import SimpleMovieCard from "@/components/SimpleMovieCard.vue";

export default {
  components: { IconBase, IconHeart, SimpleMovieCard },
  name: "MovieDetail",
  data() {
    const movie = movieList[5];
    const detailStyle = "background-image: url('" + movie.poster + "'); ";
    return {
      movieList,
      movie,
      detailStyle,
      originScheList: schedualList,
      schedualList: [],
      chosenDate: "",
      chosenSchedual: [],
      tableStyle: {
        "text-align": "center",
        color: "#333",
        "font-size": "15px"
      },
      isExpand: false,
      isLike: false,
      isDisplay: true,
      value2: 4,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"] // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
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
            ? "明天"
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
  /* margin: 0 -108px; */
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
  height: 400px;
  width: 100%;
  position: relative;
  display: flex;
  align-content: center;
}
#movie-poster {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
#movie-detail {
  height: 100%;
  padding-top: 50px;
  /* padding-right: 50px; */
  color: #fff;
}

#movie-detail .movie-info {
  height: 100%;
  overflow: auto;
}

#movie-detail .movie-info .title {
  font-size: 26px;
  margin-bottom: 20px;
}

#movie-detail .movie-info .detail {
  font-size: 12px;
  line-height: 23px;
}

.movie-description .description-expand {
  cursor: pointer;
  color: #eb002a;
}
.movie-description .description-expand:hover {
  cursor: pointer;
  color: #eb002a;
}

.movie-statics {
  position: absolute;
  bottom: 50px;
}
.movie-statics .static-info {
  margin-bottom: 10px;
}

.movie-statics .static-info .number {
  color: #ffb400;
  font-size: 30px;
}
.movie-statics .static-info .tip {
  color: #fff;
  font-size: 12px;
}

.choose {
  height: 400px;
  position: relative;
}

.choose .choose-wrap {
  width: 864px;
  min-width: 864px;
  margin: 0 auto;
  position: relative;
  top: 80px;
}

.choose .choose-wrap .choose-content {
  width: 100%;
  overflow: hidden;
  font-size: 0;
}

.movie-list {
  display: block;
  width: auto;
  height: 240px;
  overflow: hidden;
  white-space: nowrap;
  margin-left: -48px;
}
.movie-list li {
  display: inline-block;
  zoom: 1;
  margin-right: 48px;
  width: 180px;
  height: 240px;
  overflow: hidden;
  position: relative;
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
