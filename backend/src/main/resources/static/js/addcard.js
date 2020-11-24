$(document).ready(function () {


    //选择是否显示root权限
    var role=sessionStorage.getItem('role')
    console.log(role)
    console.log(role==='root')
    if (role==='root'){
        $('#root-staff-manage').css("display","")
    }


    getAllCard();

    //得到全部管理员
    function getAllCard() {
        getRequest(
            '/vipActivity/get',
            function (res) {
                if (res.success){
                    console.log(res)
                    renderCardList(res.content)
                }
            },
            function (err) {
                alert(err)
            }
        )

    }

    function renderCardList(list) {
        // console.log("render")
        $('.card-table-body').empty()
        for (var i=0;i<list.length;i++){
            var card=list[i]
            var link='<button type="button" class="btn btn-primary edit-staff"data-backdrop="static" data-toggle="modal" data-target="#addcardedit"  data-card='+JSON.stringify(card) +'> 编辑</button>'
            var line=$('<tr>'+'<td>'+card.costInNeed+'</td>'+'<td>'+card.bonusBalance+'</td>'+'<td>'+link+'</td>'+'</tr>')
            $("#card-table-body-in").append(line)
        }
    }



    $("#card-form-btn").click(function () {
        var card=getCardForm();
        if (validCardForm(card)){
            card.name="满减"
            card.decription="满减策略"
            postRequest(
                '/vipActivity/publish',
                card
                ,
                function (res) {
                    // console.log(res)
                    if (res.message){
                        alert(res.message)
                    }
                    else {
                        alert("添加成功")
                        getAllCard()
                        $('#addcard').modal('hide')
                    }
                },
                function (err) {
                    alert(err.message)
                }
            )
        }

        else {
            return;
        }

    })

    //编辑按钮
    //显示编辑框
    $(document).on('click','.edit-staff',function (e) {
        var card=JSON.parse(e.target.dataset.card)
        // console.log(user)
        $('#VIPcard-money-edit-input').val(card.costInNeed);
        $('#discount-edit-input').val(card.bonusBalance);

        $('#card-form-edit-btn').attr("data-id",card.id)
    })

    // 点击确认按钮
    $("#card-form-edit-btn").click(function () {
        var card=getCardForm()
        if (getCardForm(card)){
            card.name="满减"
            card.decription="满减策略"
            card.id=$('#card-form-edit-btn').attr("data-id")
            postRequest(
                '/vipActivity/update',
                card
                ,
                function (res) {
                    // console.log(res)
                    if (res.message){
                        alert(res.message)
                    }
                    else {
                        alert("修改成功")
                        getAllCard()
                        $('#addcardedit').modal('hide')
                    }
                },
                function (err) {
                    alert(JSON.stringify(err))
                }
            )
        }

        else {
            return;
        }

    });


    function getCardForm() {
        return{
            costInNeed:$("#VIPcard-money-input").val()||$('#VIPcard-money-edit-input').val(),
            bonusBalance:$("#discount-input").val()||$('#discount-edit-input').val()
        }
    }

    function validCardForm(card) {
        var isValidUser=true
        if (!card.costInNeed){
            isValidUser=false
            alert("请输入正确的消费金额")
        }
        if (!card.bonusBalance){
            isValidUser=false
            alert("请输入正确的满减金额")

        }
            return isValidUser
    }
    //隐藏modal执行清空
    $('#addcard').on('hide.bs.modal', function () {
        $("#VIPcard-money-input").val('')
        $("#discount-input").val('')
    })

    $('#addcardedit').on('hide.bs.modal', function () {
        $("#VIPcard-money-edit-input").val('')
        $("#discount-edit-input").val('')
    })


});
