$(document).ready(function () {
    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
        /*var test = [
            {
                id:11,
                userId:15,
                scheduleId:75,
                columnIndex:3,
                rowIndex:5,
                state:"已完成"
            },
            {
                id:11,
                userId:15,
                scheduleId:75,
                columnIndex:5,
                rowIndex:5,
                state:"未完成"
            }
        ];
        renderTicketList(test);*/
    }

    // TODO:填空
    //DingLingyan 2019/5/15
    function renderTicketList(list) {
        if (list.length == 0){
            $('#no-ticket-tip').css('display','block');
        }
        else {
            $('#no-ticket-tip').css('display','none');
            list.forEach(function (ticket) {
                var scheduleId = ticket.scheduleId;    //获取电影名&影厅&放映时间
                var column = ticket.columnIndex + 1;
                var row = ticket.rowIndex + 1;
                var state = ticket.state;

                if (state == "未完成") {
                    state = "已失效";
                }

                //通过ScheduleId调用后端的getScheduleById方法获取ScheduleItemVO
                getRequest(
                    '/schedule/' + scheduleId,
                    function (res) {
                        if (res.success) {
                            //console.log(res.content);
                            //renderTicketItem(res.content);
                            var scheduleItem = res.content;
                            var movieName = scheduleItem.movieName;
                            var hallName = scheduleItem.hallName;
                            var startTime = new Date(scheduleItem.startTime);
                            var endTime = new Date(scheduleItem.endTime);
                            var html = '';

                            html +=
                                '<tr>' +
                                '<td class="item-info">' + movieName + '</td>' +
                                '<td class="item-info">' + hallName + '</td>' +
                                '<td class="item-info">' + row + '排' + column + '座' + '</td>' +
                                '<td class="item-info">' + formatDate(startTime) + '</td>' +
                                '<td class="item-info">' + formatDate(endTime) + '</td>' +
                                '<td class="item-info">' + state + '</td>' +
                                '</tr>';

                            $('#ticket-detail').append(html);
                        }
                        else {
                            alert(JSON.stringify(res.message));
                        }
                    },
                    function (error) {
                        alert(JSON.stringify(res.message));
                    }
                );
            })
        }
    }
});



function formatDate(myDate) {
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    var hour = myDate.getHours();
    var minute = myDate.getMinutes();
    var second = myDate.getSeconds();

    month = (month < 10) ? ("0" + month) : month;
    day = (day < 10) ? ("0" + day) : day;
    hour = (hour < 10) ? ("0" + hour) : hour;
    minute = (minute < 10) ? ("0" + minute) : minute;
    second = (second < 10) ? ("0" + second) : second;

    return myDate.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
}
