var selectedSeats = []
var scheduleId;
var order = {ticketId: [], couponId: 0};
var orderInfo={}
var ticketPrice;
var coupons = [];
var isVIP = false;
var useVIP = true;


//
$(document).ready(function () {
    console.log(window.location.href.split('?')[1])
    scheduleId = parseInt(window.location.href.split('?')[1].split('&')[1].split('=')[1]);

    let isUnpay=window.location.href.split('?')[1].split('&')[2].split('=')[1];
    console.log(isUnpay==='false')
    //再次支付
    if (isUnpay==='true'){
        $('#seat-state').css("display", "none");
        $('#order-state').css("display", "");

        var  info=window.location.href.split('?')[1].split('&');

        $('#order-movie-name').html(decodeURIComponent(info[3]));
        $('#order-schedule-hall-name').html(decodeURIComponent(info[4]));
        $('#order-schedule-time').html(decodeURIComponent(info[5]));


        //得到order
        getRequest(
            '/ticket/get/info/unpaid?userId='+sessionStorage.getItem('id')+'&scheduleId='+scheduleId,
            function (res) {
                console.log(res)
                if (res.success){
                    renderOrder(res.content)
                    getRequest(
                        '/vip/' + sessionStorage.getItem('id') + '/get',
                        function (res) {
                            isVIP = res.success;
                            useVIP = res.success;
                            if (isVIP) {
                                $('#member-balance').html("<div><b>会员卡余额：</b>" + res.content.balance.toFixed(2) + "元</div>");
                            } else {
                                $("#member-pay").css("display", "none");
                                $("#nonmember-pay").addClass("active");

                                $("#modal-body-member").css("display", "none");
                                $("#modal-body-nonmember").css("display", "");
                            }
                        },
                        function (error) {
                            alert(error);
                        });
                }
                else {
                    console.log(res.message)
                }
            },
            function (err) {
                alert(err)
            }
        )


    }
    else {
        getInfo();
    }


    function getInfo() {
        getRequest(
            '/ticket/get/occupiedSeats?scheduleId=' + scheduleId,
            function (res) {
                console.log(res)
                if (res.success) {
                    renderSchedule(res.content.scheduleItem, res.content.seats);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }
});


//填充排片信息
function renderSchedule(schedule, seats) {
    //影厅名
    $('#schedule-hall-name').text(schedule.hallName);
    $('#order-schedule-hall-name').text(schedule.hallName);
    ticketPrice=schedule.fare.toFixed(2);
    //票價
    $('#schedule-fare').text(schedule.fare.toFixed(2));
    //訂單票價
    $('#order-schedule-fare').text(schedule.fare.toFixed(2));

    $('#schedule-time').text(schedule.startTime.substring(5, 7) + "月" + schedule.startTime.substring(8, 10) + "日 " + schedule.startTime.substring(11, 16) + "场");
    $('#order-schedule-time').text(schedule.startTime.substring(5, 7) + "月" + schedule.startTime.substring(8, 10) + "日 " + schedule.startTime.substring(11, 16) + "场");

    var hallDomStr = "";
    var seat = "";
    for (var i = 0; i < seats.length; i++) {
        var temp = ""
        for (var j = 0; j < seats[i].length; j++) {
            var id = "seat" + i + j

            if (seats[i][j] == 0) {
                // 未选
                temp += "<button class='cinema-hall-seat-choose' id='" + id + "' onclick='seatClick(\"" + id + "\"," + i + "," + j + ")'></button>";
            } else {
                // 已选中
                temp += "<button class='cinema-hall-seat-lock'></button>";
            }
        }
        seat += "<div>" + temp + "</div>";
    }
    var hallDom =
        "<div class='cinema-hall'>" +
        "<div>" +
        "<span class='cinema-hall-name'>" + schedule.hallName + "</span>" +
        "<span class='cinema-hall-size'>" + seats.length + '*' + seats[0].length + "</span>" +
        "</div>" +
        "<div class='cinema-seat'>" + seat +
        "</div>" +
        "</div>";
    hallDomStr += hallDom;

    $('#hall-card').html(hallDomStr);
}


//
function seatClick(id, i, j) {
    let seat = $('#' + id);
    if (seat.hasClass("cinema-hall-seat-choose")) {
        seat.removeClass("cinema-hall-seat-choose");
        seat.addClass("cinema-hall-seat");

        selectedSeats[selectedSeats.length] = [i, j]
    } else {
        seat.removeClass("cinema-hall-seat");
        seat.addClass("cinema-hall-seat-choose");

        selectedSeats = selectedSeats.filter(function (value) {
            return value[0] != i || value[1] != j;
        })
    }

    selectedSeats.sort(function (x, y) {
        var res = x[0] - y[0];
        return res === 0 ? x[1] - y[1] : res;
    });

    let seatDetailStr = "";
    if (selectedSeats.length == 0) {
        seatDetailStr += "还未选择座位"
        $('#order-confirm-btn').attr("disabled", "disabled")
    } else {
        for (let seatLoc of selectedSeats) {
            seatDetailStr += "<span>" + (seatLoc[0] + 1) + "排" + (seatLoc[1] + 1) + "座</span>";
        }
        $('#order-confirm-btn').removeAttr("disabled");
    }
    $('#seat-detail').html(seatDetailStr);
}

function orderConfirmClick() {
    $('#seat-state').css("display", "none");
    $('#order-state').css("display", "");
    //開始鎖座
    let seats=[];
    selectedSeats.forEach(function (seat) {
        seats.push({
            columnIndex:seat[1],
            rowIndex:seat[0]
        })
    });
    console.log("可以锁座")
    postRequest(
        '/ticket/lockSeat',
        {
            userId:sessionStorage.getItem('id'),
            scheduleId:scheduleId,
            seats:seats
        },
        function (res) {
            //锁座数量
            orderInfo=res.content
            orderInfo.total = (seats.length * ticketPrice).toFixed(2);
            //显示第二页面
            renderOrder(orderInfo);

            getRequest(
                '/vip/' + sessionStorage.getItem('id') + '/get',
                function (res) {
                    isVIP = res.success;
                    useVIP = res.success;
                    if (isVIP) {
                        $('#member-balance').html("<div><b>会员卡余额：</b>" + res.content.balance.toFixed(2) + "元</div>");
                    } else {
                        $("#member-pay").css("display", "none");
                        $("#nonmember-pay").addClass("active");

                        $("#modal-body-member").css("display", "none");
                        $("#modal-body-nonmember").css("display", "");
                    }
                },
                function (error) {
                    alert(error);
                });
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function switchPay(type) {
    useVIP = (type == 0);
    if (type == 0) {
        $("#member-pay").addClass("active");
        $("#nonmember-pay").removeClass("active");

        $("#modal-body-member").css("display", "");
        $("#modal-body-nonmember").css("display", "none");
    } else {
        $("#member-pay").removeClass("active");
        $("#nonmember-pay").addClass("active");

        $("#modal-body-member").css("display", "none");
        $("#modal-body-nonmember").css("display", "");
    }
}

function renderOrder(orderInfo) {
    console.log("^^^^^^^order")
    console.log(orderInfo)
    var ticketStr = "<div>" + orderInfo.ticketVOList.length + "张</div>";
    for (let ticketInfo of orderInfo.ticketVOList) {
        ticketStr += "<div>" + (ticketInfo.rowIndex + 1) + "排" + (ticketInfo.columnIndex + 1) + "座</div>";
        order.ticketId.push(ticketInfo.id);
    }
    $('#order-tickets').html(ticketStr);

    var total = orderInfo.total;
    $('#order-schedule-fare').text(Math.floor(total/orderInfo.ticketVOList.length))
    $('#order-total').text(total);
    $('#order-footer-total').text("总金额： ¥" + total);


    var couponTicketStr = "";
    coupons = orderInfo.coupons;
    if (coupons.length==0) {
        $('#order-discount').text("优惠金额：无");
        $('#order-actual-total').text(" ¥" + total);
        $('#pay-amount').html("<div><b>金额：</b>" + total + "元</div>");
    } else {
        for (let coupon of coupons) {
            couponTicketStr += "<option>满" + coupon.targetAmount + "减" + coupon.discountAmount + "</option>"
        }
        $('#order-coupons').html(couponTicketStr);
        changeCoupon(0);
    }
}

function changeCoupon(couponIndex) {
    order.couponId = coupons[couponIndex].id;
    $('#order-discount').text("优惠金额： ¥" + coupons[couponIndex].discountAmount.toFixed(2));
    var actualTotal = (parseFloat($('#order-total').text()) - parseFloat(coupons[couponIndex].discountAmount)).toFixed(2);
    $('#order-actual-total').text(" ¥" + actualTotal);
    $('#pay-amount').html("<div><b>金额：</b>" + actualTotal + "元</div>");
}

function payConfirmClick() {
    if (useVIP) {
        postPayRequest(true);
    } else {
        if (validateForm()) {
            if ($('#userBuy-cardNum').val() === "123123123" && $('#userBuy-cardPwd').val() === "123123") {
                postPayRequest(false);
            } else {
                alert("银行卡号或密码错误");
            }
        }
    }
}
// TODO:填空
function postPayRequest(isVIP) {
    // $('#order-state').css("display", "none");
    // $('#success-state').css("display", "");
    $('#buyModal').modal('hide')
    console.log(orderInfo)
    console.log(order)
    if(!isVIP){
        postRequest(
                '/ticket/buy?ticketId='+order.ticketId+'&couponId='+order.couponId,
                null,
                function (res) {
                    console.log('not',res)
                }
            );
    }
    else {
        postRequest(
            '/ticket/vip/buy?ticketId='+order.ticketId+'&couponId='+order.couponId,
            null,
            function (res) {
                console.log("aaaaaaaaaaaaa")
                console.log(res)
                if (!res.success){
                    alert(res.message+",请更换支付方式或给会员卡进行充值。未完成的订单在15分钟内，可至我的电影票-待付款完成。")
                    // $('#buyModal').modal('hide')
                    // $('#buyModal').modal('show')
                }
                else {
                    $('#order-state').css("display", "none");
                    $('#success-state').css("display", "");
                    $('#buyModal').modal('hide')
                }
            },
            function (err) {
                alert(err.message)
            }
        );
    }
}

function validateForm() {
    var isValidate = true;
    if (!$('#userBuy-cardNum').val()) {
        isValidate = false;
        $('#userBuy-cardNum').parent('.form-group').addClass('has-error');
        $('#userBuy-cardNum-error').css("visibility", "visible");
    }
    if (!$('#userBuy-cardPwd').val()) {
        isValidate = false;
        $('#userBuy-cardPwd').parent('.form-group').addClass('has-error');
        $('#userBuy-cardPwd-error').css("visibility", "visible");
    }
    return isValidate;
}

function getCookie(cname)
{
    var ss = document.cookie;
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0)
            return c.substring(name.length,c.length);
    }
    return "";
}