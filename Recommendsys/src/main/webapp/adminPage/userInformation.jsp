<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 2/10/2020
  Time: 下午9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../plug/layui/css/layui.css">
    <link rel="stylesheet" href="../../css/admin/table.css">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
</head>
<body>
<!-- 搜索条件开始 -->
<!--<fieldset class="layui-elem-field layui-field-title"-->
<!--          style="margin-top: 0px">-->
<!--    <legend>店铺信息查询</legend>-->
<!--</fieldset>-->
<form class="layui-form"
<%--      action="user/findByExample" method="post"--%>
>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input name="userName" id="user_name" class="layui-input" type="text" autocomplete="off">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input name="email" id="email" class="layui-input" type="text"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份</label>
            <div class="layui-input-inline">
                <select lay-filter="identity" id="user_identity" name="identity">
                    <option value="" selected=""></option>
                    <option value="0">普通用户</option>
                    <option value="1">管理员</option>
                </select>
            </div>

            <div class="layui-inline layui-input-block">
                <button
                        class="layui-btn  layui-icon layui-icon-search"
                        type="submit" lay-submit lay-filter="doSearch" id="doSearch">查询
                </button>
                <button
                        class="layui-btn layui-btn-warm layui-icon layui-icon-refresh"
                        type="reset">重置
                </button>
            </div>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->
<!-- 数据表格开始 -->
<table class="layui-hide" id="userTable" lay-filter="userTable"></table>
<div style="display: none;" id="userToolBar">
<%--    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>--%>
    <button type="button" class="layui-btn layui-btn-sm"
            lay-event="delete">批量删除
    </button>
</div>
<div id="toolBar" style="display: none;">
<%--    <a class="layui-btn layui-btn-xs" lay-event="edit" lay-filter="edit">编辑</a>--%>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->
<!-- 添加和修改的弹出层开始 -->
<div style="display: none; padding: 20px" class="pop-box" id="saveOrUpadteDiv">
    <form class="layui-form " action="" lay-filter="dataFrm" id="dataFrm">
        <%--        <div class="layui-inline">--%>
        <%--            <label class="layui-form-label">用户ID</label>--%>
        <%--            <div class="layui-input-inline">--%>
        <%--                <input name="user_id" class="layui-input" type="text" autocomplete="off"--%>
        <%--                       lay-verify="required" id="edit_id">--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <div class="layui-inline">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input name="userName" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="edit_name">
            </div>
        </div>
<%--        <div class="layui-inline">--%>
<%--            <label class="layui-form-label">经验</label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <input name="experience" class="layui-input" type="text"--%>
<%--                       autocomplete="off" lay-verify="required" id="edit_experience">--%>
<%--            </div>--%>
<%--        </div>--%>
        <%--        <div class="layui-inline">--%>
        <%--            <label class="layui-form-label">身份</label>--%>
        <%--            <div class="layui-input-inline">--%>
        <%--                <input name="user_identity" class="layui-input"  autocomplete="off" id="edit_identity"--%>
        <%--                       autocomplete="off" lay-verify="required">--%>
        <%--            </div>--%>
        <%--        </div>--%>
<%--        <div class="layui-inline">--%>
<%--            <label class="layui-form-label">积分</label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <input name="integral" class="layui-input" autocomplete="off" id="edit_integral"--%>
<%--                       autocomplete="off" lay-verify="required">--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input name="email" class="layui-input" id="edit_email" autocomplete="off"
                       lay-verify="required|email">
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button
                        class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-heart-fill"
                        type="button" lay-filter="edit-save" lay-submit="" id="save">保存
                </button>
                <button
                        class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-heart"
                        type="reset">重置
                </button>
            </div>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->
<script src="../../plug/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['table', 'jquery', 'layer', 'form', 'laydate', 'upload'],
        function () {
            var table = layui.table;
            var $ = layui.jquery;
            var form = layui.form;
            var layer = layui.layer;
            var laydate = layui.laydate;
            //绑定时间选择器
            laydate.render({
                elem: '#startTime'
            });
            laydate.render({
                elem: '#endTime'
            });
            //渲染数据表格
            var tableIns = table.render({
                elem: '#userTable' //渲染的目标对象
                , url: '${pageContext.request.contextPath}/user/findAll' //数据接口
                , title: '店铺数据表' //数据导出时的标题
                , toolbar: "#userToolBar" //表头工具条
                , defaultToolbar: ['filter', 'print', 'exports']
                , cellMinWidth: 80  //设置 列的最小的默认宽度
                , page: true //是否启用分页
                , cols: [[
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'idUser', title: '用户ID', sort: true}
                    , {field: 'userName', title: '用户名', sort: true}
                    , {field: 'password', title: '密码', hide : true}
                    , {field: 'experience', title: '经验', sort: true}
                    , {field: 'identity', title: '身份'}
                    , {field: 'integral', title: '积分'}
                    , {field: 'email', title: '邮箱'}
                    , {fixed: 'right', title: '操作', toolbar: '#toolBar', minWidth: 115}
                ]]
                , limit: 10
                , limits: [3, 5, 10, 12]
                , parseData: function (res) {
                    var result;
                    if(res.data!=null){
                        if (this.page.curr) {
                            result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
                        } else {
                            result = res.data.slice(0, this.limit);
                        }
                    }else {
                        layer.msg("没有查询到相关数据")
                    }
                    return {"code": res.code, "msg": res.msg, "count": res.count, "data": result};
                }
                , text: "数据加载失败"
            });
            //监听工具条
            table.on('tool(userTable)', function (obj) {
                var id = $(this).parents("tr").children("td:nth-child(2)").text();
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                if (layEvent === 'del') { //删除
                    layer.msg("删除");
                    layer.confirm('真的删除这位用户吗', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        layer.close(index);
                        //向服务端发送删除指令
                        $.ajax({
                            url:'${pageContext.request.contextPath}/user/delete',
                            dataType:'json',
                            type: 'post',
                            data:{"idUser":id},
                            success:function (data) {
                                layer.msg(data.msg);
                                tableIns.reload();
                            }
                        })
                    });
                }
                // else if (layEvent === 'edit') { //编辑
                //     openUpadteData(data);
                // }
            });
            table.on('toolbar(userTable)', function (obj) {
                switch (obj.event) {
                    case 'add':
                        openAddUser();
                        break;
                    case 'delete':
                        var checkStatus = table.checkStatus('userTable');
                        //获取选中数量
                        data = checkStatus.data;
                        var ids = "";
                        if (data.length > 0) {
                            for (var i = 0; i < data.length; i++) {
                                ids = ids + data[i].idUser + ",";
                            }
                            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                                $.ajax({
                                    type: 'post',
                                    data: {"ids": ids},
                                    url: '${pageContext.request.contextPath}/user/batchDelete',
                                    success: function (data) {
                                        layer.msg('操作成功!', {icon: 1, time: 1000});
                                    }, error: function (code) {
                                        layer.msg('操作失败!', {icon: 5, time: 1000});
                                    }
                                });
                                tableIns.reload();
                                layer.close(index);
                                location.reload();
                            })
                        } else {
                            layer.msg("请选择需要删除的用户");
                        }
                        break;
                }
                ;
            });
            var url;
            var mainIndex;

            //打开修改页面
            function openUpadteData(data) {
                mainIndex = layer.open({
                    type: 1,
                    title: "修改",
                    content: $("#saveOrUpadteDiv"),
                    area: 'auto',
                    success: function (index) {
                        form.val("dataFrm", data);
                    }

                });
                form.on("submit(edit-save)", function (obj) {
                    var data1 = {
                        "idUser": data.idUser,
                        "userName": $("#edit_name").val,
                        "email": $("#edit_name").val,
                    };
                    // var data1={"address":$("#data_address").val()};
                    $.ajax({
                        type: "POST",
                        url: '${pageContext.request.contextPath}/user/saveOrUpdate',
                        contentType: "application/json;charset=UTF-8",
                        dataType: "json",
                        data: data1,
                        success: function (data) {
                            console.log("succeed");
                        },
                        error: function (data) {
                            console.log("fail");
                        },
                        beforeSend: function (xhr) {
                            xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
                        }

                    });
                    //关闭弹出层
                    layer.close(mainIndex);
                    //刷新数据表格
                    tableIns.reload();
                    // })
                });
            };

            //打开添加页面
            function openAddUser() {
                mainIndex = layer.open({
                    type: 1,
                    title: "添加用户",
                    content: $("#saveOrUpadteDiv"),
                    area: ['500px', '400px'],
                    /*   btnAlign:'c',
                      btn : [ '<div class="layui-icon layui-icon-heart-fill">保存</div>', '<div class="layui-icon layui-icon-heart">关闭</div>' ],
                      yes:function(index,layero){
                       layer.msg("保存");
                      },
                      btn2:function(index,layero){
                       layer.msg("关闭");
                      } */
                    success: function (index) {
                        $('#dataFrm')[0].reset();
                        url = "user/addUser.action";
                    }
                });
            }

            //搜索
            form.on("submit(doSearch)", function (data) {
                // alert(JSON.stringify(data.field));
                var user_name = data.field.userName;
                var email = data.field.email;
                var user_identity = data.field.identity;
                table.reload('userTable', {
                    url: '${pageContext.request.contextPath}/user/findByExample'
                    , where: {
                        'userName': user_name,
                        'email': email,
                        'identity': user_identity,
                    } //设定异步数据接口的额外参数
                    //,height: 300
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , text: {none: '无数据'}

                });
                return false;
            });


        });
</script>
</body>
</html>