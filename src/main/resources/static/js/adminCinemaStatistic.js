$(document).ready(function() {
    //选择是否显示root权限
    var role=sessionStorage.getItem('role')
    console.log(role)
    console.log(role==='root')
    if (role==='root'){
        $('#root-staff-manage').css("display","")
    }
    console.log("start")
    getScheduleRate();
    console.log("sr")

    getBoxOffice();
    console.log("bo")

    getAudiencePrice();
    console.log("ap")

    getPlacingRate();
    console.log("pr")

    getPopularMovie();
    console.log("pm")

    console.log("stac")

    function getScheduleRate() {

        getRequest(
            '/statistics/scheduleRate',
            function (res) {
                var data = res.content||[];
                var tableData = data.map(function (item) {
                   return {
                       value: item.time,
                       name: item.name
                   };
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title : {
                        text: '今日排片率',
                        subtext: new Date().toLocaleDateString(),
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        x : 'center',
                        y : 'bottom',
                        data:nameList
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie', 'funnel']
                            },
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    series : [
                        {
                            name:'面积模式',
                            type:'pie',
                            radius : [30, 110],
                            center : ['50%', '50%'],
                            roseType : 'area',
                            data:tableData
                        }
                    ]
                };
                var scheduleRateChart = echarts.init($("#schedule-rate-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getBoxOffice() {
        console.log("asdfhj")
        getRequest(
            '/statistics/boxOffice/total',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
//                console.log(item)
                    return item.boxOffice;
                });
                var nameList = data.map(function (item) {
//                console.log(item)
                    return item.name;
                });
                var option = {
                    title : {
                        text: '所有票房',
                        subtext: '截止至'+new Date().toLocaleDateString(),
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'bar'
                    }]
                };
                var scheduleRateChart = echarts.init($("#box-office-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function getAudiencePrice() {
        console.log("sdffwqe")
        getRequest(
            '/statistics/audience/price',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
//console.log(item)
                     return item.price;

                });
                var nameList = data.map(function (item) {
                    return formatDate(new Date(item.date));
                });
                var option = {
                    title : {
                        text: '每日客单价',
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'line'
                    }]
                };
                var scheduleRateChart = echarts.init($("#audience-price-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });

    }

    function getPlacingRate() {
        // todo
        var date=new Date().toLocaleDateString();
        getRequest(
            '/statistics/PlacingRate?date=' + date,
            function (res) {
                        var data = res.content || [];
                        var tableData = data.map(function (item) {
                             return item.placingRate;
                        });
                        var nameList = data.map(function (item) {
                            return item.name;
                        });
                        var option = {
                            title : {
                               text: '每日上座率',
                                x:'center'
                            },
                            xAxis: {
                                type: 'category',
                                data: nameList
                            },
                            yAxis: {
                                type: 'value'
                            },
                            series: [{
                                data: tableData,
                                type: 'line'
                            }]
                        };
                        var scheduleRateChart = echarts.init($("#place-rate-container")[0]);
                        scheduleRateChart.setOption(option);
                    },
                    function (error) {
                        alert(JSON.stringify(error));
                    });

    }

    function getPopularMovie() {
//                var date=new Date().toLocaleDateString();
                   console.log("进去function")
                   getRequest(
                           '/statistics/popular/movie?days=1&movieNum=9',
                           //这个是报错呢还是进入的不是下面这个function而是进了err那个？
                           //但是很玄幻的是他也没有alert
                               function (res) {
                                                   console.log("get成功")

                                            console.log(res)
                                           var data = res.content || [];
                                           var tableData = data.map(function (item) {
                                                console.log(item)
                                                return item.movieId;
                                           });
                                           var nameList = data.map(function (item) {
                                               return item.name;
                                           });
                                           var option = {
                                               title : {
                                                  text: '最受欢迎电影排行',
                                                   x:'center'
                                               },
                                               xAxis: {
                                                   type: 'category',
                                                   data: nameList
                                               },
                                               yAxis: {
                                                   type: 'value'
                                               },
                                               series: [{
                                                   data: tableData,
                                                   type: 'bar'
                                               }]
                                           };
                                           var scheduleRateChart = echarts.init($("#popular-movie-container")[0]);
                                           scheduleRateChart.setOption(option);
                                       },
                                       function (error) {
                                        console.log("get失败")

                                           alert(JSON.stringify(error));
                                       });


    }
        // todo
//
});