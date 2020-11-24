$(document).ready(function(){
    getUsedTicket();

// 得到列表
    function getUsedTicket() {
        // 发请求得到全部电影票
        getRequest(
            '/ticket/get/refundedTickets/' + sessionStorage.getItem('id'),
            function (res) {
                console.log(res.content)
                if (res.content){
                    renderUsedTicket(res.content);
                }

            },
            function (error) {
                alert(error);
            });
        // console.log("end")

// 遍历：
// 1. 是否过期（结束时间<当前时间），过期被选中
// 2. 分类，按照排片id分成不同的list
// 填进去
    }
});

function renderUsedTicket(originList) {
    // console.log(originList)


    // 按照排片分类
    let resList=getSortedList(originList)
    // console.log(resList)

    // 按照时间筛一下
    // let a = renderOrder(resList,getValidSche(resList))
    let a=getValidSche(resList,renderOrder)

    console .log(a)
    //开始填数据

}

function getSortedList(originList) {
    let resList = []
    var sche = {}

    for (var i = 0; i < originList.length; i++) {
        let ticket = originList[i]
            if (!sche[ticket.scheduleId]) {
                resList.push({
                    scheId: ticket.scheduleId,
                    tList: [ticket],
                })
                sche[ticket.scheduleId] = ticket
            } else {
                for (var j = 0; j < resList.length; j++) {
                    let ele = resList[j]
                    if (ele.scheId == ticket.scheduleId) {
                        ele.tList.push(ticket)
                        break
                    }
                }

        }
    }
    return resList
}

function getValidSche(scheList,callback) {
    for (let i=0;i<scheList.length;i++){
        let schedule=scheList[i]
        getRequest(
            "/schedule/" + schedule.scheId,
            function (res) {
                let tempSche = res.content
                    schedule.schedule=tempSche
                if (i==scheList.length-1){
                    return callback(scheList)
                }
            }
        )
    }
}

var renderOrder=function (finList) {
    let orderStr=""
    finList.forEach(function (order) {
        console.log(order)
        //判断是否过期
        if (order.scheId!=null){
            let ordItemStr=''
            //形成座位
            let seatList=''
            order.tList.forEach(function (ticket) {
                seatList+='<div class="order-ticket">'+(ticket.rowIndex + 1) + "排" + (ticket.columnIndex + 1) + "座" +'</div>'
            })
            seatList='<div class="ticket-item col-md-8 right">'+seatList+'</div>'
            let curSchedule=order.schedule
            ordItemStr='<div class="order-item col-md-4"><div class="order-item-inside"><div class="order-basic-title"><span class="name">'+
                curSchedule.movieName+'</span><span class="hall">'+
                curSchedule.hallName+'</span><span class="startTime">'+
                curSchedule.startTime.substring(0, 10) + ' ' + curSchedule.startTime.substring(11, 16)+'</span></div><div class="order-content"><div class="ticket-list"><div class="ticket-title col-md-4 left">已选择座位：</div>'+
                seatList+'</div><div class="order-operate"></div></div></div></div>'
            orderStr+=ordItemStr
        }
    })

    $('.order-container').html(orderStr)

    return true;
}


