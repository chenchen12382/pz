<!DOCTYPE html>
<html>
    <head>
    <#include "include/mobile.header.ftl" >
    </head>
    
    <body ontouchstart>
    <#include "include/mobile.title.ftl" >

        <section class="ui-container">




<section id="table">
    <div class="demo-item">
        <p style="text-align: center;font-size: 25px">中心业绩统计</p>
        <div class="demo-block" >
            <table class="ui-table ui-border-tb" >
                <thead>
                <tr><th>事业部</th><th>中心</th><th>总订单</th><th>总收入</th></tr>
                </thead>
                <tbody>
            <#if centerTotals?has_content >
                <#list centerTotals as centerTotal>
                <tr><td>${centerTotal.district}</td><td>${centerTotal.center}</td><td>${centerTotal.shouldTotal}</td><td>${centerTotal.realTotal}</td></tr>
                </#list>
            </#if>
                </tbody>
            </table>
        </div>
    </div>
    <div class="demo-item">
        <p class="demo-desc">全框</p>
        <div class="demo-block">
            <table class="ui-table ui-border">
                <thead>
                <tr><th>已购买</th><th>状态</th><th>会员到期时间</th></tr>
                </thead>
                <tbody>
                <tr><td>2014257777</td><td>使用中</td><td>2016-02-06</td></tr>
                <tr><td>2014257777</td><td>待付款</td><td><a href="#">付款</a></td></tr>
                <tr><td>2014257777</td><td>待付款</td><td><a href="#">付款</a></td></tr>
                </tbody>
            </table>
        </div>
    </div>
</section>


        </section><!-- /.ui-container-->
        <script>
        $('.ui-list li,.ui-tiled li').click(function(){
            if($(this).data('href')){
                location.href= $(this).data('href');
            }
        });
        $('.ui-header .ui-btn').click(function(){
            location.href= 'index.html';
        });
        </script>
    </body>
<script>
    $(function () {
//        $.post("mobile_list", {}, function(result){
//                if(result != null){
//
//
////                      alert(result.length);
////                      var list = result.rows[0];
////                      alert(list.center);
//                }else{
//                    alert(result.resultMessage);
//                }
//
//        })
//    })
</script>

</html>