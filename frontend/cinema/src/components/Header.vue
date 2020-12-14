<template>
  <div class="header-div">
    <el-menu
      :default-active="activeIndex"
      id="header"
      mode="horizontal"
      @select="handleSelect"
    >
      <div id="cinema-icon">SEC</div>
      <el-menu-item index="1" class="header-item">首页</el-menu-item>
      <el-menu-item index="2" class="header-item">电影</el-menu-item>
      <el-menu-item index="3" class="header-item">咨询</el-menu-item>
      <el-menu-item index="4" class="header-item">周边</el-menu-item>

      <div id="search-bar">
        <el-input
          placeholder="请输入要搜索的影片"
          v-model="keyword"
          class="search-input"
          @change="searchMovie"
        >
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
      </div>
      <div id="user">
        <el-dropdown id="user-dropdown">
          <div id="user-inner">
            <el-avatar :size="60" :src="circleUrl"></el-avatar
            ><i class="el-icon-arrow-down el-icon--right" id="user-arrow-icon"></i>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>基本信息</el-dropdown-item>
            <el-dropdown-item>我的电影票</el-dropdown-item>
            <el-dropdown-item>登出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      activeIndex: "1",
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      keyword: "",
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    searchMovie() {
      this.movies = getMoviesByKeyword(this.keyword);
    },
  },
};
</script>

<style scoped>
.header-div {
  margin: 0 -100px;
  position: sticky;
  z-index: 1000;
  top: 0;

  height: 80px;
  width: 100%；;
}

#header {
  height: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0 100px;
  position: relative;
}

#cinema-icon {
  width: 20%;
  border: solid 1px orchid;
}

.header-item {
  font-size: 18px;
}

#search-bar {
  position: absolute;
  right: 250px;
}

#search-bar >.el-input--prefix>.el-input__inner {
	border-radius: 20px;
}

#user {
  position: absolute;
  right: 100px;
  /* border: solid 1px orchid; */
  cursor: pointer;
}

#uer-dropdown{
    height: 100%;
}

#user-inner {
  display: flex;
  flex-direction: row;
  align-items: center;
}

#user-arrow-icon {
  vertical-align: middle;
}
</style>