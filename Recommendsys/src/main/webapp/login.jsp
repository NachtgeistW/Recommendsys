<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 28/10/2020
  Time: 下午3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <link rel="stylesheet" href="plug/layui/css/layui.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="layui-container">
    <div class="login-background">
        <div class="layui-form login-form"
             id="loginDiv">
            <form class="layui-form" action="" id="login_form">
                <div class="layui-form-item logo-title">
                    <h1>找铺子-登录</h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="email"></label>
                    <input type="text" name="email" id="email" lay-verify="required" placeholder="用户名或者邮箱"
                           autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="loginPassword"></label>
                    <input type="password" name="loginPassword" id="loginPassword" lay-verify="required|password"
                           placeholder="密码"
                           autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-vercode" for="captcha"></label>
                    <input type="text" name="captcha" id="captcha" placeholder="请输入验证码" autocomplete="off"
                           class="layui-input admin-input admin-input-verify captcha"
                           value="">
                    <div class="captcha-img">
                        <canvas id="canvas" width="100" height="43" class="admin-captcha"></canvas>
                    </div>
                </div>
                <div class="tip">
                    <a href="javascript:" class="transPage" onclick="jump(0)">注册</a>
                    <a href="javascript:" class="forget-password" onclick="jump(2)">忘记密码？</a>
                </div>
                <div class="layui-form-item">
                    <input type="button" class="layui-btn layui-btn layui-btn-normal layui-btn-fluid btn" lay-submit="login"
                            lay-filter="login" id="loginBtn" value="登录">
                    </input>
                </div>
            </form>
        </div>
        <div class="layui-form login-form" id="forgetDiv" style="display: none">
            <form class="layui-form" action="">
                <div class="layui-form-item logo-title">
                    <h1>找铺子-忘记密码</h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="fEmail"></label>
                    <input type="text" name="fEmail" id="fEmail" lay-verify="required" placeholder="输入注册邮箱"
                           autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="password" id="password" lay-verify="required|password"
                           placeholder="输入新密码"
                           autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="fPasswords"></label>
                    <input type="fPasswords" name="fPasswords" id="fPasswords" lay-verify="required|confirmFPass"
                           placeholder="密码"
                           autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-vercode" for="FEmailCaptcha"></label>
                    <input type="text" name="FEmailCaptcha" id="FEmailCaptcha" placeholder="请输入验证码" autocomplete="off"
                           class="layui-input admin-input admin-input-verify captcha" value="">
                    <input type=button class="layui-btn captcha-btn" id="getFCaptcha" value="获取验证码">
                </div>
                <div class="tip">
                    <a href="javascript:" class="transPage" onclick="jump(1)">返回登录页面</a>
                </div>
                <div class="layui-form-item">
                    <input type="button" class="layui-btn layui-btn layui-btn-normal layui-btn-fluid btn" lay-submit="fPasswordBtn"
                            lay-filter="fPasswordBtn" id="fPasswordBtn" value="修改密码">
                    </input>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="plug/layui/layui.js"></script>
<script src="js/json2.js"></script>
<%--<script src="js/jquery.particleground.min.js" charset="utf-8"></script>--%>
<script type="text/javascript">
    layui.use(['form', 'jquery'], function () {
        var form = layui.form,
            layer = layui.layer;

        var show_num = [];
        draw(show_num);
        $("#canvas").on('click', function () {
            draw(show_num);
        })

        // // 粒子线条背景
        // $(document).ready(function(){
        //     $('.layui-container').particleground({
        //         dotColor:'#c8dcff',
        //         lineColor:'#62bfff'
        //     });
        // });
        // 进行登录操作
        form.on('submit(login)', function (data) {
            data = data.field;
            var num = show_num.join("");
            if (data.eamil == '') {
                layer.msg('用户名不能为空');
                draw(show_num);
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                draw(show_num);
                return false;
            }
            if (data.captcha == '') {
                layer.msg('验证码不能为空');
                draw(show_num);
                return false;
            }
            if (data.captcha != num) {
                layer.msg('验证码错误！请重新输入！');
                draw(show_num);
                return false;
            }
            var email = $('#email').val();
            var password = $('#loginPassword').val();
            var data1 = {
                "email": email,
                "password": password
            };

            $.ajax({
                type: 'post',
                async:false,
                cache:false,
                url: '${pageContext.request.contextPath}/loginUser',
                // contentType: "application/json;charset=UTF-8",
                dataType: "json",
                data: data1,
                success: function (data) {
                    console.log(data);
                   if(data.msg=="1"){
                       layer.msg("管理员登录成功");
                       window.location.href = "AdminIndex.jsp";
                   }else if(data.msg=="0"){
                       layer.msg("用户登录成功");
                       window.location.href = "userPage/home.jsp";
                   }
                   else{
                       layer.msg("账号或密码错误");
                       $("#login_form")[0].reset();
                       //TODO：重设验证码
                       layui.form.render();
                   }
                },
                error: function (res) {
                    layer.msg('1');
                    // window.location.href = "login.jsp";
                }
            })
        });
    });

    //画验证码
    function draw(show_num) {
        var canvas_width = $('#canvas').width();
        var canvas_height = $('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度

        for (var i = 0; i <= 3; i++) {
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
            var txt = aCode[j];//得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";

            context.translate(x, y);
            context.rotate(deg);

            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);

            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }

    function randomColor() {//得到随机的颜色值
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }
</script>
<script>
    //跳div
    function jump(site) {
        if (site == 0) {
            window.location.href="loginRegister.jsp";
        }
        if (site == 1) {
            document.getElementById('loginDiv').style.display = 'block';
            document.getElementById('forgetDiv').style.display = 'none';
        }
        if (site == 2) {
            document.getElementById('forgetDiv').style.display = 'block';
            document.getElementById('loginDiv').style.display = 'none';
            layui.use(['form', 'jquery'], function () {
                var form = layui.form
                    , layer = layui.layer;
                //进行更改密码操作
                form.on('submit(fPasswordBtn)', function (data) {
                    if (data.field.FEmailCaptcha == "") {
                        layer.msg('验证码不能为空');
                    }else{
                        $.ajax({
                            url:"${pageContext.request.contextPath}/checkMail",
                            contentType: "application/json",
                            dataType:'json',
                            type: "post",
                            async: false,
                            data: JSON.stringify(data.field),
                            success: function(result) {
                                if(result){
                                    layer.msg("修改成功");
                                }else {
                                    layer.msg("修改失败");
                                }

                            },
                        })
                    }

                    });
                //验证操作
                form.verify({
                    confirmFPass: function (value) {
                        if ($('#password').val() !== value)
                            return '两次密码输入不一致！';
                    }

                });
                //获取验证码
                $('#getFCaptcha').click(function () {
                    var address = $('#fEmail').val();

                        $.ajax({
                            url:"${pageContext.request.contextPath}/sendEmail",
                            contentType: "application/json",
                            dataType:'json',
                            type: "post",
                            data: JSON.stringify(address),
                            success: function(result) {
                                if (result) {
                                    layer.msg("发送成功");
                                } else {
                                    layer.msg("发送失败");
                                }
                            },
                    })
                });
            });
        }
    }
</script>

</body>
</html>

