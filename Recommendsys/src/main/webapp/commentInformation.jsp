<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 3/10/2020
  Time: 上午8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="plug/layui/css/layui.css">
    <link rel="stylesheet" href="css/admin/table.css">
</head>
<body>
<!-- 搜索条件开始 -->
<!--<fieldset class="layui-elem-field layui-field-title"-->
<!--          style="margin-top: 0px">-->
<!--    <legend>店铺信息查询</legend>-->
<!--</fieldset>-->
<form class="layui-form" action="">
    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">店铺id</label>
            <div class="layui-input-inline">
                <input name="r_id" id="r_id" class="layui-input" type="text" autocomplete="off">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">用户id</label>
            <div class="layui-input-inline">
                <input name="user_id" class="layui-input"   id="user_id" type="text"
                       autocomplete="off" >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline " style="width: 110px">
                <input name="startTime" id="startTime" class="layui-input time-input"
                       type="text" autocomplete="off" readonly="readonly">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-inline" style="width: 110px">
                <input name="endTime" id="endTime" class="layui-input time-input" type="text"
                       autocomplete="off" readonly="readonly">
            </div>
        </div>

        <div class="layui-inline layui-input-block">
            <button
                    class="layui-btn  layui-icon layui-icon-search"
                    type="button" lay-filter="doSearch" id="doSearch">查询
            </button>
            <button
                    class="layui-btn layui-btn-warm layui-icon layui-icon-refresh"
                    type="reset">重置
            </button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->
<!-- 数据表格开始 -->
<table class="layui-hide" id="commentTable" lay-filter="commentTable"></table>
<div style="display: none;" id="userToolBar">
    <button type="button" class="layui-btn layui-btn-sm"
            lay-event="delete">批量删除
    </button>
</div>
<div id="toolBar" style="display: none;">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->
<script src="plug/layui/layui.js"></script>
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
                elem: '#commentTable' //渲染的目标对象
                , url: '${pageContext.request.contextPath}/Comment/findAll' //数据接口
                , title: '店铺数据表' //数据导出时的标题
                , toolbar: "#userToolBar" //表头工具条
                , defaultToolbar: ['filter', 'print', 'exports']
                , cellMinWidth: 80  //设置 列的最小的默认宽度
                , page: true //是否启用分页
                , cols: [[
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'idComment', title: '评论id', sort: true,hide:true}
                    , {field: 'restaurantName', title: '被评论店铺名', sort: true}
                    , {field: 'userName', title: '用户名', sort: true}
                    , {field: 'context', title: '内容'}
                    , {field: 'time', title: '时间',sort:true}
                    , {field: 'score', title: '评分',sort:true}
                    , {field: 'numLike', title: '点赞数',sort:true}
                    , {field: 'idCommentReply', title: '回复评论的id'}
                    , {fixed: 'right', title: '操作', toolbar: '#toolBar', minWidth: 115}
                ]]
            });
            form.on("submit(doSearch)", function (data) {
                alert(JSON.stringify(data.field));
                var r_id=data.field.r_id;
                var user_id=data.field.user_id;
                var startTime=data.field.startTime;
                var endTime = data.field.endTime;
                table.reload('commentTable', {
                    url: '${pageContext.request.contextPath}/restaurant/findByExample'
                    , where: {
                        'r_id':r_id,
                        'user_id':user_id,
                        'startTime':startTime,
                        'endTime':endTime
                    } //设定异步数据接口的额外参数
                    //,height: 300
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , text: {none: '无数据'}
                    , done: function () {
                        alert("234");
                    }
                });
                return false;
            });
            table.on('toolbar(commentTable)', function (obj) {
                switch (obj.event) {
                    case 'delete':
                        layer.msg('删除');
                        break;
                }
                ;
            });
            var url;
            var mainIndex;

            //监听工具条
            table.on('tool(restaurantTable)', function (obj) {
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                if (layEvent === 'del') { //删除
                    layer.msg("删除");
                    layer.confirm('真的删除行么', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                }
            });
        });
</script>
</body>
</html>
