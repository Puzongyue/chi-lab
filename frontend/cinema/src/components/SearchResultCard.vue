<template>
  <el-card class="card">
    <img
      src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
			class="image"
    />
    <div class="movie-info">
      <span>好吃的汉堡</span>
      <div>
        <el-button>操作按钮</el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "SearchResultCard",

  props: ["movieList"],
};
</script>

<style scoped>

.card {
	display: block;
}

.card .image {
	display: inline-block;
	
}

.card .movie-info {
	display: inline-block;
	vertical-align: top;
}

</style>