<template>
  <el-card 
    :body-style="{ padding: '0px' }"
    class="main"
    >
    <div class="poster">
      <img
        :src="movie.poster"
        class="image"
      />
    </div>
    <div class="movie-ver" v-if="movie.score === -1">
      <a>预售</a>
    </div>
    <div style="padding: 14px">
      <el-tooltip class="item" effect="dark" :content="movie.name" placement="top">
        <div class="name">{{ movie.name }}</div>
      </el-tooltip>
      <div class="num-container">
        <span class="like-num" v-if="movie.score === -1">{{ movie.like }}人想看</span>
        <span class="score" v-else>{{ movie.score }}</span>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "SimpleMovieCard",

  props: ["movie"],
};
</script>

<style scoped>
.main {
  position: relative;
  margin-bottom: 20px;
  /* height: 100px; */
}

.main .movie-ver {
  position: absolute;
  right: 5px;
  bottom: 80px;
  padding: 0 3px;
  height: 18px;
  line-height: 18px;
  border-radius: 1px;
  background-color: #12a2f1;
}

.main .movie-ver a{
  color: #fff;
  font-size: 12px;
  vertical-align: top;
}

.main .image {
  width: 100%
}

.main .name {
  text-align: center;
  font-size: 16px;
  color: #333;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  cursor: pointer;
}

.main .num-container {
  text-align: center;
  margin-top: 10px;
  font-style: italic;
  color: #ffb400;
}

.main .num-container .score {
  font-size: 22px;
}

.main .num-container .like-num {
  font-size: 16px;
}

</style>