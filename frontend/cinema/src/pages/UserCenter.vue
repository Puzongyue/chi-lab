<template>
  <div class="user-center">
    <el-row :gutter="16">
      <el-col :span="5" class="user-menu">
        <h2 class="menu-title">个人中心</h2>
        <el-menu
          :default-active="activeIndex"
          :default-openeds="['1']"
          class="el-menu-vertical-demo"
          router
          @select="handleSelect"
          ref="userMenu"
        >
          <el-submenu index="1">
            <template slot="title"
              ><i class="el-icon-menu"></i>我的订单</template
            >
            <el-menu-item
              index="1.1"
              :route="{
                path: '/usercenter/orders',
                query: { status: 'unpaid' }
              }"
              >待支付</el-menu-item
            >

            <el-menu-item
              index="1.2"
              :route="{
                path: '/usercenter/orders',
                query: { status: 'unused' }
              }"
              >待使用</el-menu-item
            >
            <el-menu-item
              index="1.3"
              :route="{
                path: '/usercenter/orders',
                query: { status: 'finished' }
              }"
              >已完成</el-menu-item
            >
          </el-submenu>
          <el-menu-item index="2" :route="{ path: '/usercenter' }">
            <i class="el-icon-setting"></i>
            <span slot="title">基本信息</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="19" class="user-content">
        <div class="title">{{ title }}</div>
        <router-view />
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "UserCenter",
  data() {
    return {
      title: this.$route.path === "/usercenter" ? "基本信息" : "我的电影票",
      activeIndex: "",
      
    };
  },
  mounted() {
    this.activeIndex = this.getIndex();
  },
  methods: {
    handleSelect(index, indexPath) {
      this.title = index === "2" ? "基本信息" : "我的电影票";
    },
    getIndex() {
      const route = this.$route;
      const currentIndex =
        route.name === "UserInfo"
          ? "2"
          : route.query.status === "unpaid"
          ? "1.1"
          : route.query.status === "unused"
          ? "1.2"
          : "1.3";
      return currentIndex;
    }
  },
  watch: {
    $route() {
      const currentIndex = this.getIndex();
      if (currentIndex !== "2"){
      }
      this.$refs.userMenu.activeIndex = currentIndex;
    }
  }
};
</script>

<style scoped>
.user-menu {
  height: 100%;
}
.user-menu .menu-title {
  text-align: center;
}

.user-content {
  min-height: 900px;
}
.user-content .title {
  font-size: 18px;
  padding: 30px 0;
  color: #ff4500;
  border-bottom: 1px solid #e1e1e1;
  margin-bottom: 30px;
}
</style>
