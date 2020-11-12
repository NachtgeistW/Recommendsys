layui.use(['form', 'layedit', 'rate', 'layer', 'carousel', 'jquery'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    var layer = layui.layer;
    var layedit = layui.layedit;
    var rate = layui.rate;
    var carousel = layui.carousel;
    //路径
    var search = window.location.search;
    var ID = getSearchString('productId', search);
    var restaurantData = "";

    $(function () {
        $("#shopAddress").click(function () {
            var windowMap = document.getElementById("windowMap");
            windowMap.style.display = "block";
            console.log(windowMap.style.display);
            openLayer();
        });
        $("#shopPhoto").click(function () {
            var windowPhoto = document.getElementById("windowPhoto");
            windowPhoto.style.display = "block";
            playCarousel();
        });
        //给评论点赞
        $("#comment").on("click", ".commentHeart1", function () {
            var index = $('.commentHeart1').index($(this));
            commentLike(index);
        });
        //取消评论点赞
        $("#comment").on("click", ".commentHeart2", function () {
            var index = $('.commentHeart2').index($(this));
            commentDisLike(index);
        });

        getData(ID);
        fillData(restaurantData);
        fillComment(ID);
    });

    function getData(ID) {
        $.ajax({
            async: false,
            cache: false,
            // type : 'POST',
            // url : "",
            type: "get",
            url: "_detail.json",
            dataType: "json",
            success: function (data) {
                restaurantData = data;
            }
        })
    }

    //分隔url
    function getSearchString(key, Url) {
        var str = Url;
        str = str.substring(1, str.length); // 获取URL中?之后的字符（去掉第一位的问号）
        // 以&分隔字符串，获得类似name=xiaoli这样的元素数组
        var arr = str.split("&");
        var obj = new Object();

        // 将每一个数组元素以=分隔并赋给obj对象
        for (var i = 0; i < arr.length; i++) {
            var tmp_arr = arr[i].split("=");
            obj[decodeURIComponent(tmp_arr[0])] = decodeURIComponent(tmp_arr[1]);
        }
        return obj[key];
    }

    //页面数据填充
    function fillData(data) {
        var restaurantName = document.getElementById("restaurantName");
        var commentNumber = document.getElementById("commentNumber");
        var addressID = document.getElementById("addressID");
        var restaurant_intro = document.getElementById("restaurant_intro");
        var commentReason = document.getElementById("commentReason");
        restaurantName.innerHTML = data.name;
        commentNumber.innerHTML = data.comment_num;
        addressID.innerHTML = data.address;
        restaurant_intro.innerHTML = data.intro;
        commentReason.innerHTML = data.recommand_reason;
    }

    function fillComment(ID) {
        $.ajax({
            // async : false,
            // cache : false,
            // type : 'POST',
            // url : "",
            type: "get",
            url: "_comment.json",
            dataType: "json",
            success: function (data) {
                // var comment =document.getElementById("comment");
                for (var i = 0; i < data.length; i++) {
                    var newHtml = '<li>\n' +
                        '                            <div class="comment-parent">\n' +
                        '                                <img src="../images/Absolutely.jpg" alt="absolutely"/>\n' +
                        '                                <div class="info">\n' +
                        '                                    <span class="username">' + data[i].userName + '</span>\n' +
                        '                                    评分：<span class="score">' + data[i].score + '</span>\n' +
                        '                                    <span class="time">' + data[i].time + '</span>\n' +
                        '                                    <div style="float: right"><img src="../images/heart.svg" width="16px"\n' +
                        '                                                                   class="commentHeart1" style="display: inline-block;">\n' +
                        '                                        <img src="../images/heart.png" width="16px" class="commentHeart2"\n' +
                        '                                             style="display: none">\n' +
                        '                                        <span class="commentNum" style="float: right">' + data[i].like + '</span></div>\n' +
                        '                                </div>\n' +
                        '                                <div class="content">\n' + data[i].context +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </li>';
                    $("#comment").append(newHtml);
                }
            }
        })
    }

    //评论和留言的编辑器
    var editIndex = layedit.build('remarkEditor', {
        height: 150,
        tool: ['face', '|', 'left', 'center', 'right'],
    });
    //显示评价星星
    rate.render({
        elem: '#gradeStar'
        , value: 3.5
        , half: true
        , text: true
        , readonly: true
        , setText: function (value) { //自定义文本的回调
            this.span.text(value + "分");
        }
    });
    //星星评分
    rate.render({
        elem: '#star'
        , value: 3.5
        , half: true
        , text: true
        , theme: '#009688'
        , setText: function (value) { //自定义文本的回调
            this.span.text(value);
        }
        , choose: function (value) {
            star = value;
        }
    });
    //评论和留言的编辑器的验证
    form.verify({
        content: function (value) {
            value = $.trim(layedit.getText(editIndex));
            if (value == "") return "至少得有一个字吧";
            layedit.sync(editIndex);
        }
    });

    //评论时间
    function commentTime() {
        var d = new Date();
        var M = (d.getMonth() + 1) < 10 ? ('0' + (d.getMonth() + 1)) : (d.getMonth() + 1);
        var D = d.getDate() < 10 ? ('0' + d.getDate()) : d.getDate();
        var H = d.getHours() < 10 ? ('0' + d.getHours()) : d.getHours();
        var m = d.getMinutes() < 10 ? ('0' + d.getMinutes()) : d.getMinutes();
        var s = d.getSeconds() < 10 ? ('0' + d.getSeconds()) : d.getSeconds();
        var t = d.getFullYear() + "-" + M + "-" + D + "&nbsp;&nbsp;" + H + ":" + m + ":" + s;
        return t;

    };

    //监听评论提交
    form.on('submit(formRemark)', function (data) {
        var index = layer.load(1);

        setTimeout(function () {
            layer.close(index);
            var content = data.field.editorContent;
            var star = Number(document.querySelector("#star>span").innerText);
            //模拟评论提交start
            var html = '<li>\n' +
                '                            <div class="comment-parent">\n' +
                '                                <img src="../images/Absolutely.jpg" alt="absolutely"/>\n' +
                '                                <div class="info">\n' +
                '                                    <span class="username">老辣鸡</span>\n' +
                '                                    评分：<span class="score">' + star + '</span>\n' +
                '                                    <span class="time">' + commentTime() + '</span>\n' +
                '                                    <div style="float: right"><img src="../images/heart.svg" width="16px"\n' +
                '                                                                   class="commentHeart1" style="display: inline-block;">\n' +
                '                                        <img src="../images/heart.png" width="16px" class="commentHeart2"\n' +
                '                                             style="display: none">\n' +
                '                                        <span class="commentNum" style="float: right">0</span></div>\n' +
                '                                </div>\n' +
                '                                <div class="content">\n' + content +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </li>';
            $('#comment').append(html);
            //模拟评论提交end
            // var data1 = {
            //     "context": content,
            //     "score": star
            // };
            // $.ajax({
            //     type: 'post',
            //     async: false,
            //     cache: false,
            //     url: '',
            //     dataType: "json",
            //     data: data1,
            //     success: function (data) {
            //         layer.msg("评论成功", {icon: 1});
            //
            //     },
            //     error: function () {
            //         layer.msg("评论失败", {icon: 2});
            //     }
            // })
            $('#remarkEditor').val('');
            editIndex = layui.layedit.build('remarkEditor', {
                height: 150,
                tool: ['face', '|', 'left', 'center', 'right'],
            });

        }, 500);
        return false;
    });

    //地图初始化
    function initMap() {
        var addressId = document.getElementById("addressID");
        var shopAddress = addressId.innerText;

        AMap.plugin('AMap.Geocoder', function () {
            var geocoder = new AMap.Geocoder({
                // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
                city: '全国'
            });

            geocoder.getLocation(shopAddress, function (status, result) {
                if (status === 'complete' && result.info === 'OK') {
                    var lnglat = result.geocodes[0].location;
                    marker.setPosition(lnglat);
                    map.add(marker);
                    map.setFitView(marker);
                } else {
                    console.log(status);
                }
            })
        });
        var map = new AMap.Map("containerMap", {
            zoom: 18,
            center: [116.396574, 39.992706],
        });
        var marker = new AMap.Marker({
            map: map,
            position: [116.396574, 39.992706],
            label: {
                offset: new AMap.Pixel(20, 20),//修改label相对于maker的位置
                content: "点击Marker打开高德地图"
            }
        });
        marker.on('click', function (e) {
            console.log(marker.getPosition());
            var url = "https://gaode.com/regeo?lng=" + marker.getPosition().lng + "&lat=" + marker.getPosition().lat + "&adcode=";
            window.open(url);
        });
    }

    // 打开图片弹窗
    function playCarousel() {
        $.ajax({
            type: "post",
            url: "_carousal.json",
            async: false,
            dataType: "json",
            success: function (data) {
                var W = document.body.clientWidth;
                var H = document.body.clientHeight;
                if (W > 500) {
                    layer.open({
                        type: 1,
                        area: ["400px", "700px"],
                        // area:"auto",
                        title: "地图显示",
                        scrollbar: false,
                        offset: ['0px', '35%'],
                        content: $("#windowPhoto")
                        , success: function () {
                            for (var i = 0; i < data.length; i++) {
                                var newHtml = '<div><img src=' + data[i].src + ' class="pic" width="100%"></div>';
                                $("#carousel").append(newHtml);
                            }
                            carousel.render({
                                elem: '#detail-carousel'
                                // , full: 'true'
                                , width: '400px'
                                , height: '600px'
                                , autoplay: false
                                , arrow: 'hover' //悬停显示箭头
                            });
                        }
                        , end: function () {
                            var windowPhoto = document.getElementById("windowPhoto");
                            windowPhoto.style.display = "none";
                        }
                    });
                } else {
                    layer.open({
                        type: 1,
                        area: ["400px", "700px"],
                        // area:"auto",
                        title: "地图显示",
                        scrollbar: false,
                        offset: ['0px', '6px'],
                        content: $("#windowPhoto")
                        , success: function () {
                            for (var i = 0; i < data.length; i++) {
                                var newHtml = '<div><img src=' + data[i].src + ' class="pic" width="100%"></div>';
                                $("#carousel").append(newHtml);
                            }
                            carousel.render({
                                elem: '#detail-carousel'
                                // , full: 'true'
                                , width: '400px'
                                , height: '600px'
                                , autoplay: false
                                , arrow: 'hover' //悬停显示箭头
                            });
                        }
                        , end: function () {
                            var windowPhoto = document.getElementById("windowPhoto");
                            windowPhoto.style.display = "none";
                        }
                    });
                }


            },
        });
    }

    //打开地图弹窗
    function openLayer() {
        var width = document.body.clientWidth;
        initMap();

        if (width > 500) {
            layer.open({
                type: 1,
                area: '500px',
                title: "地图显示",
                content: $("#windowMap") //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                , end: function () {
                    windowMap.style.display = "none";
                }
            });
        } else {
            layer.open({
                type: 1,
                area: '300px',
                title: "地图显示",
                content: $("#windowMap") //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                , end: function () {
                    windowMap.style.display = "none";
                }
            });
        }

    }

    //给评论点赞
    function commentLike(index) {
        var commentHeart2 = document.getElementsByClassName("commentHeart2");
        var commentHeart1 = document.getElementsByClassName("commentHeart1");
        var commentNum = document.getElementsByClassName("commentNum");
        var num = Number(commentNum[index].innerText);
        num = num + 1;
        commentNum[index].innerHTML = num;
        commentHeart2[index].style.display = "inline-block";
        commentHeart1[index].style.display = "none";
    }

    //取消评论点赞
    function commentDisLike(index) {
        var commentHeart2 = document.getElementsByClassName("commentHeart2");
        var commentHeart1 = document.getElementsByClassName("commentHeart1");
        var commentNum = document.getElementsByClassName("commentNum");
        var num = Number(commentNum[index].innerText);
        num = num - 1;
        commentNum[index].innerHTML = num;
        commentHeart2[index].style.display = "none";
        commentHeart1[index].style.display = "inline-block";
    }
});