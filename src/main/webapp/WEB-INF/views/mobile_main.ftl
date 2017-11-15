
<!DOCTYPE html>
<html>
    <head>
        <#include "include/mobile.header.ftl" >
    </head>
    
    <body ontouchstart>
    <#include "include/mobile.title.ftl" >
        <#--<header class="ui-header ui-header-positive ui-border-b">-->
            <#--<i class="ui-icon-return" onclick="history.back()"></i><h1>培正信息管理系统 </h1><button class="ui-btn">回首页</button>-->
        <#--</header>-->
        <!--<footer class="ui-footer ui-footer-btn">
            <ul class="ui-tiled ui-border-t">
                <li data-href="index.html" class="ui-border-r"><div>基础样式</div></li>
                <li data-href="ui.html" class="ui-border-r"><div>UI组件</div></li>
                <li data-href="js.html"><div>JS插件</div></li>
            </ul>
        </footer>-->
        <section class="ui-container">
<section id="list">
    <div class="demo-item">
        <div class="demo-block">
            <ul class="ui-list ui-list-pure ui-border-b">
                <li class="ui-border-t" data-href="centerTotal/mobile_index">
                  业绩统计
                </li>
                <li class="ui-border-t" data-href="icon.html">
                  事业部业绩汇总
                </li>
                <li class="ui-border-t" data-href="layout.html">
                  当月各中心收入
                </li>
            </ul>
        </div>
    </div>
</section>
        </section><!-- /.ui-container-->
    </body>
<script>
    $('.ui-list li,.ui-tiled li').click(function(){
        if($(this).data('href')){
            location.href= $(this).data('href');
        }
    });
</script>
</html>