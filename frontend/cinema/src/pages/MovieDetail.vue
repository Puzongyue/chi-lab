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
      <el-col :span="16" id="moive-detail">
        <div id="movie-title">{{ movie.name }}</div>
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
            上映时间：{{ movie.startDay.toISOString().substring(0, 10) }}
          </div>
          <div>时长：{{ movie.time }}分钟</div>
          <div>简介：{{ movie.description }}</div>
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
  display: flex;
  flex-direction: column;
  align-content: center;
}
#movie-title {
  font-size: 26px;
  margin-bottom: 20px;
}
.detail {
  font-size: 12px;
}
.schedual {
  background-color: aqua;
  width: 100%;
  height: 500px;
}
</style>
