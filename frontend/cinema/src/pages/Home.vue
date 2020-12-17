<template>
  <div class="home">
    <el-row id="carousel-row">
      <el-carousel :interval="4000" height="370px" id="movie-ad">
        <el-carousel-item
          v-for="item in carouselInfo"
          :key="item.id"
          @click.native="gotoMovie(item.id)"
        >
          <img class="carousel-poster" alt="poster" :src="item.poster"/>
        </el-carousel-item>
      </el-carousel>
    </el-row>
    <el-row :gutter="40">
      <el-col :span="16">
        <div id="current-movies">
          <div id="current-movies-header">
            <h2 class="module-header">{{ currentMoviesTitle }}</h2>
            <div
              class="hint-font"
              id="show-all-movies"
              @click="gotoMovieList(0)"
            >
              查看全部
            </div>
          </div>
          <div id="current-movies-content">
            <div
              class="current-movie-item"
              v-for="item in currentMoviesInfo"
              v-bind:key="item.id"
              @click="gotoMovie(item.id)"
            >
              <div
                class="current-movie-item-inner"
                :class="{
                  left: item.position % 3 == 0,
                  middle: item.position % 3 == 1,
                  right: item.position % 3 == 2,
                }"
              >
                <img
                  class="current-movie-poster"
                  alt="poster"
                  width="160px"
                  height="220px"
                  :src="item.poster"
                />
                <div class="current-movie-overlay"></div>
                <div class="current-movie-info">
                  <div class="current-movie-name">{{ item.name }}</div>
                  <div class="current-movie-score">
                    {{ item.score.toFixed(1) }}
                  </div>
                </div>
                <div class="current-movie-buy">
                  <el-button type="danger" round class="current-movie-buy-btn"
                    >购票</el-button
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="incoming-movies">
          <div id="incoming-movies-header">
            <h2 class="module-header">{{ incomingMoviesTitle }}</h2>
            <div
              class="hint-font"
              id="show-all-incoming-movies"
              @click="gotoMovieList(1)"
            >
              查看全部
            </div>
          </div>
          <div id="incoming-movies-content">
            <div
              class="incoming-movie-item"
              v-for="item in incomingMoviesInfo"
              v-bind:key="item.id"
            >
              <div
                class="incoming-movie-item-inner"
                :class="{
                  left: item.position % 3 == 0,
                  middle: item.position % 3 == 1,
                  right: item.position % 3 == 2,
                }"
              >
                <img
                  class="incoming-movie-poster"
                  alt="poster"
                  width="160px"
                  height="220px"
                  :src="item.poster"
                />
                <div class="incoming-movie-overlay"></div>
                <div class="incoming-movie-info">
                  <div class="incoming-movie-name">{{ item.name }}</div>
                </div>
                <div class="incoming-movie-buy">
                  <el-button type="danger" round class="incoming-movie-buy-btn"
                    >预售</el-button
                  >
                </div>
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
            v-for="item in movieRankInfo"
            v-bind:key="item.id"
            @click="gotoMovie(item.id)"
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
              <div class="item-name top-item-name">
                <span>{{ item.name }}</span>
                <span class="top-item-type">{{ item.types.join(" / ") }}</span>
              </div>
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
import homeInfo from "../lib/homeInfo";

export default {
  name: "Home",
  data() {
    return {
      msg: "Home",
      carouselInfo: homeInfo.test,
      currentMoviesInfo: homeInfo.currentMoviesInfo,
      incomingMoviesInfo: homeInfo.incomingMoviesInfo,
      movieRankInfo: homeInfo.movieRankInfo,

      currentMoviesTitle: "正在热映",
      incomingMoviesTitle: "即将上映",
      movieRankTitle: "票房排行",
    };
  },
  methods: {
    gotoMovie(id) {
      this.$router.push("/movie/" + id);
    },
    gotoMovieList(type) {
      this.$router.push("/list");
    },
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

#carousel-row {
  margin: 0 -100px;
}

#movie-ad {
  margin-left: 0;
  margin-right: 0;
  margin-bottom: 40px;
}

.carousel-poster {
  width: 100%;
  height: 100%;
  cursor: pointer;
}

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

/* current-movies module & incoming-movies module*/
#current-movies,
#incoming-movies {
  height: auto;
  display: flex;
  flex-direction: column;

  padding-right: 100px;
  margin-bottom: 50px;
}

#current-movies-header,
#movie-rank-header,
#incoming-movies-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-end;

  height: 50px;
  width: 100%;
}

#show-all-movies,
#show-all-incoming-movies {
  padding: 0;
  margin: 10px 0; /* For alignment purpose */
  cursor: pointer;
}

#show-all-movies:hover,
#show-all-incoming-movies:hover {
  text-decoration: orangered underline;
}

#current-movies-content,
#incoming-movies-content {
  width: 100%;
  height: auto;
  padding-top: 15px;

  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

.current-movie-item,
.incoming-movie-item {
  width: 33.3%;
  height: 220px;
  margin-bottom: 25px;
  position: relative;
  display: flex;
}

.current-movie-item-inner,
.incoming-movie-item-inner {
  position: absolute;
  width: 160px;
  height: 100%;
}

.current-movie-item > .left,
.incoming-movie-item > .left {
  left: 0;
}

.current-movie-item > .middle,
.incoming-movie-item > .middle {
  left: calc(calc(100% - 160px) / 2);
}

.current-movie-item > .right,
.incoming-movie-item > .right {
  right: 0;
}

.current-movie-poster,
.current-movie-overlay,
.incoming-movie-poster,
.incoming-movie-overlay {
  position: absolute;
  width: 100%;
  height: 100%;
}

.current-movie-overlay,
.incoming-movie-overlay {
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKAAAABQCAMAAACpg44GAAAAbFBMVEUiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiK2HsfSAAAAJHRSTlPMxr63q6OahzclHRULBgHCsZOOf3p1cGtmYVxXUk1IQz4wLBAszz8hAAAAhklEQVRo3u3OxY0DAAADsBxDmZn337FTVPIjnsAZ4/zgCOcHhzg/+MBlgPOD/zg/+Ifzg3dcbrj84vzgFZcLLmdcTrgccTngssdlh8sWlw0ua1xWuCxxWeDyg/ODc1xmuHzj/OAXzg9+4vzgFJcPnB98x/nBCS5vOD9YVVVVVVVVVVVVr/IEUdzLcLhtP2AAAAAASUVORK5CYII=)
    repeat-x bottom;
}

.current-movie-info,
.incoming-movie-info {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 148px;
  padding: 0 6px;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.current-movie-name,
.incoming-movie-name {
  color: #fff;
  padding-bottom: 6px;
  font-size: 16px;
}

.current-movie-score {
  color: #fdbf54;
  font-size: 18px;
  font-style: italic;
}

.current-movie-buy,
.incoming-movie-buy {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  visibility: hidden;

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.current-movie-buy-btn,
.incoming-movie-buy-btn {
  background-color: orangered;
}

.current-movie-item-inner:hover > .current-movie-buy,
.incoming-movie-item-inner:hover > .incoming-movie-buy {
  visibility: visible;
  background-color: rgba(0, 0, 0, 0.192);
  cursor: pointer;
  box-shadow: 10px 10px 20px #d3d2d2;
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
  width: 230px;
  display: inline-block;

  font-size: 18px;
  line-height: 20px;
}

.top-item-name {
  width: 130px;
  margin-left: 10px;
  display: flex;
  flex-direction: column;
}

.top-item-type {
  font-size: 14px;
  color: #99a9bf;
  margin-top: 10px;
}

.item-boxoffice {
  display: inline-block;
  line-height: 20px;
  width: 20%;
}
</style>
