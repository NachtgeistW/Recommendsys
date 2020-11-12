<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 3/10/2020
  Time: 上午8:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="plug/layui/css/layui.css">
    <link rel="stylesheet" href="css/admin/table.css">

</head>
<body>
<table class="layui-hide" id="check_restaurant" lay-filter="check_restaurant"></table>



<!--<script type="text/html" id="barDemo">-->
<!--    <a class="layui-btn layui-btn-xs" lay-event="details">详情</a>-->
<!--</script>-->

<script type="text/html" id="checkboxTpl">
    <!-- 这里的 checked 的状态只是演示 -->
    <input type="checkbox" name="check" title="已处理" lay-filter="checkDemo">
</script>

<!-- 添加和修改的弹出层开始 -->
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
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use(['table', 'jquery', 'layer', 'form',], function () {
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;

        table.render({
            elem: '#check_restaurant'
            , url: '${pageContext.request.contextPath}/complain/checkRecord'
            , cellMinWidth: 100
            , cols: [[
                {type: 'numbers'}
                , {field: 'idComplainRecord', title: '举报记录编号', sort: true}
                , {field: 'restaurantName', title: '被举报店铺名', sort: true }
                , {field: 'userName', title: '举报用户名', sort: true}
                , {field: 'reason', title: '举报理由'}
                , {fixed: 'right', title: '是否处理', minWidth: 100, templet: '#checkboxTpl', unresize: true}
            ]]
            , page: true
        });


        //监听锁定操作
        form.on('checkbox(checkDemo)', function (obj) {
            mainIndex = layer.open({
                type: 1,
                title: "处理结果",
                content: $("#resultDiv"),
                area: ['400px', '450px'],
                success: function (index) {
                    url = "user/updateUser.action";
                }
            });
        });

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
    });
</script>
</body>
</html>
