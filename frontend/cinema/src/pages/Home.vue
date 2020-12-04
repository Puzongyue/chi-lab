<template>
  <div class="home">
    <el-row>
      <el-carousel :interval="4000" type="card" height="250px">
        <el-carousel-item v-for="item in 6" :key="item">
          <h3 class="medium">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </el-row>
    <el-row :gutter="30">
      <el-col :span="16">
        <div id="current-movies">
          <h2>{{currentMoviesTitle}}</h2>
        </div>
      </el-col>
      <el-col :span="8">
        <div id="movie-rank">
          <h2>{{movieRankTitle}}</h2>
          <div class="rank-item" v-for="item in rankList" v-bind:key="item.id">
            {{item.name}}
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      msg: "Home",
      rankList: [
        {
          id: 0,
          name: "疯狂原始人2",
          boxOffice: 576,
        },
        {
          id: 1,
          name: "疯狂原始人2",
          boxOffice: 576,
        },
        {
          id: 2,
          name: "疯狂原始人2",
          boxOffice: 576,
        }
      ],
      currentMoviesTitle: "正在热映",
      movieRankTitle: "票房排行",
    };
  }
};
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

#current-movies {
  height: 500px;
  background-color: aquamarine;
}

#movie-rank {
  height: 500px;
  background-color: aquamarine;
}
</style>
