<template>
  <div class="header-div">
    <el-menu
      :default-active="activeIndex"
      id="header"
      mode="horizontal"
      @select="handleSelect"
      router
      ref="menu"
    >
      <div id="cinema-icon" @click="gotoHome">
        <img
          src="https://p.pstatp.com/origin/1383d0002e72253ecf7dc"
          alt="logo"
          id="icon-pic"
        />
      </div>
      <el-menu-item index="/" class="header-item" :route="{ path: '/' }"
        >首页</el-menu-item
      >
      <el-menu-item index="/list" class="header-item" :route="{ path: '/list' }"
        >电影</el-menu-item
      >
      <el-menu-item index="3" class="header-item">资讯</el-menu-item>
      <el-menu-item index="4" class="header-item">周边</el-menu-item>

      <div id="search-bar">
        <el-input
          placeholder="请输入要搜索的影片"
          v-model="keyword"
          class="search-input"
          @change="searchMovie"
          @keyup.enter.native="searchMovie"
        >
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
      </div>
      <div id="user">
        <el-dropdown id="user-dropdown" @command="gotoUserCenter">
          <div id="user-inner">
            <el-avatar :size="60" :src="circleUrl"></el-avatar
            ><i
              class="el-icon-arrow-down el-icon--right"
              id="user-arrow-icon"
            ></i>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="0">基本信息</el-dropdown-item>
            <el-dropdown-item command="1">我的订单</el-dropdown-item>
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
      activeIndex: "/",
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      keyword: "",
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      // console.log(key, keyPath);
    },
    searchMovie() {
      this.$router.push({path: "/search", query: { keyword: this.keyword}});
    },
    gotoUserCenter(command) {
      if (command == "0") {
        this.$router.push("/usercenter");
      } else if (command == "1") {
        this.$router.push("/usercenter/orders?status=unpaid");
      }
    },
    gotoHome() {
      if (this.$route.path !== "/") {
        this.$router.push("/");
      }
    },
  },
  watch: {
    $route() {
      this.keyword = this.$route.query.keyword || "";
      let currentPath = this.$route.path;
      if (
        currentPath.includes("/movie") ||
        currentPath.includes("/list") ||
        currentPath.includes("/search")
      ) {
        this.$refs.menu.activeIndex = "/list";
      } else if (currentPath === "/") {
        this.$refs.menu.activeIndex = "/";
      } else if (currentPath.includes("/usercenter")) {
        this.$refs.menu.activeIndex = "";
      }
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
  height: 100%;

  display: flex;
  align-items: center;
  justify-content: center;
  border: hidden;

  cursor: pointer;
}

#cinema-icon:focus {
  outline: none;
}
#icon-pic {
  width: 60%;
  height: 60%;
}

.header-item {
  font-size: 18px;
  text-decoration: none;
}

#search-bar {
  position: absolute;
  right: 250px;
}

#search-bar > .el-input--prefix > .el-input__inner {
  border-radius: 20px;
}

#user {
  position: absolute;
  right: 100px;
  /* border: solid 1px orchid; */
  cursor: pointer;
}

#uer-dropdown {
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