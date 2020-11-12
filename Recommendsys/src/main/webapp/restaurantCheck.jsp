<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 3/10/2020
  Time: 上午8:12
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
<table class="layui-hide" id="check_restaurant" lay-filter="check_restaurant"></table>



<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="details">详情</a>
</script>

<script type="text/html" id="checkboxTpl">
    <!-- 这里的 checked 的状态只是演示 -->
    <input type="checkbox" name="check" title="通过" lay-filter="checkDemo" >
</script>

<!-- 添加和修改的弹出层开始 -->
<div style="display: none; padding: 20px" class="pop-box" id="detailsDiv">
    <form class="layui-form " action="" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-inline">
            <label class="layui-form-label">店铺名</label>
            <div class="layui-input-inline">
                <input name="r_name" class="layui-input" type="text" autocomplete="off"
                       lay-verify="required" id="edit_name" disabled="disabled">
            </div>
        </div>
        <!--        <div class="layui-upload layui-input-inline">-->
        <!--            <blockquote class="layui-elem-quote layui-quote-nm" style="margin: 5px;width: 228px">-->
        <!--                预览图：-->
        <!--                <div class="layui-upload-list"name="r_photo" id="edit_photo"></div>-->
        <!--            </blockquote>-->
        <!--            <button type="button" class="layui-btn" id="btnPhotos" style="width: 265px">多图片上传</button>-->
        <!--        </div>-->
        <div class="layui-inline">
            <label class="layui-form-label">菜系</label>
            <div class="layui-input-inline">
                <input name="r_cuisine" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="edit_cuisine" disabled="disabled">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">店铺地址</label>
            <div class="layui-input-inline">
                <input name="r_address" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="edit_address" disabled="disabled">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">店铺简介</label>
            <div class="layui-input-inline">
                <textarea name="r_introduction" class="layui-textarea" autocomplete="off" id="edit_introduction" disabled="disabled"></textarea>
            </div>

        </div>
        <div class="layui-inline">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline">
                <textarea name="r_remark" class="layui-textarea" autocomplete="off" id="edit_remark" disabled="disabled"></textarea>
            </div>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->


<script src="plug/layui/layui.js"></script>
<script>
    layui.use(['table', 'jquery', 'layer', 'form',], function () {
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;

        table.render({
            elem: '#check_restaurant'
            , url: '${pageContext.request.contextPath}/restaurant/checkRecommend'
            , cellMinWidth: 100
            , cols: [[
                {type: 'numbers'}
                , {field: 'idRestaurant', title: '店铺ID', sort: true}
                , {field: 'name', title: '店铺名' }
                , {field: 'resturantImage', title: '图片'}
                , {field: 'intro', title: '店铺简介'}
                , {field: 'typeOfCuisine', title: '菜系名称'}
                , {field: 'address', title: '店铺地址'}
                , {field: 'comment', title: '店铺备注'}
                , {field: 'userName', title: '推荐人'}
                , {field: 'recommandReason', title: '推荐理由'}
                , {field: 'recommendTime', title: '推荐时间', sort:true }
                , {fixed: 'right', title: '详情', toolbar: '#barDemo'}
                , {fixed: 'right', title: '是否通过', minWidth: 100, templet: '#checkboxTpl', unresize: true}
            ]]
            , page: true
        });


        //监听通过操作
        form.on('checkbox(checkDemo)', function (obj) {
            var id = $(this).parents("tr").children("td:nth-child(2)").text();//获得当前行数据
            alert(id);
            layer.msg("通过");
            layer.confirm('确定通过？', function (index) {
                layer.close(index);
                //向服务端发送删除指令
            });
        });
        //打开修改页面
        function openDetails(data) {
            mainIndex = layer.open({
                type: 1,
                title: "详情",
                content: $("#detailsDiv"),
                area: ['325px', '350px'],
                success: function (layero,index) {
                    form.val("dataFrm", data);
                    url = "user/updateUser.action";
                }
            });
        };
        //监听行工具事件
        table.on('tool(check_restaurant)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'details'){
                openDetails(data);
            }
        });
    });
</script>
</body>
</html>
