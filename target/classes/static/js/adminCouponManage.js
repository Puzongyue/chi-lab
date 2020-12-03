$(document).ready(function() {
    //选择是否显示root权限
    var role=sessionStorage.getItem('role')
    console.log(role)
    console.log(role==='root')
    if (role==='root'){
        $('#root-staff-manage').css("display","")
    }
    
    //得到优惠券列表
    getAllCoupon();

    
    function getAllCoupon() {
        getRequest(
            '/coupon/giveVIP/get',
            function (res) {
                console.log(res)
                if (res.success){
                    var couponList = res.content;
                    var couponListContent = '';
                    for (let coupon of couponList) {
                        couponListContent += '<div class="col-md-4 coupon-wrapper right"><input class="coupon-checkbox col-md-1" type="checkbox" name="select-coupon" id="couponid+' +
                            coupon.couponId +
                            '" value="' +coupon.couponId+
                            '" ><label for="couponid+' +coupon.couponId+
                            '">' +
                            '<div class="coupon"><div class="content">' +
                            '<div class="col-md-8 left">' +
                            '<div class="name">' +
                            coupon.name +
                            '</div>' +
                            '<div class="description">' +
                            coupon.description +
                            '</div>' +
                            '<div class="price">' +
                            '满' + coupon.targetAmount + '减' + coupon.discountAmount +
                            '</div>' +
                            '</div>' +
                            '<div class="col-md-5 right">' +
                            '<div>有效日期</div>' +
                            '<div>' + formatDate(coupon.startTime) + ' ~ ' + formatDate(coupon.endTime) + '</div>' +
                            '</div></div></div></label></input></div>'
                    }

                    $('#all-coupon-list').append(couponListContent);
                }
            },
            function (err) {
                alert(JSON.stringify(err))
            }
        )
    }
    function formatDate(date) {
        return date.substring(5, 10).replace("-", ".");
    }
});



function submitPro() {
    getCouponIdList()
}
//得到被选中的优惠券
function getCouponIdList() {
    var list=$("input[name='select-coupon']:checked")
    var couponId=[]
    // console.log(!list)
    if (list==="a"){
        alert("请选择要赠送的优惠券")
    }
    else {
    list.each(function(){
        var cur=$(this).val()
        // console.log(cur)
        // console.log("*******************************")
        couponId.push(cur)
    })
    return couponId
    }
}
