<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 15/11/2020
  Time: 下午5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; Charset=gb2312">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <title>找铺子</title>
    <link rel="shortcut icon" href="../images/Logo_40.png" type="image/x-icon">
    <!--Layui-->
    <link href="../plug/layui/css/layui.css" rel="stylesheet"/>
    <!--font-awesome-->
    <link href="../plug/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
    <!--全局样式表-->
    <link href="../css/global.css" rel="stylesheet"/>
    <!--本页样式表-->
    <link href="../css/upLoad.css" rel="stylesheet"/>
    <!--    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>-->
    <!--    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>-->
</head>
<body>
<!-- 导航 -->
<nav class="blog-nav layui-header">
    <div class="blog-container">
        <!-- QQ互联登陆 -->
        <a href="javascript:;" class="blog-user">
            <i class="fa fa-qq"></i>
        </a>
        <a href="javascript:;" class="blog-user layui-hide">
            <img src="../images/Absolutely.jpg" alt="Absolutely" title="Absolutely"/>
        </a>
        <!-- 找铺子 -->
        <a class="blog-logo" href="home.html">找铺子</a>
        <!-- 导航菜单 -->
        <ul class="layui-nav" lay-filter="nav">
            <li class="layui-nav-item">
                <a href="home.html"><i class="fa fa-home fa-fw"></i>&nbsp;查看店铺</a>
            </li>
            <li class="layui-nav-item  layui-this">
                <a href="upLoad.html"><i class="fa fa-file-text fa-fw"></i>&nbsp;上传店铺</a>
            </li>
            <li class="layui-nav-item">
                <a href="resource.html"><i class="fa fa-tags fa-fw"></i>&nbsp;热门店铺</a>
            </li>
            <li class="layui-nav-item">
                <a href="timeline.html"><i class="fa fa-hourglass-half fa-fw"></i>&nbsp;签到</a>
            </li>
            <li class="layui-nav-item">
                <a href="about.html"><i class="fa fa-info fa-fw"></i>&nbsp;个人中心</a>
            </li>
        </ul>
        <!-- 手机和平板的导航开关 -->
        <a class="blog-navicon" href="javascript:;">
            <i class="fa fa-navicon"></i>
        </a>
    </div>
</nav>
<div id="windowUploadImg" style="display: none">
    <div class="layui-upload">
        <button type="button" class="layui-btn layui-btn-normal" id="photoListBtn">选择多图片</button>
        <div class="layui-upload-list">
            <table class="layui-table" width="100%" style="table-layout:fixed">
                <thead>
                <tr>
                    <th width="40">文件名</th>
                    <th width="20">大小</th>
                    <th width="20">状态</th>
                    <th width="20">操作</th>
                </tr>
                </thead>
                <tbody id="photoListTable"></tbody>
            </table>
        </div>
    </div>
    <button
            class="layui-btn layui-btn-normal"
            id="btnUploadImg"
            type="button" lay-filter="btnUploadImg" lay-submit="">保存
    </button>
    <input type="hidden" id="isCoverSourceFile" name="isCoverSourceFile" value="">
    <input type="hidden" id="schoolId" name="schoolId" value="">
</div>
<!-- 主体（一般只改变这里的内容） -->
<div class="blog-body">
    <div class="blog-container">
        <blockquote class="layui-elem-quote sitemap layui-breadcrumb shadow">
            <a href="home.html" title="网站首页">网站首页</a>
            <a><cite>文章专栏</cite></a>
        </blockquote>
        <div class="blog-main">
            <div class="blog-main-left">
                <!--                    <div class="shadow" style="text-align:center;font-size:16px;padding:40px 15px;background:#fff;margin-bottom:15px;">-->
                <!--                        未搜索到与【<span style="color: #FF5722;">keywords</span>】有关的文章，随便看看吧！-->
                <!--                    </div>-->
                <div class="article shadow">
                    <div style=" padding: 20px;text-align: center" class="pop-box" id="saveOrUpadteDiv">
                        <form class="layui-form " action="" lay-filter="dataFrm" id="dataFrm">
                            <div class="layui-form-item upload-line">
                                <label class="layui-form-label">店铺名</label>
                                <div class="layui-input-inline">
                                    <input name="name" class="layui-input" type="text" autocomplete="off"
                                           lay-verify="required" id="data_name">
                                </div>
                            </div>
                            <div class="layui-form-item upload-line">
                                <label class="layui-form-label">菜系</label>
                                <div class="layui-input-inline">
                                    <input name="typeOfCuisine" class="layui-input" type="text"
                                           autocomplete="off" lay-verify="required" id="data_cuisine">
                                </div>
                            </div>
                            <div class="layui-form-item upload-line">
                                <label class="layui-form-label">店铺地址</label>
                                <div class="layui-input-inline">
                                    <input name="address" class="layui-input" type="text"
                                           autocomplete="off" lay-verify="required" id="data_address">
                                </div>
                            </div>
                            <div class="layui-form-item upload-line">
                                <label class="layui-form-label">店铺简介</label>
                                <div class="layui-input-inline">
                <textarea name="intro" class="layui-textarea" autocomplete="off"
                          id="data_introduction"></textarea>
                                </div>

                            </div>
                            <div class="layui-form-item upload-line">
                                <label class="layui-form-label">备注</label>
                                <div class="layui-input-inline">
                                    <textarea name="comment" class="layui-textarea" autocomplete="off"
                                              id="edit_remark"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item" style="text-align: center;">
                                <!--                                    <div class="layui-input-block">-->
                                <button
                                        class="layui-btn layui-btn-normal "
                                        id="edit-save"
                                        type="button" lay-filter="edit-save" lay-submit="">保存
                                </button>
                                <button
                                        class="layui-btn layui-btn-warm "
                                        type="reset">重置
                                </button>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="blog-main-right">
                <div class="blog-search">
                    <form class="layui-form" action="">
                        <div class="layui-form-item">
                            <div class="search-keywords  shadow">
                                <input type="text" name="keywords" lay-verify="required" placeholder="输入关键词搜索"
                                       autocomplete="off" class="layui-input">
                            </div>
                            <div class="search-submit  shadow">
                                <a class="search-btn" lay-submit="formSearch" lay-filter="formSearch"><i
                                        class="fa fa-search"></i></a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="article-category shadow">
                    <div class="article-category-title">分类导航</div>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">ASP.NET MVC</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">SQL Server</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">Entity Framework</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">Web前端</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">C#基础</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">杂文随笔</a>
                    <div class="clear"></div>
                </div>
                <div class="blog-module shadow">
                    <div class="blog-module-title">作者推荐</div>
                    <ul class="fa-ul blog-module-ul">
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">Web安全之跨站请求伪造CSRF</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">ASP.NET MVC 防范跨站请求伪造（CSRF）</a>
                        </li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">C#基础知识回顾-扩展方法</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">一步步制作时光轴（一）（HTML篇）</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">一步步制作时光轴（二）（CSS篇）</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">一步步制作时光轴（三）（JS篇）</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">写了个Win10风格快捷菜单！</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">ASP.NET MVC自定义错误页</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">ASP.NET
                            MVC制作404跳转（非302和200）</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                        </li>
                    </ul>
                </div>
                <div class="blog-module shadow">
                    <div class="blog-module-title">随便看看</div>
                    <ul class="fa-ul blog-module-ul">
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">一步步制作时光轴（一）（HTML篇）</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">ASP.NET
                            MVC制作404跳转（非302和200）</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">ASP.NET MVC 防范跨站请求伪造（CSRF）</a>
                        </li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">一步步制作时光轴（三）（JS篇）</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                        </li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">一步步制作时光轴（二）（CSS篇）</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">写了个Win10风格快捷菜单！</a></li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">常用正则表达式</a></li>
                    </ul>
                </div>
                <!--右边悬浮 平板或手机设备显示-->
                <div class="category-toggle"><i class="fa fa-chevron-left"></i></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!-- 底部 -->
<footer class="blog-footer">
</footer>
<!--侧边导航-->
<ul class="layui-nav layui-nav-tree layui-nav-side blog-nav-left layui-hide" lay-filter="nav">
    <li class="layui-nav-item ">
        <a href="home.html"><i class="fa fa-home fa-fw"></i>&nbsp;查看店铺</a>
    </li>
    <li class="layui-nav-item layui-this">
        <a href="upLoad.html"><i class="fa fa-file-text fa-fw"></i>&nbsp;上传店铺</a>
    </li>
    <li class="layui-nav-item">
        <a href="resource.html"><i class="fa fa-tags fa-fw"></i>&nbsp;热门店铺</a>
    </li>
    <li class="layui-nav-item">
        <a href="timeline.html"><i class="fa fa-road fa-fw"></i>&nbsp;签到</a>
    </li>
    <li class="layui-nav-item">
        <a href="about.html"><i class="fa fa-info fa-fw"></i>&nbsp;个人中心</a>
    </li>
</ul>
<!--分享窗体-->
<div class="blog-share layui-hide">
    <div class="blog-share-body">
        <div style="width: 200px;height:100%;">
            <div class="bdsharebuttonbox">
                <a class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                <a class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                <a class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                <a class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a>
            </div>
        </div>
    </div>
</div>
<%--jquery.js--%>
<script src="../js/jquery.min.js"></script>
<!--遮罩-->
<div class="blog-mask animated layui-hide"></div>
<!-- layui.js -->
<script src="../plug/layui/layui.js"></script>
<!-- 全局脚本 -->
<script src="../js/global.js"></script>
<script type="text/javascript">
    layui.use(['table', 'jquery', 'layer', 'form', 'laydate', 'upload'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var upload = layui.upload;

        $(function () {
            // var $btn = document.getElementById("#edit-save");
            // var $table = document.getElementById("#dataFrm");
            var $name = $("#data_name");
            var $typeOfCuisine = $("#data_cuisine");
            var $adress = $("#data_address");
            var $intro = $("#data_introduction");
            var $comment = $("#edit_remark");
            var restaurantID="";


            $("#edit-save").on("click", function () {
                // if($name.val()==""||$typeOfCuisine.val()==""||$adress.val()==""||$intro.val()==""){
                //     layer.msg("存在必填项未填");
                // }else{
                // }
                var data1 = {
                    "name": $name.val(),
                    "typeOfCuisine": $typeOfCuisine.val(),
                    "address": $adress.val(),
                    "intro": $intro.val(),
                    "comment": $comment.val()
                };
                $.ajax({
                        url: '${pageContext.request.contextPath}/restaurant/save',
                        type: "POST",
                        contentType: "application/json;charset=UTF-8",
                        dataType: "json",
                        data: JSON.stringify(data1),
                        success: function (data) {
                            $("input[type=reset]").trigger("click");
                            console.log("succeed");
                            restaurantID = data.msg;
                            layer.confirm('上传成功！是否继续上传图片？', {
                                btn: ['确定', '取消'] //可以无限个按钮
                            }, function(index, layero){
                                //按钮1-确定
                                uploadImg(restaurantID);
                                layer.close(index);
                            }, function(index){
                                //按钮2-取消
                                layer.close(index);
                                location.reload();
                            });
                        },
                        error: function (data) {
                            console.log("fail");
                        },
                    }
                )
            })
        })

        function uploadImg(restaurantID) {
            layer.open({
                type: 1,
                area: ["400px", "700px"],
                // area:"auto",
                title: "上传图片",
                scrollbar: false,
                content: $("#windowUploadImg")
                ,success: function ( ) {
                    var demoListView = $('#photoListTable')
                        ,uploadListIns = upload.render({
                        elem: '#photoListBtn'
                        ,url: '${pageContext.request.contextPath}/restaurant/uploadImg' //改成您自己的上传接口
                        ,accept: 'images'
                        ,acceptMime:'image/*'
                        // ,method: 'post'
                        ,multiple: true
                        ,auto: false
                        // ,async: false
                        ,size:2048
                        ,bindAction: '#btnUploadImg'
                        ,data:{"id":restaurantID}
                        ,choose: function(obj){
                            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                            //读取本地文件
                            obj.preview(function(index, file, result){
                                var tr = $(['<tr id="upload-'+ index +'">'
                                    ,'<td><div class="layui-table-cell laytable-cell-1-0-3"> '+ file.name +'</div></td>'
                                    ,'<td>'+ (file.size/1024).toFixed(1) +'kb</td>'
                                    ,'<td>等待上传</td>'
                                    ,'<td>'
                                    ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                                    ,'</td>'
                                    ,'</tr>'].join(''));

                                //单个重传
                                tr.find('.demo-reload').on('click', function(){
                                    obj.upload(index, file);
                                });

                                //删除
                                tr.find('.demo-delete').on('click', function(){
                                    delete files[index]; //删除对应的文件
                                    tr.remove();
                                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                                });

                                demoListView.append(tr);
                            });
                        }
                        ,done: function(res, index, upload){
                            if(res.code == 0) { //上传成功
                                var tr = demoListView.find('tr#upload-' + index)
                                    , tds = tr.children();
                                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                                tds.eq(3).html(''); //清空操作
                                return delete this.files[index]; //删除文件队列已经上传成功的文件
                            }
                            this.error(index, upload,res.msg);
                        }
                        ,error: function(index, upload,msg){
                            var tr = demoListView.find('tr#upload-'+ index)
                                ,tds = tr.children();
                            tds.eq(2).html('<span style="color: #ff5722;">'+msg+'</span>');
                            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
                        }
                        ,allDone: function(obj){ //当文件全部被提交后，才触发
                            console.log(obj.total); //得到总文件数
                            console.log(obj.successful); //请求成功的文件数
                            console.log(obj.aborted); //请求失败的文件数
                            if(obj.successful==obj.total){
                                layer.msg(obj.total+"张图片都已上传成功！",{icon: 1, time: 1000});
                                setTimeout(function(){
                                    location.reload();
                                },1000);
                            }
                        }
                    });
                }
                , end: function () {
                    var windowUploadImg = document.getElementById("windowUploadImg");
                    windowUploadImg.style.display = "none";
                }
            });
        }


    });
</script>
</body>
</html>
