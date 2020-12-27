<template>
  <el-tabs v-model="activeName" @tab-click="switchTab" class="tab">
    <div class="filter-bar">
      <el-card class="box-card">
        <el-row class="type">
          <el-col :offset="1" :span="1" class="tag-title">类型:</el-col>
          <el-col :span="20">
            <ul class="list">
              <li 
                :class="{ active: currentTypeIdx === -1 }"
                @click="filterType(-1)"
              >
                全部
              </li>
              <li
                v-for="(type, index) in this.allTypes"
                :class="{ active: currentTypeIdx === index }"
                :key="index"
                @click="filterType(index)"
              >
                {{ type }}
              </li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="location">
          <el-col :offset="1" :span="1" class="tag-title">区域:</el-col>
          <el-col :span="20">
            <ul class="list">
              <li 
                :class="{ active: currentLocationIdx === -1 }"
                @click="filterLocation(-1)"
                >
                  全部
              </li>
              <li
                v-for="(location, index) in this.allLocations"
                :class="{ active: currentLocationIdx === index }"
                :key="index"
                @click="filterLocation(index)"
              >
                {{ location }}
              </li>
            </ul>
          </el-col>
        </el-row>
      </el-card>
    </div>

    <div class="sort">
      <el-radio-group v-model="radio" @change="sortMovie">
        <el-radio :label="0">按热度排序</el-radio>
        <el-radio :label="1">按时间排序</el-radio>
        <el-radio :label="2" v-if="activeName === 'current'"
          >按评分排序</el-radio
        >
      </el-radio-group>
    </div>

    <el-tab-pane label="正在热映" name="current">
      <empty-tip v-if="movies.length === 0" :tipSentences="tipSentences"></empty-tip>
      <el-row :gutter="20">
        <el-col :span="4" v-for="movie in movies" :key="movie.id">
          <simple-movie-card :movie="movie"></simple-movie-card>
        </el-col>
      </el-row>
    </el-tab-pane>
    <el-tab-pane label="即将上映" name="future">
      <empty-tip v-if="movies.length === 0" :tipSentences="tipSentences"></empty-tip>
      <el-row :gutter="20">
        <el-col :span="4" v-for="movie in movies" :key="movie.id">
          <simple-movie-card :movie="movie"></simple-movie-card>
        </el-col>
      </el-row>
    </el-tab-pane>
  </el-tabs>
</template>


<script>
import SimpleMovieCard from "../components/SimpleMovieCard.vue";
import EmptyTip from "../components/EmptyTip.vue";
import {
  getCurrentMovies,
  getFutureMovies,
  getAllMovieTypes,
  getAllMovieLocations,
  sortMoviesByHeat,
  sortMoviesByDate,
  sortMoviesByScore,
  filterMovies
} from "../lib/movieList";

export default {
  components: { SimpleMovieCard, EmptyTip },

  data() {
    return {
      activeName: "current",
      allTypes: [],
      allLocations: [],
      currentTypeIdx: -1,
      currentLocationIdx: -1,
      radio: 0,
      movies: [],
      tipSentences: ["请修改选择的类型或区域", "请切换至“正在热映”或“即将上映”"]
    };
  },

  watch: {
    $route(to, from) {
      this.activeName = this.$route.query.type;
      this.movies = this.activeName === "current"? getCurrentMovies() : getFutureMovies();
    },
  },

  mounted() {
    this.activeName = this.$route.query.type;
    this.movies = this.activeName === "current"? getCurrentMovies() : getFutureMovies();
    this.allTypes = getAllMovieTypes();
    this.allLocations = getAllMovieLocations();
    this.sortMovie();
  },

  methods: {
    switchTab() {
      this.radio = 0; // default sort type
      this.currentTypeIdx = -1; // default type
      this.currentLocationIdx = -1; // default location

      this.activeName = this.$route.query.type;
      if(this.activeName === "current") {
        this.$router.push({path: "/list", query: { type: "future"}});
      }
      else {
        this.$router.push({path: "/list", query: { type: "current"}});
      }
      this.movies = this.activeName === "current"? getCurrentMovies() : getFutureMovies();
      this.sortMovie();
      this.filterType(-1);
      this.filterLocation(-1);
    },

    filterType(idx) {
      let type = "all";
      if (idx === -1) {
        this.currentTypeIdx = -1;
      } else {
        this.currentTypeIdx = idx;
        type = this.allTypes[this.currentTypeIdx];
      }

      let location = this.currentLocationIdx === -1 ? "all" : this.allLocations[this.currentLocationIdx];

      this.movies = filterMovies(type, location, this.activeName);
      this.sortMovie();
    },

    filterLocation(idx) {
      let location = "all";
      if (idx === -1) {
        this.currentLocationIdx = -1;
      } else {
        this.currentLocationIdx = idx;
        location = this.allLocations[this.currentLocationIdx];
      }

      let type = this.currentTypeIdx === -1 ? "all" : this.allTypes[this.currentTypeIdx];
      this.movies = filterMovies(type, location, this.activeName);
      this.sortMovie();
    },

    sortMovie() {
      switch(this.radio) {
        case 0:
          this.movies = sortMoviesByHeat();
          break;
        case 1:
          this.movies = sortMoviesByDate();
          break;
        case 2:
          this.movies = sortMoviesByScore();
          break;
        default:
          break;
      }
    },
  },
};
</script>

<style>
.active {
  background-color: #ffb400;
  border-radius: 20px;
}

.el-tabs__nav-scroll {
  display: flex;
  justify-content: center;
}

.tab {
  margin-top: 30px;
}

.tab .filter-bar {
  margin-bottom: 15px;
}

.tab .filter-bar .box-card {
  padding: 0 20px;
}

.tab .filter-bar .box-card .tag-title {
  color: #999;
  line-height: 24px;
  height: 24px;
  font-size: 14px;
}

.tab .filter-bar .box-card .list {
  display: inline-block;
  padding: 0;
  margin: 0;
}

.tab .filter-bar .type {
  border-bottom: 1px dotted #e5e5e5;
  padding-bottom: 15px;
}

.tab .filter-bar .location {
  padding-top: 15px;
}

.tab .filter-bar li {
  text-decoration: none;
  display: inline-block;
  padding: 3px 9px;
  margin-left: 12px;
  font-size: 14px;
  cursor: pointer;
}

.tab .sort {
  /* background-color: aquamarine; */
  margin: 80px 0 10px 0;
}
</style>