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
        <#--<p class="demo-desc">选择数据时间</p>-->
        <div class="demo-block">
            <div class="ui-form ui-border-t">
                <form action="#">
                    <div class="ui-form-item ui-border-b">
                        <label>选择时间</label>
                        <div class="ui-select">
                            <select id="s_time"  >
                                <option  value="0">当日</option>
                                <option selected="" value="1">当月</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="demo-item">
        <p style="text-align: center;font-size: 25px">中心业绩统计</p>
        <div class="demo-block" >
            <table class="ui-table ui-border-tb" id="tb" >
                <thead>
                <tr><th>事业部</th><th>中心</th><th>总订单</th><th>总收入</th></tr>
                </thead>
                <tbody>
            <#if centerTotals?has_content >
                <#list centerTotals as centerTotal>
                <tr><td>${centerTotal.district!}</td><td>${centerTotal.center}</td><td>${centerTotal.orderTotal}</td><td>${centerTotal.realTotal}</td></tr>
                </#list>
            </#if>
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
            location.href= '${ctx}/mobile_main';
        });
        </script>
    </body>
<script>

    $(document).ready(function() {
        $('#s_time').change(function () {
            var cusId = $("#s_time").val();
            var data = {"time": cusId};
            $.post("m_list", data, function (result) {
                var newTr = '';
                if(result !=null){
                    for(var i=0;i<result.length;i++){
                        var district=result[i].district;
                        if(district==null){
                            district='';
                        }
                        newTr=newTr+'<tr><td>'+district+'</td><td>'
                        +result[i].center+'</td><td>'+result[i].orderTotal+'</td><td>'
                                +result[i].realTotal+'</td></tr>';

                    }
                }else {
                    alert("error");
                }

                $("#tb  tr:not(:first)").html("");

                $("#tb").append(newTr);

            })
        })
    })
</script>

</html>