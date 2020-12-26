<template>
  <div class="success-container">
    <img src="../assets/success.jpg" alt="">
    <div class="complete"><span>已完成支付</span></div>
    <div class="to-order-page">
      前往
      <el-link type="primary" class="link" href="/#/usercenter/orders?status=unused">我的订单</el-link>
      查看
    </div>
  </div>
</template>

<script>
export default {
  name: "PaymentSuccess",
  
}
</script>

<style scoped>
.success-container {
  background-color:rgba(96, 241, 23, 0.2);
  width: 100%;
  height: 400px;
  text-align: center;
  padding-top: 50px;
};

.success-container .complete {
  margin-top: 30px;
  overflow: hidden;
}

.success-container .complete span{
  color: #4a9126;
  font-size: 20px;
  font-weight: bold;
}

.success-container .to-order-page {
  margin-top: 15px;
  vertical-align: text-bottom;
  line-height: 25px;
  font-size: 16px;
}

.success-container .to-order-page .link {
  font-size: 18px;
}
</style>