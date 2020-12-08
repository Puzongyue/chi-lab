<template>
  <el-card class="el-card" shadow="hover">
    <el-row class="card">
      <!-- <div class="card"> -->
      <el-col :span="9" :offset="3">
        <div class="movie-poster" @click="jumpToMovieDetail">
          <img :src="this.movie.poster" />
        </div>
      </el-col>
      <el-col :span="10" :offset="2">
        <div class="movie-name" @click="jumpToMovieDetail">
          <a>{{ this.movie.name }}</a>
        </div>
        <div class="movie-info">
          <div class="movie-like" v-if="this.movie.score === -1">
            <span class="num">{{ this.movie.like }}</span>
            <span class="text"> 人想看</span>
          </div>

          <div class="movie-score" v-else>
            <span class="num">{{ this.movie.score }}</span>
          </div>

          <div class="movie-type">{{ this.movie.types.join(" / ") }}</div>
          <div class="movie-start-date">
            {{ this.movie.startDay.toISOString().substring(0, 10) }}上映
          </div>
        </div>
      </el-col>
      <!-- </div> -->
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: "SearchResultCard",

  props: ["movie"],

  methods: {
    jumpToMovieDetail() {
      console.log(this.movie.id);
    },
  },
};
</script>

<style scoped>
.el-card {
  margin-bottom: 8px;
}
.card {
  position: relative;
}

.card .movie-poster {
  display: inline-block;
  position: relative;
  margin: -1px;
  /* float: left; */
  width: 180px;
  height: 240px;
  cursor: pointer;
  margin-right: 20px;
}

.card .movie-poster img {
  width: 100%;
  height: 100%;
}

.card .movie-name {
  color: #333;
  text-align: left;
  font-size: 26px;
  width: auto;
  margin-top: 27px;
  padding-bottom: 1px;
  margin-bottom: -1px;
  vertical-align: top;

  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: 1.2;
}

.card .movie-info {
  line-height: 1.2;
  margin-top: 20px;
}


.card .movie-info .movie-like,
.card .movie-info .movie-score {
  height: 31px;
  line-height: 31px;
  margin-bottom: 13px;
}

.card .movie-info .movie-like .num,
.card .movie-info .movie-score .num {
  font-size: 26px;
  font-weight: 700;
  color: #ffb400;
}

.card .movie-info .movie-like .text {
  color: #ffb400;
}

.card .movie-info .movie-type {
  margin-top: 5px;
  font-size: 16px;
  color: #333;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  padding-bottom: 1px;
  margin-bottom: -1px;
  line-height: 1.2;
}

.card .movie-info .movie-start-date {
  color: #999;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  padding-bottom: 1px;
  margin-bottom: -1px;
  margin-top: 5px;
  font-size: 16px;
}
</style>