$(document).ready(function () {

    getAllStaff();

    //得到全部管理员
    function getAllStaff() {
        getRequest(
            '/accountOfManager/get/all',
            function (res) {
                if (res.success){
                    // $('#staff-line').remove()
                    // console.log(res)
                    renderStaffList(res.content)
                }

            }
        )
        
    }
    
    function renderStaffList(list) {
        // console.log("render")
        $('.staff-table-body').empty()
        for (var i=0;i<list.length;i++){
            var user=list[i]
            // var userStr=JSON.stringify(user)
            //root不能被删除
            var link=""
            if (user.username==="root"){
                link='<button type="button" class="btn btn-primary edit-staff"data-backdrop="static" data-toggle="modal" data-target="#editStaff"  data-user='+JSON.stringify(user) +'> 编辑</button>'

            }
            else {
                link='<button type="button" class="btn btn-primary edit-staff"data-backdrop="static" data-toggle="modal" data-target="#editStaff"  data-user='+JSON.stringify(user) +'> 编辑</button>'+' <button type="button" class="btn btn-primary deleteStaff"  id="delete-staff" data-name='+user.username+'> 删除</button>'

            }
            var line=$('<tr>'+'<td>'+user.username+'</td>'+'<td>'+user.password+'</td>'+'<td>'+link+'</td>'+'</tr>')
            $("#staff-table-body-in").append(line)
        }
    }



    $("#card-form-btn").click(function () {
        var user=getUserForm();
        if (validUserForm(user)){
            postRequest(
                '/accountOfManager/add',
                user
                ,
                function (res) {
                    // console.log(res)
                    if (res.message){
                        alert(res.message)
                    }
                    else {
                        alert("添加成功")
                        getAllStaff()
                        $(':text').val('')
                        $('#addStaff').modal('hide')
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
        var user=JSON.parse(e.target.dataset.user)
        // console.log(user)
        $('#staff-edit-username-input').val(user.username);
        $('#staff-edit-password-input').val(user.password);
        $('#staff-edit-btn').attr("data-oldname",user.username)
        if (user.username==="root"){
            $('#staff-edit-username-input').attr("readonly",true)
        }
        else {
            $('#staff-edit-username-input').attr("readonly",false)
        }
    })
    // 点击确认按钮
    $(".editStaff").click(function () {
        var user=getUserForm();
        console.log("GET FORM")
        console.log(user)
        if (validUserForm(user)){
            user.oldname=$("#staff-edit-btn").attr("data-oldname")
            console.log("edit")
            console.log(user)
            postRequest(
                '/accountOfManager/update',
                user
                ,
                function (res) {
                    // console.log(res)
                    if (res.message){
                        alert(res.message)
                    }
                    else {
                        alert("修改成功")
                        getAllStaff()
                        $('#editStaff').modal('hide')
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

    })

    //删除按钮

    $(document).on('click','.deleteStaff',function (e) {
        var r=confirm("确认删除本管理员？")
        if (r){
            var name=e.target.dataset.name
           deleteRequest(
               '/accountOfManager/delete?name='+name,
               null,
               function (res) {
                   if (res.message){
                       alert(res.message)
                   }
                   else {
                       alert("已删除")
                       getAllStaff()
                   }
               },
               function (err) {
                   alert(JSON.stringify(err))
               }
           )
        }
    })


    function getUserForm() {
        return{
            username:$("#staff-username-input").val()||$('#staff-edit-username-input').val(),
            password:$("#staff-password-input").val()||$('#staff-edit-password-input').val()
        }
    }

    function validUserForm(user) {
        var isValidUser=true
        if (!user.username){
            isValidUser=false
            $('#staff-username-input').parent('.form-group').addClass('has-error');
        }
        if (!user.password){
            isValidUser=false
            $('#staff-password-input').parent('.form-group').addClass('has-error');
        }
        if (user.password)
        // console.log(isValidUser)
        return isValidUser
    }
    //隐藏modal执行清空
    $('#addStaff').on('hide.bs.modal', function () {
        $(':text').val('')
    })

    //密码表单验证


});