$(document).ready(function () {

    console.log("afdfasd")

    $("#login-btn").click(function () {
        var formData = getLoginForm();
        console.log(formData+'data')
        if (!validateLoginForm(formData)) {
            return;
        }

        postRequest(
            '/accountOfManager/login',
            formData,
            function (res) {
                console.log(res+"success")
                if (res.success) {
                    sessionStorage.setItem('adminname', formData.username);
                    sessionStorage.setItem('id', res.content.managerID);
                    if (formData.username == "root") {
                        sessionStorage.setItem('role', 'root');
                    }
                    else
                    {
                        sessionStorage.setItem('role', 'admin');
                    }
                    window.location.href = "/admin/movie/manage"
                }
                else {
                    console.log(res+"fail")
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            });
    });

    function getLoginForm() {
        return {
            username: $('#index-name').val(),
            password: $('#index-password').val()
        };
    }

    function validateLoginForm(data) {
        var isValidate = true;
        if (!data.username) {
            isValidate = false;
            $('#index-name').parent('.input-group').addClass('has-error');
            $('#index-name-error').css("visibility", "visible");
        }
        if (!data.password) {
            isValidate = false;
            $('#index-password').parent('.input-group').addClass('has-error');
            $('#index-password-error').css("visibility", "visible");
        }
        return isValidate;
    }


});