<template>
  <div class="main">
    <div class="step-bar">
      <el-steps :active="activeStep" finish-status="success" align-center>
        <el-step title="选择座位"></el-step>
        <el-step title="15分钟内付款"></el-step>
        <el-step title="付款成功"></el-step>
      </el-steps>
    </div>

    <seat-selection :hallId="0" :soldSeats="soldSeats" :movie="mockMovie" @confirm="confirmSeats"></seat-selection>
  </div>
</template>

<script>
import SeatSelection from "@/components/SeatSelection.vue";
import Payment from "@/components/Payment.vue";

export default {
  name: "TicketPurchase",

  components: { SeatSelection, Payment },

  data() {
    return {
      activeStep: 0,
      // TODO: 获取对应schedule信息
      scheduleId: 0,
      // TODO: soldSeats的逻辑
      soldSeats: [
        [1, 1],
        [8, 7],
      ],
      // TODO: 获取对应movie
      mockMovie: {
        id: 6,
        name: "野性的呼唤",
        types: ["剧情", "冒险"],
        location: ["美国"],
        time: 100,
        boxOffice: 96280, // 以k为单位
        poster:
          "https://p0.meituan.net/moviemachine/cf7d6942f2aa9189cce20519b490b6b1879487.jpg@464w_644h_1e_1c",
        score: 9.2,
        like: 0,
        startDay: new Date(2020, 10, 14),
        description: `巴克是一只体型庞大、脾气暴躁的圣伯纳犬与苏格兰牧羊犬混血，
                  它和米勒法官一家在美国加州的圣克拉拉市一起居住。但巴克不幸被绑架，
                  并被卖了出去，成了一只奴役狗。后来巴克成了佩罗（奥马·希 饰）的狗拉雪橇队中一员，
                  佩罗是一个生性温和的邮递员，他的工作任务是在被冰雪覆盖、自然环境严酷恶劣的育空冻原上投递信件。
                  巴克很快融入到这只狗狗团队中，而且逐渐变成了狗狗们的领袖，但是电报系统的出现，
                  让佩罗的工作变得过时了。后来巴克又遇到了一系列新的主人，这里面有残酷的采矿者哈尔（丹·史蒂文斯 饰），
                  还有善良的好人约翰·桑顿（哈里森·福特 饰）。巴克很喜欢约翰，随着约翰旅程的持续进行，
                  本来是被高度驯化的巴克发现自己逐渐显露出动物天性，而且越来越多展现出动物冲动的本性，并被其所驱使。`,
        director: ["克里斯·桑德斯"],
        stars: [
          {
            star: "哈里森·福特",
            role: "John Thornton",
          },
          {
            star: "丹·史蒂文斯",
            role: "Hal",
          },
          {
            star: "凯伦·吉兰",
            role: "Mercedes",
          },
          {
            star: "珍·路易莎·凯利",
            role: "Katie Miller",
          },
        ],
      },
    };
  },

  methods: {
    confirmSeats(seats) {
      this.activeStep = 1;
      console.log("seats: ", seats);
    }
  }
};
</script>

<style scoped>
/* .main {
  position: relative;
} */

/* .main .step-bar {
  position: absolute;
  width: 100%;
  left: 50%;
  transform: translateX(-50%);
} */

.main .step-bar {
  margin: 30px auto;
  width: 100%;
}
</style>