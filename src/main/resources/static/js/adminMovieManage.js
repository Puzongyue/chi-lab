$(document).ready(function(){

    getMovieList();

    $("#movie-form-btn").click(function () {
        var formData = getMovieForm();
        if(!validateMovieForm(formData)) {
            return;
        }
        postRequest(
            '/movie/add',
            formData,
            function (res) {
                getMovieList();
                $("#movieModal").modal('hide');
            },
             function (error) {
                alert(error);
            });
    });

    $('#add-movie').click(function () {
        clearMovieForm();
    });

    //puzongyue 2019/5/15
    function clearMovieForm() {
        console.info(2);
        $('#movie-name-input').val('?????');
        console.info(3);
    }

    function getMovieForm() {
        return {
            name: $('#movie-name-input').val(),
            startDate: $('#movie-date-input').val(),
            posterUrl: $('#movie-img-input').val(),
            description: $('#movie-description-input').val(),
            type: $('#movie-type-input').val(),
            length: $('#movie-length-input').val(),
            country: $('#movie-country-input').val(),
            starring: $('#movie-star-input').val(),
            director: $('#movie-director-input').val(),
            screenWriter: $('#movie-writer-input').val(),
            language: $('#movie-language-input').val()
        };
    }
    //puzongyue 2019/5/15
    function validateMovieForm(data) {
        var isValidate = true;
        if(!data.name) {
            isValidate = false;
            $('#movie-name-input').parent('.form-group').addClass('has-error');
            $('#movie-name-error').css("visibility", "visible");
        }else{
            $('#movie-name-error').css("visibility", "hidden");
        }
        if(!data.posterUrl) {
            isValidate = false;
            $('#movie-img-input').parent('.form-group').addClass('has-error');
            $('#movie-img-error').css("visibility", "visible");
        }else{
            $('#movie-img-error').css("visibility", "hidden");
        }
        if(!data.startDate) {
            isValidate = false;
            $('#movie-date-input').parent('.form-group').addClass('has-error');
            $('#movie-date-error').css("visibility", "visible");
        }else{
            $('#movie-date-error').css("visibility", "hidden");
        }
        if(!data.description){
            isValidate = false;
            $('#movie-description-input').parent('.form-group').addClass('has-error');
            $('#movie-description-error').css("visibility", "visible");
        }else{
            $('#movie-description-error').css("visibility", "hidden");
        }
        if (!data.type){
            isValidate = false;
            $('#movie-type-input').parent('.form-group').addClass('has-error');
            $('#movie-type-error').css("visibility", "visible");
        }else{
            $('#movie-type-error').css("visibility", "hidden");
        }
        if (!data.country){
            isValidate = false;
            $('#movie-country-input').parent('.form-group').addClass('has-error');
            $('#movie-country-error').css("visibility", "visible");
        }else{
            $('#movie-country-error').css("visibility", "hidden");
        }
        if (!data.starring){
            isValidate = false;
            $('#movie-star-input').parent('.form-group').addClass('has-error');
            $('#movie-star-error').css("visibility", "visible");
        }else{
            $('#movie-star-error').css("visibility", "hidden");
        }
        if (!data.director){
            isValidate = false;
            $('#movie-director-input').parent('.form-group').addClass('has-error');
            $('#movie-director-error').css("visibility", "visible");
        }else{
            $('#movie-director-error').css("visibility", "hidden");
        }
        if (!data.screenWriter){
            isValidate = false;
            $('#movie-writer-input').parent('.form-group').addClass('has-error');
            $('#movie-writer-error').css("visibility", "visible");
        }else{
            $('#movie-writer-error').css("visibility", "hidden");
        }
        if (!data.language){
            isValidate = false;
            $('#movie-language-input').parent('.form-group').addClass('has-error');
            $('#movie-language-error').css("visibility", "visible");
        }else{
            $('#movie-language-error').css("visibility", "hidden");
        }
        if (!data.length){
            isValidate = false;
            $('#movie-length-input').parent('.form-group').addClass('has-error');
            $('#movie-length-error').css("visibility", "visible");
        }else{
            $('#movie-length-error').css("visibility", "hidden");
        }
        return isValidate;
    }

    function getMovieList() {
        getRequest(
            '/movie/all',
            function (res) {
                renderMovieList(res.content);
            },
            function (error) {
                alert(error);
            }
        );
    }

    function renderMovieList(list) {
        $('.movie-on-list').empty();
        var movieDomStr = '';
        list.forEach(function (movie) {
            movie.description = movie.description || '';
            movieDomStr +=
                "<li class='movie-item card'>" +
                "<img class='movie-img' src='" + (movie.posterUrl || "../images/defaultAvatar.jpg") + "'/>" +
                "<div class='movie-info'>" +
                "<div class='movie-title'>" +
                "<span class='primary-text'>" + movie.name + "</span>" +
                "<span class='label "+(!movie.status ? 'primary-bg' : 'error-bg')+"'>" + (movie.status ? '已下架' : (new Date(movie.startDate)>=new Date()?'未上映':'热映中')) + "</span>" +
                "<span class='movie-want'><i class='icon-heart error-text'></i>" + (movie.likeCount || 0) + "人想看</span>" +
                "</div>" +
                "<div class='movie-description dark-text'><span>" + movie.description + "</span></div>" +
                "<div>类型：" + movie.type + "</div>" +
                "<div style='display: flex'><span>导演：" + movie.director + "</span><span style='margin-left: 30px;'>主演：" + movie.starring + "</span>" +
                "<div class='movie-operation'><a href='/admin/movieDetail?id="+ movie.id +"'>详情</a></div></div>" +
                "</div>"+
                "</li>";
        });
        $('.movie-on-list').append(movieDomStr);
    }
});