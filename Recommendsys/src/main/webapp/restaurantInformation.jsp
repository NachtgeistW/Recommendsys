<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 3/10/2020
  Time: 上午8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/admin/table.css">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <base href="<%=basePath%>">　
</head>
<body>
<!-- 搜索条件开始 -->
<!--<fieldset class="layui-elem-field layui-field-title"-->
<!--          style="margin-top: 0px">-->
<!--    <legend>店铺信息查询</legend>-->
<!--</fieldset>-->
<form class="layui-form" action="restaurant/findByExample" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">店铺名</label>
            <div class="layui-input-inline">
                <input name="r_name" id="r_name" class="layui-input" type="text" autocomplete="off">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">店铺地址</label>
            <div class="layui-input-inline">
                <input name="r_address" id="r_address" class="layui-input" type="text"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">菜系类型</label>
            <div class="layui-input-inline">
                <input name="r_cuisine" id="r_cuisine" class="layui-input" type="text"
                       autocomplete="off">
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
                    type="submit" lay-submit lay-filter="doSearch" id="doSearch">查询
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
<table class="layui-hide" id="restaurantTable" lay-filter="restaurantTable"></table>
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
<div style="display: none; padding: 20px" class="pop-box" id="saveOrUpadteDiv">
    <form class="layui-form " action="" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-inline">
            <label class="layui-form-label">店铺名</label>
            <div class="layui-input-inline">
                <input name="name" class="layui-input" type="text" autocomplete="off"
                       lay-verify="required" id="data_name">
            </div>
        </div>
        <div class="layui-upload layui-input-inline">
            <blockquote class="layui-elem-quote layui-quote-nm" style="margin: 5px;width: 228px">
                预览图：
                <div class="layui-upload-list" name="resturantImage" id="edit_photo"></div>
            </blockquote>
            <button type="button" class="layui-btn" id="btnPhotos" style="width: 265px">多图片上传</button>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">菜系</label>
            <div class="layui-input-inline">
                <input name="typeOfCuisine" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="data_cuisine">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">店铺地址</label>
            <div class="layui-input-inline">
                <input name="address" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="data_address">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">店铺简介</label>
            <div class="layui-input-inline">
                <textarea name="intro" class="layui-textarea" autocomplete="off"
                          id="data_introduction"></textarea>
            </div>

        </div>
        <div class="layui-inline">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline">
                <textarea name="comment" class="layui-textarea" autocomplete="off" id="edit_remark"></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button
                        class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-heart-fill"
                        type="button" lay-filter="edit-save" lay-submit="">保存
                </button>
                <button
                        class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-heart"
                        type="reset">重置
                </button>
            </div>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层开始 -->
<div style="display: none; padding: 20px" class="pop-box" id="addDiv">
    <form class="layui-form " action="restaurant/save" method="post" lay-filter="dataFrm" id="addFrm">
        <div class="layui-inline">
            <label class="layui-form-label">店铺名</label>
            <div class="layui-input-inline">
                <input name="name" class="layui-input" type="text" autocomplete="off"
                       lay-verify="required" id="name">
            </div>
        </div>
<%--        <div class="layui-upload layui-input-inline">--%>
<%--            <blockquote class="layui-elem-quote layui-quote-nm" style="margin: 5px;width: 228px">--%>
<%--                预览图：--%>
<%--                <div class="layui-upload-list" name="resturantImage" id="edit_photo"></div>--%>
<%--            </blockquote>--%>
<%--            <button type="button" class="layui-btn" id="btnPhotos" style="width: 265px">多图片上传</button>--%>
<%--        </div>--%>
        <div class="layui-inline">
            <label class="layui-form-label">菜系</label>
            <div class="layui-input-inline">
                <input name="typeOfCuisine" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="typeOfCuisine">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">店铺地址</label>
            <div class="layui-input-inline">
                <input name="address" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="address">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">店铺简介</label>
            <div class="layui-input-inline">
                <textarea name="intro" class="layui-textarea" autocomplete="off"
                          id="intro"></textarea>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline">
                <textarea name="comment" class="layui-textarea" autocomplete="off" id="comment"></textarea>
            </div>
        </div>
        <div class="layui-inline">
        <label class="layui-form-label">推荐人id</label>
        <div class="layui-input-inline">
            <input name="idRecommandedUser" class="layui-input" type="text"
                   autocomplete="off" lay-verify="required" id="idRecommandedUser">
        </div>
    </div>
        <div class="layui-inline">
            <label class="layui-form-label">推荐理由</label>
            <div class="layui-input-inline">
                <input name="recommandReason" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" id="recommandReason">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">推荐时间</label>
            <div class="layui-input-inline">
                <textarea name="recommendTime" class="layui-input" autocomplete="off"
                          id="recommendTime"></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button
                        class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-heart-fill"
                        type="submit" lay-filter="add-save" lay-submit="">保存
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
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="layui/layui.js"></script>
<script type="text/javascript">
    function dateToString(time) {
        var datetime = new Date();
        datetime.setTime(time);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1;
        var date = datetime.getDate();
        var hour = datetime.getHours();
        /*对月 日 时 分 秒 小于10的时候的处理  --小于 10 时前面加 0*/
        if (month <= 9) {
            month = "0" + month;
        }
        if (date <= 9) {
            date = "0" + date
        }
        if (hour <= 9) {
            hour = "0" + hour;
        }
        var minute = datetime.getMinutes();
        if (minute <= 9) {
            minute = "0" + minute;
        }
        var second = datetime.getSeconds();
        if (second <= 9) {
            second = "0" + second;
        }
        return year + "-" + month + "-" + date + " " + hour + ":" + minute
            + ":" + second;
    };
</script>
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
            laydate.render({
                elem: '#recommendTime'
            });
            //渲染数据表格
            var tableIns = table.render({
                elem: '#restaurantTable' //渲染的目标对象
                , url: '${pageContext.request.contextPath}/restaurant/findAll'//数据接口
                , title: '店铺数据表' //数据导出时的标题
                , toolbar: "#userToolBar" //表头工具条
                , defaultToolbar: ['filter', 'print', 'exports']
                , cellMinWidth: 80  //设置 列的最小的默认宽度
                , page: true //是否启用分页
                , cols: [[
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'idRestaurant', title: '店铺ID', sort: true}
                    , {field: 'name', title: '店铺名'}
                    , {field: 'resturantImage', title: '图片', templet: '#rPhoto'}
                    , {field: 'typeOfCuisine', title: '菜系名称'}
                    , {field: 'address', title: '店铺地址'}
                    , {field: 'intro', title: '店铺简介'}
                    , {field: 'comment', title: '店铺备注'}
                    , {field: 'userName', title: '推荐人'}
                    , {field: 'recommandReason', title: '推荐理由'}
                    , {field: 'recommendTime', title: '推荐时间',templet:function(d){
                           return  ''+dateToString(d.recommendTime)+''
                        } , sort: true}
                    , {fixed: 'right', title: '操作', toolbar: '#toolBar', minWidth: 115}
                ]]
                , text: "数据加载失败"

            });
            table.on('toolbar(restaurantTable)', function (obj) {
                switch (obj.event) {
                    case 'add':
                        openAddData();
                        break;
                    case 'delete':
                        layer.msg('删除');
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
                    alert(JSON.stringify({
                        "idRestaurant": data.idRestaurant,
                        "name": $("#data_name").val(),
                        "intro": $("#data_introduction").val(),
                        "typeOfCuisine": $("#data_cuisine").val(),
                        "address": $("#data_address").val(),
                        "idRecommandedUser": data.idRecommandedUser,
                        "recommandReason": data.recommandReason,
                        "isAuditPassed": "1",
                        "comment": data.comment,
                        "resturantImage": "sss",
                        "recommendTime": data.recommendTime
                    }));
                    var data1={"idRestaurant": data.idRestaurant,"idRecommandedUser": data.idRecommandedUser,"recommandReason":data.recommandReason,"comment": data.comment,"name": $("#data_name").val(),"intro":$("#data_introduction").val(),"typeOfCuisine":$("#data_cuisine").val(),"address":$("#data_address").val()};
                    // var data1={"address":$("#data_address").val()};
                    $.ajax({
                        type: "POST",
                        url: '${pageContext.request.contextPath}/restaurant/update',
                        contentType:"application/json;charset=UTF-8",
                        dataType: "json",
                        data:data1,
                        // data:{
                            // "idRestaurant": idRestaurant,
                            // "name": $("#data_name").val(),
                            // "intro":  $("#data_introduction").val(),
                            // "typeOfCuisine": encodeURI($("#data_cuisine").val()),
                            // "address": $("#data_address").val(),
                            // "idRecommandedUser": idRecommandedUser,
                            // "recommandReason": recommandReason,
                            // "isAuditPassed": "1",
                            // "comment": comment,
                            // "resturantImage": "sss",
                            // "recommendTime": recommendTime,
                        //     、
                        //
                        // },
                        // data:'{"idRestaurant": idRestaurant,"name": $("#data_name").val(),"intro":$("#data_introduction").val(),"typeOfCuisine":$("#data_cuisine").val(),"address":$("#data_address").val(),"idRecommandedUser":idRecommandedUser,"recommandReason":recommandReason,"isAuditPassed":1,"comment": comment,"resturantImage": "sss","recommendTime": recommendTime}',
                        // data:JSON.stringify('{"idRestaurant": idRestaurant,"name": $("#data_name").val(),"intro":$("#data_introduction").val(),"typeOfCuisine":$("#data_cuisine").val(),"address":$("#data_address").val(),"idRecommandedUser":idRecommandedUser,"recommandReason":recommandReason,"isAuditPassed":1,"comment": comment,"resturantImage": "sss","recommendTime": recommendTime}'),
                            // data:'{"name": $("#data_name").val(),"intro":$("#data_introduction").val(),"typeOfCuisine":$("#data_cuisine").val(),"address":$("#data_address").val()}',
                        success: function (data) {
                            console.log("1111111111111");
                        },
                        error: function (data) {
                            console.log("22222222222222");
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
            //监听工具条
            table.on('tool(restaurantTable)', function (obj) {
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                if (layEvent === 'del') { //删除
                    layer.msg("删除");
                    layer.confirm('真的删除行么', function (index) {
                        // alert(data.idRestaurant);
                        var id = data.idRestaurant;
                        $.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath}/restaurant/delete",
                            data: {"idRestaurant": id},
                            dataType: "json",
                            success: function (data) {
                                if (data == "true") {//删除成功：移除删除行
                                    console.log("1111111111111");
                                } else {//删除失败
                                    console.log("22222222222222");
                                }
                                ;
                            },
                        });

                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if (layEvent === 'edit') { //编辑
                    openUpadteData(data);
                }
            });

            //打开添加页面
            function openAddData() {
                mainIndex = layer.open({
                    type: 1,
                    title: "添加用户",
                    content: $("#addDiv"),
                    area: 'auto',
                    success: function (index) {
                        form.on("submit(add-save)", function (data) {
                            alert(JSON.stringify(data.field));
                            var name = data.field.name;
                            var typeOfCuisine = data.field.typeOfCuisine;
                            var address = data.field.address;
                            var intro = data.field.intro;
                            var comment = data.field.comment;
                            var idRecommandedUser = data.field.idRecommandedUser;
                            var recommandReason = data.field.recommandReason;
                            var recommendTime = data.field.recommendTime;
                            table.reload('restaurantTable', {
                                url: '${pageContext.request.contextPath}/restaurant/save'
                                , where: {
                                    'name': name,
                                    'typeOfCuisine': typeOfCuisine,
                                    'address': address,
                                    'intro': intro,
                                    'comment': comment,
                                    'idRecommandedUser': idRecommandedUser,
                                    'recommandReason': recommandReason,
                                    'recommendTime': recommendTime,
                                } //设定异步数据接口的额外参数
                                //,height: 300
                                , page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                                , text: {none: '无数据'}

                            });
                            return false;
                        });
                    }
                });
            }

            //多图片上传
            // upload.render({
            //     elem: '#btnPhotos'
            //     ,url: '## ' //改成您自己的上传接口
            //     ,multiple: true
            //     ,before: function(obj){
            //         //预读本地文件示例，不支持ie8
            //         obj.preview(function(index, file, result){
            //             $('#edit_photo').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
            //         });
            //     }
            //     ,done: function(res){
            //         //上传完毕
            //     }
            // });
            form.on("submit(doSearch)", function (data) {
                // alert(JSON.stringify(data.field));
                var r_name = data.field.r_name;
                var r_address = data.field.r_address;
                var r_cuisine = data.field.r_cuisine;
                var startTime = data.field.startTime;
                var endTime = data.field.endTime;
                table.reload('restaurantTable', {
                    url: '${pageContext.request.contextPath}/restaurant/findByExample'
                    , where: {
                        'r_name': r_name,
                        'r_address': r_address,
                        'r_cuisine': r_cuisine,
                        'startTime': startTime,
                        'endTime': endTime
                    } //设定异步数据接口的额外参数
                    //,height: 300
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , text: {none: '无数据'}

                });
                return false;
            });
            //保存

        });
</script>
<script id="rPhoto" type="text/html">
    {{#    if(d.resturantImage.length  == 0){   }}
    {{ " " }}
    {{#   }else{   }}
    {{#   var srr=d.resturantImage.split("|");   }}
    {{#   for(var j in srr) { srr[j];  }}
    <div style="margin:0 10px; display:inline-block !important; display:inline;  max-width:70px; max-height:50px;">
        <img style=" max-width:70px; max-height:50px;" src="{{srr[j]}}"/>
    </div>
    {{#  }  }}
    {{#  }  }}
</script>
</body>
</html>