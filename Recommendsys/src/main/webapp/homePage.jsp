<%--
  Created by IntelliJ IDEA.
  User: WX_78
  Date: 3/10/2020
  Time: 上午8:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">本月注册人数</div>
                <div class="layui-card-body">
                    <div id="container1" style="min-width:400px;height:400px"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header"></div>
                <div class="layui-card-body">
                    <div id="container2" style="min-width:400px;height:400px"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="layui/layui.js"></script>
<script src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script>
    //获取天数数组
    var newdate = new Date();//当前日期
    var month = newdate.getMonth() + 1;//当前月份
    // var day = newdate.getDate();//当前号数
    var day=30;
    var DAYS = (function () {
        var arr = [];
        for (var i = day; i > 0; i--) {
            arr.push(month + '-' + (i < 10 ? '0' + i : i + ''));
        }
        return arr.reverse();
    })();

    //随机注册数
    var register = (function () {
        var arr = [];
        for (var i = 0; i < day; i++) {
            var r = Math.ceil(Math.random() * 100);
            arr.push(r);
        }
        return arr.reverse();
    })();

    var chart1 = Highcharts.chart('container1', {
        chart: {
            type: 'area'
        },
        title: {
            text: ''
        },
        legend: {
            layout: 'vertical',
            align: 'left',
            verticalAlign: 'top',
            x: 150,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
        },
        credits: {
            enabled: false
        },
        xAxis: {
            allowDecimals: false,
            categories: DAYS,
            // plotBands: [{ // 标识出周末
            //     from: 4.5,
            //     to: 6.5,
            //     color: 'rgba(68, 170, 213, .2)'
            // }]
        },
        yAxis: {
            title: {
                text: '人数 单位'
            }
        },
        tooltip: {
            // shared: true,
            valueSuffix: ' 单位'
        },

        series: [{
            name: '注册人数',
            data: register
        }]
    });

    var chart2 = Highcharts.chart('container2', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '店铺评分百分比'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        credits: {
            enabled: false
        },
        plotOptions: {
            //饼状图
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    formatter: function() {
                        //Highcharts.numberFormat(this.percentage,2)格式化数字，保留2位精度
                        return '<b>'+ this.point.name +'</b>: '+Highcharts.numberFormat(this.percentage,2) +' %';
                    }
                }
            }
        },
        series: [{
            name: '比例',
            colorByPoint: true,
            data: [{
                type: 'pie',
                name: '市场份额'
            }],
        }]
    });
    $.ajax({
        type:"GET",
        url:'json/table/charts.json',//提供数据的Servlet
        success:function(data){
            //定义一个数组
            browsers = [],
                //迭代，把异步获取的数据放到数组中
                $.each(data,function(i,d){
                    browsers.push([d.name,d.y]);
                });
            //设置数据
            chart2.series[0].setData(browsers);
        },
        error:function(e){
            // alert(e);
        }
    });
</script>
</body>
</html>
