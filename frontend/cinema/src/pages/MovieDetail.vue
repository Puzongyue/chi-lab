<template>
  <div class="movie-detail">
    <el-row class="movie">
      <el-col :span="8" id="movie-poster">
        <el-image
          style="width: 250px; height: 360px"
          :src="movie.poster"
          fit="contain"
        ></el-image>
      </el-col>
      <el-col :span="16">
        <div id="moive-detail">
          <div id="movie-title">{{ movie.name }}</div>
          <div class="detail">
              <div>导演：{{movie.director}}</div>
              <!-- <div>主演：<span class="actor" v-for=""></div> -->
          </div>
        </div>
      </el-col>
    </el-row>
    <div class="schedual"></div>
  </div>
</template>

<script>
import movieList from "@/lib/movieList";
export default {
  name: "MovieDetail",
  data() {
    return {
      movieList: movieList,
      movie: movieList[0]
    };
  }
};
</script>

<style scoped>
.movie-detail {
}
.movie {
  background-color: aquamarine;
  height: 400px;
  width: 100%;
}
#movie-poster {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-color: bisque;
}
#moive-detail {
  height: 100%;
  background-color: chartreuse;
}
#movie-title{
    font-size: 26px;
}
.schedual {
  background-color: aqua;
  width: 100%;
  height: 500px;
}
</style>
