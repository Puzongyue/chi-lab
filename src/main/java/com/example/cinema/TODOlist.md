#前端
1. adminScheduleManage.js文件
$('#schedule-form-btn').click()、
$('#schedule-edit-form-btn').click()方法
排片输入校验

2. movieDetail.js文件
$("#modify-btn").click()【修改也需要编写对应表单】
$("#delete-btn”).click()方法
下架、修改电影

3. adminCinema.js文件
getPlacingRate()、
getPolularMovie()方法
剩余两个统计图表【上座率和受欢迎电影】

4. userMovieBuy.js中对应后端1到6的相应方法（即这个文件中的所有方法都可以挖掉）

#后端
1. //锁座
ResponseVO addTicket(TicketForm ticketForm);
2. //普通购票
ResponseVO completeTicket(List<Integer> id, int couponId);
3. //使用会员卡购票
ResponseVO completeByVIPCard(List<Integer> id,int couponId);
4. //取消锁座
ResponseVO cancelTicket(List<Integer> id);
5. //获得用户购买的票
ResponseVO getTicketByUser(int userId);
6. //获得已被占的座位
ResponseVO getBySchedule(int scheduleId);

7. 受欢迎电影：
   7.1 StatisticsServiceImpl文件中的getPopularMovies方法（此方法中运用到的相应的操作数据库的接口和实现请自行定义和实现，相应的结果需要自己定义一个VO类返回给前端）
8. 上座率
   8.1 StatisticsServiceImpl文件中的getMoviePlacingRateByDate方法（此方法中运用到的相应的操作数据库的接口和实现请自行定义和实现，相应的结果需要自己定义一个VO类返回给前端）