<!DOCTYPE html>
<html>
    <head>
    <#include "../include/mobile.header.ftl" >
    </head>
    
    <body ontouchstart>
    <#include "../include/mobile.title.ftl" >

        <section class="ui-container">




<section id="table">
    <div class="demo-item">
        <p class="demo-desc">表单输入项3</p>
        <div class="demo-block">
            <div class="ui-form ui-border-t">
                <form action="#">
                    <div class="ui-form-item ui-form-item-pure ui-border-b">
                        <input type="text" placeholder="QQ号/手机号/邮箱">
                        <a href="#" class="ui-icon-close"></a>
                    </div>
                    <div class="ui-form-item ui-form-item-pure ui-border-b">
                        <input type="password" placeholder="密码">
                        <a href="#" class="ui-icon-close"></a>
                    </div>
                    <div class="ui-form-item ui-form-item-r ui-border-b">
                        <input type="text" placeholder="请输入验证码">
                        <!-- 若按钮不可点击则添加 disabled 类 -->
                        <button type="button" class="ui-border-l">重新发送</button>
                        <a href="#" class="ui-icon-close"></a>
                    </div>
                </form>
            </div>
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