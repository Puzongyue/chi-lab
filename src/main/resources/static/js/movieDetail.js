$(document).ready(function(){

    var movieId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var userId = sessionStorage.getItem('id');
    var isLike = false;

    getMovie();
    if(sessionStorage.getItem('role') === 'admin')
        getMovieLikeChart();

    function getMovieLikeChart() {
       getRequest(
           '/movie/' + movieId + '/like/date',
           function(res){
               var data = res.content,
                    dateArray = [],
                    numberArray = [];
               data.forEach(function (item) {
                   dateArray.push(item.likeTime);
                   numberArray.push(item.likeNum);
               });

               var myChart = echarts.init($("#like-date-chart")[0]);

               // 指定图表的配置项和数据
               var option = {
                   title: {
                       text: '想看人数变化表'
                   },
                   xAxis: {
                       type: 'category',
                       data: dateArray
                   },
                   yAxis: {
                       type: 'value'
                   },
                   series: [{
                       data: numberArray,
                       type: 'line'
                   }]
               };

               // 使用刚指定的配置项和数据显示图表。
               myChart.setOption(option);
           },
           function (error) {
               alert(error);
           }
       );
    }

    function getMovie() {
        getRequest(
            '/movie/'+movieId + '/' + userId,
            function(res){
                var data = res.content;
                isLike = data.islike;
                repaintMovieDetail(data);
            },
            function (error) {
                alert(error);
            }
        );
    }

    function repaintMovieDetail(movie) {
        !isLike ? $('.icon-heart').removeClass('error-text') : $('.icon-heart').addClass('error-text');
        $('#like-btn span').text(isLike ? ' 已想看' : ' 想 看');
        $('#movie-img').attr('src',movie.posterUrl);
        $('#movie-name').text(movie.name);
        $('#order-movie-name').text(movie.name);
        $('#movie-description').text(movie.description);
        $('#movie-startDate').text(new Date(movie.startDate).toLocaleDateString());
        $('#movie-type').text(movie.type);
        $('#movie-country').text(movie.country);
        $('#movie-language').text(movie.language);
        $('#movie-director').text(movie.director);
        $('#movie-starring').text(movie.starring);
        $('#movie-writer').text(movie.screenWriter);
        $('#movie-length').text(movie.length);



        //DingLingyan 2019.5.11
        if(sessionStorage.getItem('role') === 'admin') {
            $('#movie-name-input').val(movie.name);
            //$('#movie-date-input').val(new Date(movie.startDate));
            //用jQuery没有效果，只能用原生DOM，虽然奇奇怪怪，但只能这样了QAQ
            document.getElementById('movie-date-input').valueAsDate = new Date(movie.startDate);
            $('#movie-img-input').val(movie.posterUrl);
            $('#movie-description-input').val(movie.description);
            $('#movie-type-input').val(movie.type);
            $('#movie-length-input').val(movie.length);
            $('#movie-country-input').val(movie.country);
            $('#movie-language-input').val(movie.language);
            $('#movie-director-input').val(movie.director);
            $('#movie-star-input').val(movie.starring);
            $('#movie-writer-input').val(movie.screenWriter);
            //console.log($('#movie-description').text());
        }
    }

    // user界面才有
    $('#like-btn').click(function () {
        var url = isLike ?'/movie/'+ movieId +'/unlike?userId='+ userId :'/movie/'+ movieId +'/like?userId='+ userId;
        postRequest(
             url,
            null,
            function (res) {
                 isLike = !isLike;
                getMovie();
            },
            function (error) {
                alert(error);
            });
    });

    // admin界面才有  这个可以参考排片管理界面的js文件，但不怎么看得懂×
    /*$("#modify-btn").click(function (e) {
        var movie = JSON.parse(e.target.dataset.movie);

        $('#movie-name-input').val(movie.name);
        $('#movie-description-input').val(movie.description);*/

        /*$('#movie-name-input').val($('#movie-name').text());
        $('#movie-date-input').val($('#movie-startDate').text());
        $('#movie-img-input').val($('#movie-img').src());
        $('#movie-description-input').val($('#movie-description').val());
        $('#movie-type-input').val($('#movie-type').text());
        $('#movie-length-input').val($('#movie-length').text());
        $('#movie-country-input').val($('#movie-country').text());
        $('#movie-language-input').val($('#movie-language').text());
        $('#movie-director-input').val($('#movie-director').text());
        $('#movie-star-input').val($('#movie-starring').text());
        $('#movie-writer-input').val($('#movie-writer').text());
    });*/

    //DingLingyan 2019.5.11

    $('#update-form-btn').click(function () {
        var updateData = getMovieForm();
        var status = 3;

        if (!validateMovieForm(updateData)){
            return;
        }

        getRequest(
            '/movie/'+movieId + '/' + userId,
            function (res) {
                if (res.success) {
                    var data = res.content;
                    status = data.status;
                    if (status==0){
                        postRequest(
                            '/movie/update',
                            updateData,
                            function (res) {
                                console.log(JSON.stringify(res));
                                if (res.success) {
                                    $('#updateMovie').modal('hide');
                                    getMovie();
                                }else {
                                    //console.log(3);
                                    alert(res.message);
                                }
                            },
                            function (error) {
                                //console.log(4);
                                alert(error);
                            });
                    }
                    else {
                        //console.log(5);
                        alert("已下架电影不能修改信息");
                    }
                }
                else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            }
        );




    });

    function getMovieForm() {
        return {
            id: movieId,
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


    function validateMovieForm(data) {
        var isValidate = true;
        if(!data.name) {
            isValidate = false;
            $('#movie-name-input').parent('.form-group').addClass('has-error');
        }
        if(!data.posterUrl) {
            isValidate = false;
            $('#movie-img-input').parent('.form-group').addClass('has-error');
        }
        if(!data.startDate) {
            isValidate = false;
            $('#movie-date-input').parent('.form-group').addClass('has-error');
        }
        return isValidate;
    }


    $("#delete-btn").click(function () {
        var sure = confirm("确定要将这部影片下架吗？");
        //console.log({movieIdList:[Number($('#updateMovie')[0].dataset.movieId)]});
        if (sure) {
            postRequest(
                '/movie/off/batch',
                {movieIdList:[movieId]},
                function (res) {
                    if(res.success){
                        alert("success");
                    } else{
                        alert(res.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );
            /*getRequest(
                '/movie/all',
                function (res) {
                    renderMovieList(res.content);
                },
                function (error) {
                    alert(error);
                }
            );*/
        }
    });

    /*function renderMovieList(list) {
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
    }*/

});