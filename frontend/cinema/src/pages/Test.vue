<template>
  <swiper ref="mySwiper" :options="swiperOptions" :slides-per-view="3">
    <swiper-slide class="side">Slide 1</swiper-slide>
    <swiper-slide class="side">Slide 2</swiper-slide>
    <swiper-slide class="side">Slide 3</swiper-slide>
    <swiper-slide class="side">Slide 4</swiper-slide>
    <swiper-slide class="side">Slide 5</swiper-slide>
    <div class="swiper-pagination" slot="pagination"></div>
    <div class="swiper-button-prev"></div>
    <!--左箭头。如果放置在swiper-container外面，需要自定义样式。-->
    <div class="swiper-button-next"></div>
    <!--右箭头。如果放置在swiper-container外面，需要自定义样式。-->
  </swiper>
</template>

<script>
export default {
  name: "Test",
  data() {
    return {
      swiperOptions: {
        pagination: {
          el: ".swiper-pagination"
        },
        slidesPerView: 3,
        spaceBetween : 20,
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev"
        }

        // Some Swiper option/callback...
      }
    };
  },
  computed: {
    swiper() {
      return this.$refs.mySwiper.$swiper;
    }
  },
  mounted() {
    console.log("Current Swiper instance object", this.swiper);
    //   this.swiper.slideTo(3, 10000, false)
  }
};
</script>

<style scoped>
.side {
  background-color: yellowgreen;
  height: 100%;
}
</style>
