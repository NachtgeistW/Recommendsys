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
        <div class="layui-form login-form" id="registerDiv">
            <form class="layui-form" action="/register">
                <div class="layui-form-item logo-title">
                    <h1>找铺子-注册</h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="email"></label>
                    <input type="text" name="email" id="email" lay-verify="required"
                           placeholder="邮箱"
                           autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="password" id="password"
                           lay-verify="required|password" placeholder="密码"
                           autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="confirm_password"></label>
                    <input type="password" name="confirm_password" id="confirm_password"
                           lay-verify="required|confirmPass"
                           placeholder="验证密码"
                           autocomplete="off" class="layui-input " value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-vercode" for="emailCaptcha"></label>
                    <input type="text" name="emailCaptcha" id="emailCaptcha" placeholder="请输入验证码" autocomplete="off"
                           class="layui-input admin-input admin-input-verify captcha" value="">
                    <input type=button class="layui-btn captcha-btn" id="getRCaptcha" value="获取验证码">
                </div>
                <div class="tip">
                    <a href="javascript:" class="transPage" onclick="jump(1)">返回登陆页面</a>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn layui-btn-normal layui-btn-fluid btn" lay-submit="register_btn"
                            type="submit"
                            lay-filter="register_btn" id="register_btn">注册
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="plug/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form', 'jquery'], function () {
        var form = layui.form
            , layer = layui.layer;
        //进行注册操作
        form.on('submit(register_btn)', function (data) {
            alert(JSON.stringify(data.field));
            if (data.field.emailCaptcha == "") {
                layer.msg('验证码不能为空');
            }
            alert(3)
            layer.msg('emmm');
        });
        //验证操作
        form.verify({
            isExist: function (value) {
                var params = {"userId": value};
                var data;
                $.ajax({
                    url: "",
                    contentType: "application/json",
                    dataType: 'json',
                    type: "post",
                    async: false,
                    data: JSON.stringify(params),
                    success: function (result) {
                        data = result.flag;
                    },

                });
                if (data.flag) {
                    return '用户id已存在';
                }
            },
            confirmPass: function (value) {
                if ($('#password').val() !== value)
                    return '两次密码输入不一致！';
            },

        });
        //获取验证码
        $('#getRCaptcha').click(function () {
            var address = $('#email').val();
            console.log(address);
            $.ajax({
                url: "${pageContext.request.contextPath}/sendEmail",
                contentType: "application/json",
                dataType: 'json',
                type: "post",
                data: JSON.stringify(address),
                success: function (result) {
                    if (result) {
                        layer.msg("发送成功");
                    } else {
                        layer.msg("发送失败");
                    }
                },
            })
        });
    });

</script>
<script type="text/javascript">
    function jump(site) {
        if (site == 0) {
            window.location.href = "loginRegister.jsp";
        }
        if (site == 1) {
            window.location.href = "login.jsp";
        }
    }
</script>
</body>
</html>

