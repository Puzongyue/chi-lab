 $(document).ready(function () {
     var count=0
    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                // console.log(res)
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList(list) {
        console.log(list)
        for (var i = 0; i < list.length; i++) {
            let tempVO = list[i]
            if (tempVO.state == "未完成") {
                continue;
            }
            let state = ""
            let seat = ""
            seat = "<td>" + (tempVO.rowIndex + 1) + "排" + (tempVO.columnIndex + 1) + "座" + "</td>"
            state = "<td>" + tempVO.state + "</td>"
            // console.log("enter")
            // console.log(tempVO)
            getRequest(
                "/schedule/" + tempVO.scheduleId,
                function (res) {
                    // console.log("本次排片")
                    // console.log(res)
                    let tempSche = res.content
                    // console.log(new Date(tempSche.startTime))
                    // console.log(new Date(tempSche.startTime)>new Date())
                    let name = "<td>" + tempSche.movieName + "</td>"
                    let hallname = "<td>" + tempSche.hallName + "</td>"
                    let sTime = "<td>" + tempSche.startTime.substring(0, 10) + ' ' + tempSche.startTime.substring(11, 16) + "</td>"
                    let eTime = "<td>" + tempSche.endTime.substring(0, 10) + ' ' + tempSche.endTime.substring(11, 16) + "</td>"
                    let tempStr = "<tr>" + name + hallname + seat + sTime + eTime + state + "</tr>"
                    // console.log("得到每条数据的内容")
                    // console.log(tempStr)
                    $('#ticket-table-body-in').append(tempStr)
                }
            );
            // console.log("lalalalla")
            // console.log(sessionStorage.getItem(count))
        }
    }

});