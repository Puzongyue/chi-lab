import Vue from "vue";
import Router from "vue-router";
import Home from "@/pages/Home";
import SearchResult from "@/pages/SearchResult";
import MovieDetail from "@/pages/MovieDetail";
import MovieList from "@/pages/MovieList";
import TicketPurchase from "@/pages/TicketPurchase";
import UserCenter from "@/pages/UserCenter";
import BasicInfo from "../components/UserCenter/BasicInfo.vue";
import UserTicket from "../components/UserCenter/UserTicket.vue";
import SeatSelection from "../components/SeatSelection.vue";
import Payment from "../components/Payment.vue";
import PaymentSuccess from "../components/PaymentSuccess.vue";

import Test from "@/pages/Test";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home
    },
    {
      path: "/search",
      name: "SearchResult",
      component: SearchResult
    },
    {
      path: "/movie/:id",
      name: "MovieDetail",
      component: MovieDetail,
      beforeEnter:  (to, from, next) => {
        window.scrollTo(0,0)
        next();
      }
    },
    {
      path: "/list",
      name: "MovieList",
      component: MovieList
    },
    {
      path: "/purchase",
      name: "TicketPurchase",
      component: TicketPurchase,
      children: [
        {
          path: "selection",
          component: SeatSelection,
          name: "SeatSelection"
        },
        {
          path: "payment",
          component: Payment,
          name: "Payment"
        },
        {
          path: "success",
          component: PaymentSuccess,
          name: "PaymentSuccess"
        }
      ]
    },
    {
      path: "/usercenter",
      name: "UserCenter",
      component: UserCenter,
      children: [
        {
          path: "/",
          component: BasicInfo,
          name: "UserInfo"
        },
        {
          path: "orders",
          component: UserTicket,
          name: "UserOrder"
        }
      ]
    },
    {
      name: "Test",
      path: "/test",
      component: Test
    }
  ]
});
