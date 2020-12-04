<template>
	<div class="main">
		<div class="search-bar"></div>
		<div class="movie-list">
			<el-row :gutter="20">
				<el-col :span="12" v-for="movie in movies" :key="movie.id">
					<search-result-card :movie="movie"></search-result-card>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
import SearchResultCard from '../components/SearchResultCard.vue';
import movies from "../lib/movieList";


export default {
  components: { SearchResultCard },
	name: "SearchResult",

	data() {
		return {
			movies: movies
		}
	}
}
</script>

<style scoped>

.main {
	margin-top: 20px;
	height: 1000px;
	background-color: aquamarine;
}

.main .search-bar {
	height: 20vh;
	background-color: blue;
}
</style>