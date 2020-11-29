<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 28/11/2020
  Time: 下午6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
    <script>
        var contextPath = "${contextPath}";
    </script>
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
    <!-- 本页样式表 -->
    <link href="../css/detail.css" rel="stylesheet"/>
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
        <a class="blog-logo" href="home.jsp">找铺子</a>
        <!-- 导航菜单 -->
        <ul class="layui-nav" lay-filter="nav">
            <li class="layui-nav-item layui-this">
                <a href="home.jsp"><i class="fa fa-home fa-fw"></i>&nbsp;查看店铺</a>
            </li>
            <li class="layui-nav-item">
                <a href="upLoad.jsp"><i class="fa fa-file-text fa-fw"></i>&nbsp;上传店铺</a>
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
<!--    图片弹窗-->
<div id="windowPhoto">
    <div class="layui-carousel" id="detail-carousel" lay-filter="caroFilter"
         style="padding:0px;width: 100%;">
        <div carousel-item id="carousel">
        </div>
    </div>
</div>
<!--    地图弹窗-->
<div style="padding: 10px; margin:0; background-color: #F2F2F2;display: none;" id="windowMap">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <div id="containerMap" class="containerMap"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 主体（一般只改变这里的内容） -->
<div class="blog-body">
    <div class="blog-container">
        <blockquote class="layui-elem-quote sitemap layui-breadcrumb shadow">
            <a href="home.jsp" title="网站首页">网站首页</a>
            <a href="upLoad.jsp" title="文章专栏">文章专栏</a>
            <a><cite>基于layui的laypage扩展模块！</cite></a>
        </blockquote>
        <a id="userID" data = "${userID} "></a>
        <div class="blog-main">
            <div class="blog-main-left">
                <!-- 文章内容（使用Kingeditor富文本编辑器发表的） -->
                <div class="shop-detail shadow">
                    <div class="shop-detail-photo">
                        <div class="boxPhoto" id="shopPhoto">
                            <img  id="resImg" width="300px" style="margin: 0 auto;">
                        </div>
                    </div>
                    <div class="shop-detail-text">
                        <h1 class="shop-detail-text-name" id="restaurantName"></h1>
                        <div>
                            <div class="layui-inline">
                                <div id="gradeStar"></div>
                            </div>
                            <div class="layui-inline shop-detail-text-commentNum"><span id="commentNumber"></span>名用户参与评分
                            </div>
                        </div>
                        <div class="shop-detail-text-content">
                            <div class="content_low">
                                <label class="layui-inline shop-detail-text-label" for="shopAddress">地址</label>
                                <a href="javascript:void(0);">
                                    <div class="layui-inline" id="shopAddress"><span
                                            id="addressID"></span><img src="../images/locate.png"
                                                                       width="15px"></div>
                                </a>
                            </div>
                            <div class="content-line">
                                餐馆介绍：<span id="restaurant_intro"></span>
                            </div>
                            <div class="content-line">
                                推荐理由：<span id="commentReason"></span>
                            </div>
                        </div>

                    </div>


                </div>
                <!-- 评论区域 -->
                <div class="blog-module shadow" style="box-shadow: 0 1px 8px #a6a6a6;">
                    <fieldset class="layui-elem-field layui-field-title" style="margin-bottom:0">
                        <legend>来说两句吧</legend>
                        <!--                        <span class="layui-inline star">评分：</span><div  id="star" class="layui-inline"></div>-->
                        <div class="layui-field-box">
                            <form class="layui-form blog-editor" action="">
                                <span class="layui-inline star">评分：</span><div  id="star" class="layui-inline" name="star"></div>
                                <div class="layui-form-item">
                                    <textarea name="editorContent" lay-verify="content" id="remarkEditor"
                                              placeholder="请输入内容" class="layui-textarea layui-hide"></textarea>
                                </div>
                                <div class="layui-form-item">
                                    <button class="layui-btn" lay-submit="formRemark" lay-filter="formRemark">提交评论
                                    </button>
                                </div>
                            </form>
                        </div>
                    </fieldset>
                    <div class="blog-module-title">最新评论</div>
                    <ul class="comment" id="comment">
                        <li>
<%--                            <div class="comment-parent">--%>
<%--                                <img src="../images/Absolutely.jpg" alt="absolutely"/>--%>
<%--                                <div class="info">--%>
<%--                                    <span class="username">Absolutely</span>--%>
<%--                                    评分：<span class="score">4.5</span>--%>
<%--                                    <span class="time">2017-03-18 18:46:06</span>--%>
<%--                                    <div style="float: right"><img src="../images/heart.svg" width="16px"--%>
<%--                                                                   class="commentHeart1" style="display: inline-block;">--%>
<%--                                        <img src="../images/heart.png" width="16px" class="commentHeart2"--%>
<%--                                             style="display: none">--%>
<%--                                        <span class="commentNum" style="float: right">98</span></div>--%>
<%--                                </div>--%>
<%--                                <div class="content">--%>
<%--                                    我为大家做了模拟评论功能！还有，这个评论功能也可以改成和留言一样，但是目前没改，有兴趣可以自己改--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="replycontainer layui-hide">--%>
<%--                                <form class="layui-form" action="">--%>
<%--                                    <div class="layui-form-item">--%>
<%--                                        <textarea name="replyContent" lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;"></textarea>--%>
<%--                                    </div>--%>
<%--                                    <div class="layui-form-item">--%>
<%--                                        <button class="layui-btn layui-btn-mini" lay-submit="formReply" lay-filter="formReply">提交</button>--%>
<%--                                    </div>--%>
<%--                                </form>--%>
<%--                            </div>--%>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="blog-main-right">
                <!--右边悬浮 平板或手机设备显示-->
                <div class="category-toggle"><i class="fa fa-chevron-left"></i></div>
                <!--这个div位置不能改，否则需要添加一个div来代替它或者修改样式表-->
                <div class="article-category shadow">
                    <div class="article-category-title">分类导航</div>
                    <!-- 点击分类后的页面和artile.html页面一样，只是数据是某一类别的 -->
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">ASP.NET MVC</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">SQL Server</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">Entity Framework</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">Web前端</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">C#基础</a>
                    <a href="javascript:layer.msg(&#39;切换到相应分类&#39;)">杂文随笔</a>
                    <div class="clear"></div>
                </div>
                <div class="blog-module shadow">
                    <div class="blog-module-title">相似文章</div>
                    <ul class="fa-ul blog-module-ul">
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                        </li>
                        <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                        </li>
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
                    </ul>
                </div>
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
    <li class="layui-nav-item layui-this">
        <a href="home.jsp"><i class="fa fa-home fa-fw"></i>&nbsp;查看店铺</a>
    </li>
    <li class="layui-nav-item">
        <a href="upLoad.jsp"><i class="fa fa-file-text fa-fw"></i>&nbsp;上传店铺</a>
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
<!--遮罩-->
<div class="blog-mask animated layui-hide"></div>
<!-- layui.js -->
<script src="../plug/layui/layui.js"></script>
<!-- 自定义全局脚本 -->
<script src="../js/global.js"></script>
<!-- 高德地图jsAPI-->
<script src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=2.0&key=9098fae6e7eb5d81455c1f648d61365f"></script>
<!-- 本页脚本 -->
<script src="../js/detail.js"></script>
</body>
</html>
