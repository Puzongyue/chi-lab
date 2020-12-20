<template>
  <div class="main">
    <div class="search-bar">
      <el-input
        placeholder="请输入要搜索的影片"
        v-model="keyword"
        class="search-input"
        @change="searchMovie"
      >
        <i slot="prefix" class="el-input__icon el-icon-search"></i>
      </el-input>
    </div>
    <div class="movie-list">
      <empty-tip
        v-if="movies.length === 0"
        :tipSentences="tipSentences"
      ></empty-tip>
      <el-row :gutter="20">
        <el-col :span="12" v-for="movie in movies" :key="movie.id">
          <search-result-card :movie="movie"></search-result-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import SearchResultCard from "../components/SearchResultCard.vue";
import EmptyTip from "../components/EmptyTip.vue";
import { movies, getMoviesByKeyword } from "../lib/movieList";
// import movies from "../lib/movieList";

export default {
  components: { SearchResultCard, EmptyTip },
  name: "SearchResult",

  data() {
    return {
      movies: movies,
      keyword: "",
      tipSentences: ["请检查输入的关键词是否有误", "请缩短关键词"],
    };
  },

  methods: {
    searchMovie() {
      this.movies = getMoviesByKeyword(this.keyword);
    },
  },

  created() {
    this.bus.$on("searchKey", (value) => {
      console.log("result: " + value);
      this.keyword = value;
      this.searchMovie();
    });
  },

  mounted() {
    this.bus.$on("searchKey", (value) => {
      this.keyword = value;
      this.searchMovie();
    });
  },
};
</script>

<style>
.main {
  margin-top: 20px;
}

.main .search-bar {
  height: 20vh;
  background-color: #47464a;
  opacity: 0.7;
  position: relative;
}

.main .search-bar .search-input {
  position: absolute;
  width: 50%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.main .el-input--prefix .el-input__inner {
  border-radius: 20px;
}

.main .movie-list {
  margin-top: 20px;
}

.main .movie-list .no-movie-tip {
  position: relative;
  left: 80%;
  transform: translateX(-50%);
}
</style>