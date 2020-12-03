<template>
	<div class="main">
		<div class="search-bar"></div>
		<div class="movie-list"></div>
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
	margin: 20px 30px;
	height: 1000px;
	background-color: aquamarine;
}

</style>