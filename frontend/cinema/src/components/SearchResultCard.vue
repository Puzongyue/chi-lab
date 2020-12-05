<template>
  <el-card class="el-card" shadow="hover">
    <div class="card">
      <div class="movie-poster" @click="jumpToMovieDetail">
        <img :src="this.movie.poster" />
      </div>
      <div class="movie-info">
        <div class="movie-name" @click="jumpToMovieDetail">{{ this.movie.name }}</div>

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
    </div>
  </el-card>
</template>

<script>
export default {
  name: "SearchResultCard",

	props: ["movie"],
	
	methods: {
		jumpToMovieDetail() {
			console.log(this.movie.id);
		}
	}
};
</script>

<style scoped>
.el-card {
	margin-bottom: 8px;
}
.card {
  display: flex;
  justify-content: space-around;
	padding: 0 100px 0 50px;
}

.card .movie-poster {
  display: inline-block;
  width: 180px;
  height: 240px;
	cursor: pointer;
}

.card .movie-poster img {
  width: 100%;
  height: 100%;
}

.card .movie-info {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.card .movie-info .movie-name {
  color: #333;
  font-size: 26px;
	cursor: pointer;
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
  line-height: 1.2;
}

.card .movie-info .movie-start-date {
  color: #999;
}
</style>