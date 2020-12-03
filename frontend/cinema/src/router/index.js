import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/Home'
import SearchResult from '@/pages/SearchResult'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/search',
      name: 'SearchResult',
      component: SearchResult
    }
  ]
})
