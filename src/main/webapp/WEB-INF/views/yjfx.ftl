<!DOCTYPE html>
<html>
<head>
    <title>客户关系管理系统</title>
    <script src="${ctx}/highcharts4/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${ctx}/highcharts4/js/highcharts.js" type="text/javascript"></script>
    <link href="${ctx}/assets/css/bootstrap.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui-1.3.3/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script>
        var ctx = "${ctx}/";
    </script>

    <title>培正信息管理系统</title>
    <script>

        $(document).ready(function() {
            //每隔3秒自动调用方法，实现图表的实时更新
            window.setInterval(s_time,1000*60*5);

        });

        function s_time() {
            var start = $("#start").datebox('getValue');
            var over = $("#over").datebox('getValue');

            if (start > over && over != "") {

                alert("开始时间不能大于结束时间！");
                return;
            }

            var time = {"start": start, "over": over};
            var categories = [];
            var data = [];
            $.ajax({
                type: "POST",
                url: '${ctx}/report_count/yjfx',
                dataType: 'json',
                data: time,
                async: false,
                success: function (resp) {
                    for (var i = 0; i < resp.district.length; i++) {
                        categories.push(resp.district[i]);
                        data.push(resp.total[i] / 10000);
                    }
//                    console.log(JSON.stringify(data));
                },
                error: function () {
                },
                before: function () {
                }
            })


            $(function () {
                $('#container').highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: '区域业绩分析'
                    },
                    subtitle: {
                        text: '数据来源: 培正信息管理系统'
                    },
                    xAxis: {
                        categories: categories,
                        crosshair: true,
                        labels : {
                            style : {
                                'fontSize' : '18px',
                                'fontFamily' : '黑体'

                            }
                        }
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: '总收入 (万元)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.1f} 万元</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0,
                            dataLabels:{
                                enabled:true, // dataLabels设为true
                                style:{
                                    color:'#50B432'
                                }
                            }
                        }
                    },
                    series: [{
                        name: '区域总收入(万元)',
                        data: data
                    }]
                });
            });



        }

        $(function () {
            var start = $("#start").datebox('getValue');
            var over = $("#over").datebox('getValue');

            if (start > over && over != "") {

                alert("开始时间不能大于结束时间！");
                return;
            }

            var time = {"start": start, "over": over};
            var categories = [];
            var data = [];
            $.ajax({
                type:"POST",
                url: '${ctx}/report_count/yjfx',
                dataType: 'json',
                data: time,
                async: false,
                success: function(resp) {
                    for (var i = 0; i < resp.district.length; i++) {
                        categories.push(resp.district[i]);
                        data.push(resp.total[i]/10000);
                    }
//                    console.log(JSON.stringify(data));
                },
                error: function() {},
                before: function() {}
            })


            $(function () {
                $('#container').highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: '区域业绩分析'
                    },
                    subtitle: {
                        text: '数据来源: 培正信息管理系统'
                    },
                    xAxis: {
                        categories: categories,
                        crosshair: true,
                        labels : {
                            style : {
                                'fontSize' : '18px',
                                'fontFamily' : '黑体'

                            }
                        }
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: '总收入 (万元)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.1f} 万元</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0,
                            dataLabels:{
                                enabled:true, // dataLabels设为true
                                style:{
                                    color:'#50B432'
                                }
                            }
                        }
                    },
                    series: [{
                        name: '区域总收入(万元)',
                        data: data
                    }]
                });
            });
        });
	</script>
</head>
<body style="margin: 1px">
<div id="tb">
    <div>
        &nbsp;开始时间：&nbsp;<input type="text" id="start" class="easyui-datebox" size="15" onkeydown="if(event.keyCode==13) searchCustomer()"/>
        &nbsp;结束时间：&nbsp;<input type="text" id="over" class="easyui-datebox" size="15" onkeydown="if(event.keyCode==13) searchCustomer()"/>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" type="submit" onclick="s_time()"  >搜索</a>
    </div>
</div>

<div id="container" style="min-width: 310px; height: 600px; margin: 0 auto"></div>

</body>
</html>