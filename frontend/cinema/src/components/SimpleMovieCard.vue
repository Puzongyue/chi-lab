<template>
  <el-card 
    :body-style="{ padding: '0px' }"
    class="main"
    >
    <img
      :src="movie.poster"
      class="image"
    />
    <div style="padding: 14px">
      <span>好吃的汉堡</span>
      <div class="bottom clearfix">
        <time class="time">{{ currentDate }}</time>
        <el-button type="text" class="button">操作按钮</el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "SimpleMovieCard",

  props: ["movie"],
};
</script>

<style scoped>
.main {
  position: relative;
  height: 100px;
}

.main .image {
  width: 100%
}

</style>