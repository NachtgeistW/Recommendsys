<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 3/10/2020
  Time: 上午8:11
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
            <label class="layui-form-label">记录编号</label>
            <div class="layui-input-inline">
                <input name="record_id" id="record_id" class="layui-input" type="text" autocomplete="off">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">店铺id</label>
            <div class="layui-input-inline">
                <input name="r_id" id="r_id" class="layui-input" type="text"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">举报人id</label>
            <div class="layui-input-inline">
                <input name="user_id" id="user_id" class="layui-input" type="text"
                       autocomplete="off">
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
<table class="layui-hide" id="recordTable" lay-filter="recordTable"></table>
<div style="display: none;" id="userToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-sm"
            lay-event="delete">批量删除
    </button>
</div>
<div id="toolBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit"
       lay-filter="edit">编辑</a> <a
        class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->
<!-- 添加和修改的弹出层开始 -->
<!--添加弹窗-->
<div style="display: none; padding: 20px" class="pop-box" id="addDiv">
    <form class="layui-form " action="" lay-filter="addData" id="addData">
        <div class="layui-inline">
            <label class="layui-form-label">举报记录编号</label>
            <div class="layui-input-inline">
                <input name="record_id" class="layui-input" type="text" autocomplete="off"
                       lay-verify="required" id="edit_id">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">被举报店铺id</label>
            <div class="layui-input-inline">
                <input name="r_id" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="edit_r_id">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">举报人id</label>
            <div class="layui-input-inline">
                <input name="user_id" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="edit_user_id">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">举报理由</label>
            <div class="layui-input-inline">
                <input name="record_reason" class="layui-input" autocomplete="off" id="edit_reason"
                       autocomplete="off" lay-verify="required">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">处理结果</label>
            <div class="layui-input-inline">
                <input name="record_result" class="layui-input" autocomplete="off" id="edit_result"
                       autocomplete="off" lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button
                        class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-heart-fill"
                        type="button" lay-filter="add-save" lay-submit="">保存
                </button>
                <button
                        class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-heart"
                        type="reset">重置
                </button>
            </div>
        </div>
    </form>
</div>
<!--修改弹窗-->
<div style="display: none; padding: 20px" class="pop-box" id="resultDiv">
    <form class="layui-form " action="" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-inline">
            <label class="layui-form-label">处理结果</label>
            <div class="layui-input-inline">
                <textarea name="record_result" class="layui-textarea" autocomplete="off"  ></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button
                        class="layui-btn layui-btn-normal  layui-icon layui-icon-heart-fill"
                        type="button" lay-filter="edit-save" lay-submit="">确定
                </button>
                <button
                        class="layui-btn layui-btn-warm  layui-icon layui-icon-heart"
                        type="reset">重置
                </button>
            </div>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->
<script src="plug/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['table', 'jquery', 'layer', 'form', 'laydate', 'upload'],function () {
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
            elem: '#recordTable' //渲染的目标对象
            , url: '${pageContext.request.contextPath}/complain/findAll' //数据接口
            , title: '店铺数据表' //数据导出时的标题
            , toolbar: "#userToolBar" //表头工具条
            , defaultToolbar: ['filter', 'print', 'exports']
            , cellMinWidth: 80  //设置 列的最小的默认宽度
            , page: true //是否启用分页
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'idComplainRecord', title: '举报记录编号', sort: true}
                , {field: 'restaurantName', title: '被举报店铺名', sort: true }
                , {field: 'userName', title: '举报用户名', sort: true}
                , {field: 'reason', title: '举报理由'}
                , {field: 'result', title: '处理结果'}
                , {fixed: 'right', title: '操作', toolbar: '#toolBar', minWidth: 115}
            ]]
        });
        table.on('toolbar(recordTable)', function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddUser();
                    break;
                case 'delete':
                    layer.msg('删除');
                    break;
            }
            ;
        });
        var url;
        var mainIndex;

        form.on("submit(doSearch)", function (data) {
            alert(JSON.stringify(data.field));
            var record_id=data.field.record_id;
            var r_id=data.field.r_id;
            var user_id=data.field.user_id;
            table.reload('recordTable', {
                url: '${pageContext.request.contextPath}/restaurant/findByExample'
                , where: {
                    'record_id':record_id,
                    'r_id':r_id,
                    'user_id':user_id,
                } //设定异步数据接口的额外参数
                //,height: 300
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
                , text: {none: '无数据'}
                , done: function () {
                }
            });
            return false;
        });
        //打开添加页面
        function openAddUser() {
            mainIndex = layer.open({
                type: 1,
                title: "添加用户",
                content: $("#addDiv"),
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
                    $('#addData')[0].reset();
                    url = "user/addUser.action";
                }
            });
        }

        //打开修改页面
        function openUpadteAddUser(data) {
            mainIndex = layer.open({
                type: 1,
                title: "修改",
                content: $("#resultDiv"),
                area: ['400px', '450px'],
                success: function (layero, index) {
                    form.val("dataFrm", data);
                    url = "user/updateUser.action";
                }
            });
        }
        ;
        //保存
        form.on("submit(edit-save)", function (obj) {
            //alert(url);
            //序列号表单数据
            // var params = $("#dataFrm").serialize();
            // alert(params);
            // $.post("NewFile.jsp", params, function (obj) {
            //     layer.msg(obj);
            //关闭弹出层
            layer.close(mainIndex);
            //刷新数据表格
            tableIns.reload();
            // })
        });
        //监听工具条
        table.on('tool(recordTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.msg("删除");
                layer.confirm('真的删除行么', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if (layEvent === 'edit') { //编辑
                openUpadteAddUser(data);
            }
        });
    });
</script>
</body>
</html>
