<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 2/10/2020
  Time: 下午9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="plug/layui/css/layui.css">
</head>
<body>
<a href="userInformation.jsp">测试页面显示</a>
<a href="user/findAll">测试json具体数据</a>
<a href="AdminIndex.jsp">测试首页</a>
<a href="userPage/home.html">测试用户页面</a>
<p/>
<a href="login.jsp">测试登录</a>
<a href="admin/AdminIndex.html">测试首页html</a>
<a href="restaurant/findAll">测试餐馆json</a>
<a href="complain/findAll">测试举报json</a>
<a href="restaurant/findByExample?r_name=五">测试条件查询</a>
<button type="button" class="layui-btn" id="b1" >ss</button>
<div style="display: none; padding: 20px" class="pop-box" id="saveOrUpadteDiv">
    <div class="layui-upload">
        <button type="btnPhotos" class="layui-btn layui-btn-normal" id="photoListBtn">选择多图片</button>
        <div class="layui-upload-list">
            <table class="layui-table">
                <thead>
                <tr>
                    <th>文件名</th>
                    <th>大小</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="photoListTable"></tbody>
            </table>
        </div>
        <%--            <button type="button" class="layui-btn" id="testListAction">开始上传</button>--%>
    </div>
</div>
<script src="plug/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['table', 'jquery', 'layer', 'form', 'laydate', 'upload'],
        function () {
            var upload = layui.upload;
            var $ = layui.jquery;
            var  bt=document.getElementById('b1');
            bt.onclick=function () {
                openUpadteData();
            };
            function openUpadteData() {
                mainIndex = layer.open({
                    type: 1,
                    title: "修改",
                    content: $("#saveOrUpadteDiv"),
                    area: 'auto',
                });
                //多图片上传
                var demoListView = $('#photoListTable')
                    , uploadListIns = upload.render({
                    elem: '#photoListBtn'
                    , url: '${pageContext.request.contextPath}/restaurant/save' //改成您自己的上传接口
                    , accept: 'images'
                    , acceptMime: 'image/*'
                    , method: 'post'
                    , multiple: true
                    , auto: false
                    , size: 2048
                    , bindAction: '#edit-save'
                    ,before:function (obj) {

                    }
                    , choose: function (obj) {
                        var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                        //读取本地文件
                        obj.preview(function (index, file, result) {
                            var tr = $(['<tr id="upload-' + index + '">'
                                , '<td>' + file.name + '</td>'
                                , '<td>' + (file.size / 1024).toFixed(1) + 'kb</td>'
                                , '<td>等待上传</td>'
                                , '<td>'
                                , '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                                , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                                , '</td>'
                                , '</tr>'].join(''));

                            //单个重传
                            tr.find('.demo-reload').on('click', function () {
                                obj.upload(index, file);
                            });

                            //删除
                            tr.find('.demo-delete').on('click', function () {
                                delete files[index]; //删除对应的文件
                                tr.remove();
                                uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                            });

                            demoListView.append(tr);
                            return false;
                        });
                        return false;
                    }
                    , done: function (res, index, upload) {
                        if (res.files.file) { //上传成功
                            var tr = demoListView.find('tr#upload-' + index)
                                , tds = tr.children();
                            tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                            tds.eq(3).html(''); //清空操作
                            return delete this.files[index]; //删除文件队列已经上传成功的文件
                        }
                        this.error(index, upload);
                    }
                    , error: function (index, upload) {
                        var tr = demoListView.find('tr#upload-' + index)
                            , tds = tr.children();
                        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
                    }
                });
            }
        }
    )
</script>
</body>
</html>
