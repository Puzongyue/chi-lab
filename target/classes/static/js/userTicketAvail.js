$(document).ready(function(){
    getUsedTicket();

// 得到列表

});

//ready外
function getUsedTicket() {
    // 发请求得到全部电影票
    getRequest(
        '/ticket/get/' + sessionStorage.getItem('id'),
        function (res) {
            // console.log(res)
            renderAvailTicket(res.content);
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

function renderAvailTicket(originList) {
    // console.log(originList)


    // 按照排片分类
    let resList=getSortedList(originList)
    // console.log(resList)

    // 按照时间筛一下
    // let a = renderOrder(resList,getValidSche(resList))
    let a=getValidSche(resList,renderOrder)
    //开始填数据

}

function getSortedList(originList) {
    let resList = []
    var sche = {}

    for (var i = 0; i < originList.length; i++) {
        let ticket = originList[i]
        if (ticket.state === "已完成") {
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
    }
    return resList
}

function getValidSche(scheList,callback) {
    let cur=new Date()
    for (let i=0;i<scheList.length;i++){
        let schedule=scheList[i]
        getRequest(
            "/schedule/" + schedule.scheId,
            function (res) {
                let tempSche = res.content
                if (isBefore(cur,new Date(tempSche.endTime))) {
                    schedule.schedule=tempSche
                }
                else {
                    schedule.scheId=null
                }
                if (i==scheList.length-1){
                    return callback(scheList)
                }
            }
        )
    }
}

//TODO
var renderOrder=function (finList) {
    let orderStr=""
    finList.forEach(function (order) {
        console.log(order)
        //判断是否过期
        if (order.scheId!=null){
            let ordItemStr=''
            //形成座位
            let seatList=''
            let tStrList=[]
            order.tList.forEach(function (ticket) {
                seatList+='<div class="order-ticket">'+(ticket.rowIndex + 1) + "排" + (ticket.columnIndex + 1) + "座" +'</div>'
                var ticketOb={
                    id:ticket.id,
                    seat:(ticket.rowIndex + 1) + "排" + (ticket.columnIndex + 1) + "座",
                }
                console.log(ticketOb)
                tStrList.push(JSON.stringify(ticketOb))
            })
            seatList='<div class="ticket-item col-md-8 right">'+seatList+'</div>'

            let curSchedule=order.schedule
            ordItemStr='<div class="order-item col-md-4"><div class="order-item-inside"><div class="order-basic-title"><span class="name">'+
                curSchedule.movieName+'</span><span class="hall">'+
                curSchedule.hallName+'</span><span class="startTime">'+
                curSchedule.startTime.substring(0, 10) + ' ' + curSchedule.startTime.substring(11, 16)+'</span></div><div class="order-content"><div class="ticket-list"><div class="ticket-title col-md-4 left">已选择座位：</div>'+
                seatList+'</div><div class="order-operate"><button type="button" class="btn btn-primary refund-ticket"data-backdrop="static" data-toggle="modal" data-target="#refundTicket" data-name="'+curSchedule.movieName+'" data-hall="'+curSchedule.hallName
                +'" data-time="' + curSchedule.startTime+'" data-ticl='+JSON.stringify(tStrList)+' >退票</button></div></div></div></div>'
            orderStr+=ordItemStr
        }
    })

    $('.order-container').html(orderStr)

    return true;
}

function isBefore(first,second) {
    return first<second
}


//渲染模态框时判断有无策略
// $('.refund-modal').on('show.bs.modal', function () {
//     console.log("aaaaaaaaaaaaa")
//     getRequest(
//         '/ticket/get/refundStrategy',
//         function (res) {
//             console.log(res)
//             if (res.success) {
//                 $('.refund-stra').html(res.content)
//             }
//             else {
//                 alert(res.message)
//                 $('#refundTicket').modal("hide")
//             }
//         },
//         function (err) {
//             console.log(err)
//
//         }
//     )
// })


//点击退票按钮
$(document).on('click','.refund-ticket',function (e) {
    let data=e.target.dataset
    //填入数据基本信息
    let basicMsg= '<div class="refund-content-name">' +data.name+'</div><div class="refund-content-hall">'+data.hall+'</div><div class="refund-content-time">'+ data.time.substring(0, 10) + ' ' + data.time.substring(11, 16)+'</div>'
    $('.refund-basic-info-content').html(basicMsg)
    let list=JSON.parse(data.ticl)

    let ticketList=''
    for (var j=0;j<list.length;j++){
        var ticket=JSON.parse(list[j])
        let tempTic='<div class="form-group col-md-3 clearfix"><input  class="refund-ticket-checkbox col-md-4" type="checkbox" name="select-ticket" id="couponid'+ticket.id+'" value="'+ticket.id+'"><label class="order-ticket" for="couponid'+ticket.id+'">'+ticket.seat+'</label></input></div>'
        ticketList+=tempTic
    }
    ticketList='<form class="form-horizontal list-form clearfix" role="form">'+ticketList+'</form>'
    $('.ticket-list-form').html(ticketList)
    getRefundStrategy()
})

//得到退票策略

function getRefundStrategy() {
    getRequest(
        '/refund/get',
        function (res) {
            // console.log(res)
            if (res.success) {
                renderRefundList(res.content)
            }
            else {
                alert(res.message)
            }
        },
        function (err) {
            console.log(err)

        }
    )
}

function renderRefundList(list) {
    // console.log("render")
    $('.refund-table-body').empty()
    for (var i=0;i<list.length;i++){
        var stra=list[i]
        var temp=getTime(stra.availableTime)
        stra.day=temp.day
        stra.hour=temp.hour
        stra.min=temp.min
        stra.sec=temp.sec
        // console.log(stra)
        var line=$('<tr>'+'<td>'+stra.day+"天"+stra.hour+"小时"+stra.min+"分钟"+stra.sec+"秒"+'</td>'+'<td>'+stra.refundPercentage+'</td>'+'</tr>')
        $("#refund-table-body-in").append(line)
    }
}

function getTime(second) {
    var day=parseInt(second/86400)
    var temp=second%86400
    var hour=parseInt(temp/3600)
    temp=temp%3600
    // console.log(temp)
    var min=parseInt(temp/60)
    var sec=temp%60
    return {
        day:day,
        hour:hour,
        min:min,
        sec:sec
    }
}
//点击确认
function startRefund() {
        var r=confirm("确认退票？")
        if (r){
            let ticketId=getTicketList()
            console.log(ticketId)
            deleteRequest(
                '/ticket/delete?ticketId='+ticketId,
                null,
                function (res) {
                    console.log(res)
                    if (res.success){
                        console.log("退票成功")
                    }
                    else {
                        console.log(res.content)
                    }
                    $('#refundTicket').modal("hide")
                    //刷新
                    getUsedTicket();
                },
                function (err) {
                    console.log(err)
                }
            )
        }

}

//得到被选中的票的id
function getTicketList() {
    var list=$("input[name='select-ticket']:checked")
    var ticketList=[]
    // console.log(list)
    if (list.length==0){
        alert("请选择要退的票")
    }
    else {
        list.each(function(){
            var cur=$(this).val()
            console.log(cur)
            console.log("*******************************")
            ticketList.push(cur)
        })
        return ticketList
    }
}