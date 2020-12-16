<template>
  <div class="user-center">
    <el-row :gutter="32">
      <el-col :span="4" class="user-menu">
        <h2 class="menu-title">个人中心</h2>
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          router
          @select="handleSelect"
        >
          <el-submenu index="1">
            <template slot="title"
              ><i class="el-icon-menu"></i>我的订单</template
            >
            <el-menu-item
              index="2-3"
              :route="{
                path: '/usercenter/orders',
                query: { status: 'unpaid' }
              }"
              >待支付</el-menu-item
            >

            <el-menu-item
              index="2-1"
              :route="{
                path: '/usercenter/orders',
                query: { status: 'unused' }
              }"
              >待使用</el-menu-item
            >
            <el-menu-item
              index="2-2"
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
      <el-col :span="20" class="user-content">
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
      title: this.$route.path === "/usercenter" ? "基本信息" : "我的电影票"
    };
  },
  methods: {
    handleSelect(index, indexPath) {
      this.title = index === "2" ? "基本信息" : "我的电影票";
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
