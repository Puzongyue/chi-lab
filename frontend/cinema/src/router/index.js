import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/Home'
import SearchResult from '@/pages/SearchResult'
import MovieDetail from '@/pages/MovieDetail'
import MovieList from '@/pages/MovieList'
import TicketPurchase from '@/pages/TicketPurchase'
import Test from '@/pages/Test'

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
    },
    {
      path: '/movie/:id',
      name: 'MovieDetail',
      component: MovieDetail
    },
    {
      path: '/list',
      name: 'MovieList',
      component: MovieList
    },
    {
      path: '/purchase',
      name: 'TicketPurchase',
      component: TicketPurchase
    },
    {
      name: 'Test',
      path: '/test',
      component : Test
    }
  ]
})
