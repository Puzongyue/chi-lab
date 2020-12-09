<template>
      <div class="no-movie-tip">
        <h3>很抱歉，没找到相关的电影</h3>
        <div class="tip">
          <div class="tip-title">NJU-SE影院建议您：</div>
          <div 
            class="tip-item" 
            v-for="(sentence, index) in tipSentences" 
            :key="index"
          >
            {{ index + 1}}. {{ sentence }}
          </div>
        </div>
      </div>
</template>

<script>
export default {
  name: "EmptyTip",

  props: ["tipSentences"],
}
</script>

<style scoped>
.no-movie-tip h3 {
  margin-top: 98px;
  font-size: 26px;
  color: #999;
  font-weight: 400;
}

.no-movie-tip .tip {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px; 
  margin-top: 27px;
}

.no-movie-tip .tip-title {
  color: #ef4238;
}

.no-movie-tip .tip-item {
  color: #333;
  margin-top: 10px;
  margin-left: 0;
}
</style>