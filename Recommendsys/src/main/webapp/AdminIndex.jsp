<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 2/10/2020
  Time: 下午9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台</title>
    <link rel="stylesheet" href="plug/layui/css/layui.css">
    <link rel="stylesheet" href="css/admin/common-style.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <a data-url="${pageContext.request.contextPath}/homePage.jsp" data-id="homePage" data-title="首页" class="site-demo-active"  href="javascript:;" data-type="tabAdd"><div class="layui-logo">找铺子-后台管理</div></a>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <!--        <ul class="layui-nav layui-layout-left">-->
        <!--            <li class="layui-nav-item"><a href="">控制台</a></li>-->
        <!--            <li class="layui-nav-item"><a href="">商品管理</a></li>-->
        <!--            <li class="layui-nav-item"><a href="">用户</a></li>-->
        <!--            <li class="layui-nav-item">-->
        <!--                <a href="javascript:;">其它系统</a>-->
        <!--                <dl class="layui-nav-child">-->
        <!--                    <dd><a href="">邮件管理</a></dd>-->
        <!--                    <dd><a href="">消息管理</a></dd>-->
        <!--                    <dd><a href="">授权管理</a></dd>-->
        <!--                </dl>-->
        <!--            </li>-->
        <!--        </ul>-->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601458538542&di=82f07cfd0ca8dc4b782a3adc6884ff25&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201603%2F06%2F20160306204517_i4Se8.jpeg" class="layui-nav-img">
                    管理员A
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">店铺管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="${pageContext.request.contextPath}/restaurantCheck.jsp" data-id="restaurantCheck" data-title="店铺审核" class="site-demo-active"  href="javascript:;" data-type="tabAdd">店铺审核</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/restaurantInformation.jsp" data-id="restaurantInformation" data-title="店铺信息" class="site-demo-active"  href="javascript:;" data-type="tabAdd">店铺信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">举报管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="${pageContext.request.contextPath}/recordCheck.jsp" data-id="recordCheck" data-title="举报处理" class="site-demo-active"  href="javascript:;" data-type="tabAdd">举报处理</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/recordInformation.jsp" data-id="recordInformation" data-title="记录信息" class="site-demo-active"  href="javascript:;" data-type="tabAdd">记录管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a data-url="${pageContext.request.contextPath}/userInformation.jsp" data-id="userInformation" data-title="用户信息" data-type="tabAdd"  class="site-demo-active" >用户管理</a></li>
                <li class="layui-nav-item"><a data-url="${pageContext.request.contextPath}/commentInformation.jsp" data-id="commentInformation" data-title="评价信息"  href="javascript:;" data-type="tabAdd"  class="site-demo-active" >评价信息</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-tab layui-tab-brief" lay-allowClose="true" lay-filter="content">
            <ul class="layui-tab-title" >
                <li class="layui-this">首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <iframe src="homePage.jsp" scrolling="no" frameborder="0" style="width:100%;height: 100%"></iframe>
                </div>
            </div>

        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="plug/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

        var $ = layui.jquery;
        //触发事件
        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function (url, id, name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('content', {
                    title: name,
                    content: '<iframe data-frameid="' + id + '" scrolling="no" frameborder="0" src="${pageContext.request.contextPath}' + url + '" style="width:100%;height: 100%"></iframe>',
                    id: id //规定好的id
                })
                element.render('tab');

            },
            tabChange: function (id) {
                //切换到指定Tab项
                element.tabChange('content', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("content", id);//删除
            }
            , tabDeleteAll: function (ids) {//删除所有
                $.each(ids, function (i, item) {
                    element.tabDelete("content", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
                })
            }
        };


        //当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
        $('.site-demo-active').on('click', function () {
            var dataid = $(this);
            //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                //如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            } else {
                //否则判断该tab项是否以及存在

                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

    });
</script>
</body>
</html>

