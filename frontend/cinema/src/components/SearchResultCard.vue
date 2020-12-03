<template>
  <el-card class="card">
    <el-col :span="10" class="movie-poster">
      <img :src="this.movie.poster"/>
    </el-col>
    <el-col :span="12" class="movie-info">
      <div class="movie-name">{{ this.movie.name }}</div>

			<div class="movie-like" v-if="this.movie.score === -1">
				<span class="num">{{ this.movie.like }}</span>
				<span class="text">想看</span>
			</div>

			<div class="movie-score" v-else>
				<span class="num">{{ this.movie.score }}</span>
			</div>

			<div class="movie-type">{{ this.movie.type }}</div>
			<div class="movie-start-date">{{ this.movie.start }}</div>
    </el-col>
  </el-card>
</template>

<script>
export default {
  name: "SearchResultCard",

  props: ["movie"],
};
</script>

<style scoped>
.card {
  display: block;
	margin-bottom: 8px;
}

.card .movie-poster {
	display: inline-block;
	width: 160px;
	height: 220px;
	padding-left: 20px;
	margin-right: 20px;
}

.card .movie-poster img {
	width: 100%;
	height: 100%;
}

.card .movie-info {
  display: flex;
	flex-direction: column;
	justify-items: center;
  /* vertical-align: top; */
}

.card .movie-info .movie-name {
	color: #333;
	font-size: 26px;
}
</style>