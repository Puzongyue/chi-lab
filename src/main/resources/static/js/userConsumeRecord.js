$(document).ready(function () {
    console.log("start")
    getConRecord()

    function getConRecord() {
        getRequest(
            '/consumeRecord/'+sessionStorage.getItem('id')+'/get',
            function (res) {
                console.log(res)
                renderConRecord(res.content)
            },
            function (err) {
                console.log(err)
                alert("fail")
            }
        )
    }

    function renderConRecord(recordList) {
        console.log(recordList)
        let accordRecord=''
        for (var i = 0; i < recordList.length; i++) {
            let tempSche =  recordList[i]
            console.log(tempSche.consumeTime.substring(0, 10) + ' ' + tempSche.consumeTime.substring(11, 16))
            let seat=''
            let seatList=tempSche.seat.split(" ")
            for (var j=1;j<seatList.length;j++){
                seat+='<div class="order-ticket">'+seatList[j]+'</div>'
            }
        seat='<div class="ticket-list col-md-8 right">'+seat+'</div>'
            let tempA='<div class="col-md-4">' +
                '<div class="panel panel-default record-item ">'+
                '<div class="panel-heading"><h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#index' +i+ '"><span class="detail-title">消费时间：<span class="title-time">'+ tempSche.consumeTime.substring(0, 10) + ' ' + tempSche.consumeTime.substring(11, 16)+
                '</span></span></a></h4></div>' +
                '<div id="index'+i+'"class="panel-collapse collapse in" aria-expanded="false"><div class="panel-body"><div class="sche"> ' +
                '<div class="detail-name row"><div class="detail-title col-md-3 left">电影名：</div> ' +tempSche.filmName+'</div> '+
                '<div class="detail-hall row"><div class="detail-title col-md-3 left">影厅：</div>'+tempSche.hallName+'</div>' +
                '<div class="detail-time row"><div class="detail-title col-md-3 left">放映时间：</div>'+ tempSche.begin.substring(0, 10) + ' ' + tempSche.begin.substring(11, 16)+' ~ '+ tempSche.end.substring(0, 10) + ' ' + tempSche.end.substring(11, 16)+'</div>' +
                '<div class="detail-ticket-list row"><div class="detail-title col-md-3 left">座位表：</div>'+seat+'</div>'+'</div>' +
                '<div class="consume">' +
                '<div class="detail-amount"><div class="detail-title col-md-3 left">消费金额：</div>'+tempSche.amount+'</div>' +
                '<div class="detail-way"><div class="detail-title col-md-3 left">消费方式：</div>'+(tempSche.way===0?'银行卡':'会员卡')+'</div> ' +
                '<div class="detail-time"><div class="detail-title col-md-3 left">消费时间：</div>'+ tempSche.consumeTime.substring(0, 10) + ' ' + tempSche.consumeTime.substring(11, 16) +'</div> ' +
                ' </div></div></div></div> </div>'

            accordRecord+=tempA

        }
        // accordRecord=+accordRecord
        $('.record-container').html(accordRecord)
    }
})