<template>
  <div class="home">
    <el-row>
      <el-carousel :interval="4000" type="card" height="250px">
        <el-carousel-item v-for="item in 6" :key="item">
          <h3 class="medium">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </el-row>
    <el-row :gutter="40">
      <el-col :span="16">
        <div id="current-movies">
          <div id="current-movies-header">
            <h2 class="module-header">{{ currentMoviesTitle }}</h2>
            <div class="hint-font" id="show-all-movies">查看全部</div>
          </div>
          <div id="current-movies-content">
            <div class="current-movie-item" v-for="i in 9" v-bind:key="i">
              <img
                class="current-movie-poster"
                alt="poster"
                width="160px"
                height="220px"
                src="https://p0.meituan.net/movie/303c2e671cc4df875c151d688ecbd8962085989.jpg@464w_644h_1e_1c"
              />
              <div class="current-movie-overlay"></div>
              <div class="current-movie-info">
                <div class="current-movie-name">赤狐书生</div>
                <div class="current-movie-score">7.8</div>
              </div>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div id="movie-rank">
          <div id="movie-rank-header">
            <h2 class="module-header">{{ movieRankTitle }}</h2>
          </div>
          <div
            class="rank-item"
            :class="{ topItem: item.rank < 4 }"
            v-for="item in rankList"
            v-bind:key="item.id"
          >
            <div class="rank-item-inner" v-if="item.rank < 4">
              <span class="item-rank top-item-rank">{{ item.rank }}</span>
              <img
                class="item-poster"
                alt="poster"
                width="100px"
                height="65px"
                src="https://p1.meituan.net/movie/80a0afab60ba5bc548a0c606635fef291479960.jpg@120w_80h_1e_1c"
              />
              <span class="item-name top-item-name">{{ item.name }}</span>
              <span class="item-boxoffice hint-font"
                >{{ item.boxOffice }}万</span
              >
            </div>
            <div class="rank-item-inner" v-else>
              <span class="item-rank">{{ item.rank }}</span>
              <span class="item-name">{{ item.name }}</span>
              <span class="item-boxoffice hint-font"
                >{{ item.boxOffice }}万</span
              >
            </div>
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
          name: "如果声音不记得",
          boxOffice: 576,
          rank: 1,
        },
        {
          id: 1,
          name: "如果声音不记得",
          boxOffice: 576,
          rank: 2,
        },
        {
          id: 2,
          name: "如果声音不记得",
          boxOffice: 576,
          rank: 3,
        },
        {
          id: 3,
          name: "到了30岁还是处男，似乎会变成魔法师",
          boxOffice: 576,
          rank: 4,
        },
        {
          id: 4,
          name: "如果声音不记得",
          boxOffice: 576,
          rank: 5,
        },
        {
          id: 5,
          name: "疯狂原始人2",
          boxOffice: 576,
          rank: 6,
        },
        {
          id: 6,
          name: "疯狂原始人2",
          boxOffice: 576,
          rank: 7,
        },
        {
          id: 7,
          name: "疯狂原始人2",
          boxOffice: 576,
          rank: 8,
        },
        {
          id: 8,
          name: "疯狂原始人2",
          boxOffice: 576,
          rank: 9,
        },
        {
          id: 9,
          name: "疯狂原始人2",
          boxOffice: 576,
          rank: 10,
        },
      ],
      currentMoviesTitle: "正在热映",
      movieRankTitle: "票房排行",
    };
  },
};
</script>

<style>
/* fonts setting*/
.module-header {
  color: orangered;
  font-size: 30px;
  font-weight: 400;
  padding: 0;
  margin: 10px 0;
}

.hint-font {
  color: orangered;
  font-size: 16px;
}

/* carousel */
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

/* current-movies module */
#current-movies {
  height: 800px;
  display: flex;
  flex-direction: column;

  padding-right: 100px;
}

#current-movies-header,
#movie-rank-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-end;

  height: 50px;
  width: 100%;
}

#show-all-movies {
  padding: 0;
  margin: 10px 0; /* For alignment purpose */
  cursor: pointer;
}

#current-movies-content {
  width: 100%;
  height: 750px;
  padding-top: 15px;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  flex-wrap: wrap;

  /* border: solid 2px orange; */
}

.current-movie-item {
  width: 160px;
  height: 220px;
  position: relative;
}

.current-movie-item:hover {
  cursor: pointer;
  box-shadow: 10px 10px 20px #d3d2d2;
}

.current-movie-poster,
.current-movie-overlay {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  /* border: solid 2px lightgreen; */
}

.current-movie-overlay {
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKAAAABQCAMAAACpg44GAAAAbFBMVEUiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiK2HsfSAAAAJHRSTlPMxr63q6OahzclHRULBgHCsZOOf3p1cGtmYVxXUk1IQz4wLBAszz8hAAAAhklEQVRo3u3OxY0DAAADsBxDmZn337FTVPIjnsAZ4/zgCOcHhzg/+MBlgPOD/zg/+Ifzg3dcbrj84vzgFZcLLmdcTrgccTngssdlh8sWlw0ua1xWuCxxWeDyg/ODc1xmuHzj/OAXzg9+4vzgFJcPnB98x/nBCS5vOD9YVVVVVVVVVVVVr/IEUdzLcLhtP2AAAAAASUVORK5CYII=)
    repeat-x bottom;
}

.current-movie-info {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 148px;
  padding: 0 6px;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.current-movie-name {
  color: #fff;
  padding-bottom: 6px;
}

.current-movie-score {
  color: #fdbf54;
  font-size: 18px;
  font-style: italic;
}

/* movie-rank module */

#movie-rank {
  height: 800px;
  /* border: solid 1px rebeccapurple; */
}

.rank-item {
  padding: 15px 0;
  cursor: pointer;
}

.rank-item:hover {
  background-color: rgb(253, 245, 245);
}

.topItem {
  /* border: solid gainsboro 2px; */
}

.rank-item-inner {
  display: flex;
  justify-content: space-between;
}

.item-rank {
  width: 20px;
  display: inline-block;

  font-size: 18px;
  color: grey;
  font-style: italic;
}

.top-item-rank {
  color: orangered;
}

.item-name {
  width: 250px;
  display: inline-block;

  font-size: 18px;
  line-height: 20px;
}

.top-item-name {
  width: 140px;
}

.item-boxoffice {
  display: inline-block;
  line-height: 20px;
}
</style>
