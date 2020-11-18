﻿layui.use(['jquery', 'carousel'], function () {
    var $ = layui.jquery;
    var carousel = layui.carousel;

    var ins = carousel.render({
        elem: '#home-carousel'
        , width: '100%' //设置容器宽度
        , height: '200px'
        , arrow: 'hover' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });

    $(function () {
        //播放轮播图
        playCarousel();
        //播放公告
        playAnnouncement(3000);
        //显示数据列表
        showRestaurant();
    });

    function showRestaurant() {
        $.ajax({
            type: "get",
            url: "_restaurant.json",
            dataType: "json",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var newHtml = '<div class="blog-main-left">\n' +
                        '                <div class="article shadow">\n' +
                        '                    <div class="article-left">\n' +
                        '                        <img src="../' + data[i].resturant_image + '" alt="店铺图片" class="imgRestaurant"/>\n' +
                        '                    </div>\n' +
                        '                    <div class="article-right">\n' +
                        '                        <div class="article-title">\n' +
                        '                            <a href="detail.html?productId=' + data[i].id_restaurant + '">' + data[i].name + '</a>\n' +
                        '                        </div>\n' +
                        '                        <div class="article-abstract">\n' +
                        '                            '+data[i].intro+'\n' +
                        '                        </div>\n' +
                        '                        <div class="article-abstract article-abstract-low">\n' +
                        '                            推荐理由：'+data[i].recommand_reason+'\n' +
                        '                        </div>' +
                        '                    </div>'+
                        '                    <div class="clear"></div>\n' +
                        '                    <div class="article-footer">\n' +
                        '                        <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">'+data[i].type_of_cuisine+'</a></span>\n' +
                        '                        <span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;'+data[i].comment_num+'</span>\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </div>';
                    $("#content").append(newHtml);
                }
            },
            error: function () {
                console.log("传输错误");
            }
        });

    }

    function playCarousel() {
        $.ajax({
            type: "get",
            url: "_carousal.json",
            async: false,
            dataType: "json",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var newHtml = '<div><img src=' + data[i].src + ' width="100%"></div>';
                    $("#carousel").append(newHtml);
                }
                ins.render({
                    elem: '#home-carousel'
                    , width: '100%' //设置容器宽度
                    , height: '200px'
                    , arrow: 'hover' //始终显示箭头
                    //,anim: 'updown' //切换动画方式
                });
            },
        });

    }

    function playAnnouncement(interval) {
        var index = 0;
        var $announcement = $('.home-tips-container>span');
        //自动轮换
        setInterval(function () {
            index++;    //下标更新
            if (index >= $announcement.length) {
                index = 0;
            }
            $announcement.eq(index).stop(true, true).fadeIn().siblings('span').fadeOut();  //下标对应的图片显示，同辈元素隐藏
        }, interval);
    }

});
