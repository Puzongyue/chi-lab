$(document).ready(function () {
    console.log("start")
    getChaRecord()

    function getChaRecord() {
        getRequest(
            '/chargeRecord/'+sessionStorage.getItem('id')+'/get',
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
            // console.log(tempSche.consumeTime.substring(0, 10) + ' ' + tempSche.consumeTime.substring(11, 16))
            let tempA='<div class="col-md-4">'+
                '<div class="panel panel-default record-item">' +
                '<div class="panel-heading"><h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#index' +i+ '"><span class="detail-title">消费时间：<span class="title-time">'+ tempSche.chargeTime.substring(0, 10) + ' ' + tempSche.chargeTime.substring(11, 16)+
                '</span></span></a></h4></div>' +
                '<div id="index'+i+'"class="panel-collapse collapse in" aria-expanded="false"><div class="panel-body">' +
                '<div class="consume">' +
                '<div class="detail-activity"><div class="detail-title col-md-3 left">充值活动：</div>'+tempSche.vipactivity+'</div> ' +
                '<div class="detail-amount"><div class="detail-title col-md-3 left">充值金额：</div>'+tempSche.amount+'</div>' +
                '<div class="detail-givenAmo"><div class="detail-title col-md-3 left">赠送金额：</div>'+tempSche.givenAmount+'</div> ' +
                '<div class="detail-time"><div class="detail-title col-md-3 left">充值时间：</div>'+ tempSche.chargeTime.substring(0, 10) + ' ' + tempSche.chargeTime.substring(11, 16) +'</div> ' +
                ' </div></div></div></div></div>'

            accordRecord+=tempA

        }
        // accordRecord=+accordRecord
        $('.record-container').html(accordRecord)
    }
})