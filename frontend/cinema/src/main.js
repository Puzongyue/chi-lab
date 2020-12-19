import Vue from "vue";
import App from "./App";
import router from "./router";
import "../theme/index.css";

import VueAwesomeSwiper from 'vue-awesome-swiper'

// import style (>= Swiper 6.x)
import 'swiper/swiper-bundle.css'

import ElementUI from "element-ui";
// import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
Vue.use(VueAwesomeSwiper, /* { default options with global component } */)

Vue.config.productionTip = false;

Vue.prototype.bus = new Vue();

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  render: h => h(App)
});
