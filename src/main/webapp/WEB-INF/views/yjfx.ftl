<!DOCTYPE html>
<html>
<head>
    <title>客户关系管理系统</title>
    <script src="${ctx}/highcharts4/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${ctx}/highcharts4/js/highcharts.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/js/report.js"></script>
    <title>培正信息管理系统</title>
    <script>
        $(function () {

            var categories = [];
            var data = [];
            $.ajax({
                url: '/report_count/yjfx',
                dataType: 'json',
                data: {},
                async: false,
                success: function(resp) {
                    for (var i = 0; i < resp.district.length; i++) {
                        categories.push(resp.district[i]);
                        data.push(resp.total[i]);
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
                        text: '月平均降雨量'
                    },
                    subtitle: {
                        text: '数据来源: WorldClimate.com'
                    },
                    xAxis: {
                        categories: categories,
                        crosshair: true
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: '降雨量 (mm)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0
                        }
                    },
                    series: [{
                        name: '东京',
                        data: data
                    }]
                });
            });
        });
	</script>
</head>
<body style="margin: 1px">
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>