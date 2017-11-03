<!DOCTYPE html>
<html>
<head>

   <#include "include/common.header.ftl" >
    <script type="text/javascript" src="${ctx}/js/main.js"></script>
       <link rel="stylesheet" href="${ctx}/ztree/css/zTreeStyle.css" type="text/css">
       <#--<script type="text/javascript" src="${ctx}/ztree/js/jquery-1.4.4.min.js"></script>-->
       <script type="text/javascript" src="${ctx}/ztree/js/jquery.ztree.core.js"></script>
       <#--<script type="text/javascript" src="${ctx}/ztree/js/jquery.ztree.excheck.js"></script>-->


    <script>
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onClick
            }
        };

//        var zNodes =[ ];

        // 基本功能菜单加载
        $.ajax({
            url : '${ctx}/module/menu',
            type : 'POST',
//            dataType : 'text',
            success : function(data) {
                var zNodes = data.menu;
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                treeObj.expandAll(true);
            },
            error : function(msg) {
                alert('菜单加载异常!');
            }
        });

        function onClick(event, treeId, treeNode) {
                    // 判断树菜单节点是否含有 page属性
                    if (treeNode.page!=undefined && treeNode.page!= ""&&treeNode.page!= "#") {
                        if ($("#tabs").tabs('exists', treeNode.name)) {// 判断tab是否存在
                            $('#tabs').tabs('select', treeNode.name); // 切换tab
                        } else {
                            // 开启一个新的tab页面
                            var content = '<div style="width:100%;height:100%;overflow:hidden;">'
                                    + '<iframe src="'
                                    + treeNode.page
                                    + '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>';

                            $('#tabs').tabs('add', {
                                title : treeNode.name,
                                content : content,
                                closable : true
                            });
                        }
                    }
                    if(treeNode.name=="修改密码"){
                        openPasswordModifyDialog();
                    }

            if(treeNode.name=="安全退出"){
                logout();
            }

                }

//        function zTreeOnClick(event, treeId, treeNode) {
//            alert(treeNode.tId + ", " + treeNode.name);
//        };


//        $(document).ready(function() {
//            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
//            treeObj.expandAll(true);
//        });

    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF;overflow: hidden;">
    <table style="padding: 5px" width="100%">
        <tr>
            <td width="1%">
                <img alt="logo" src="${ctx}/images/b.png" height="60" width="150" >
            </td>
            <td width="30%">
                <font size="6" color="#000066" face="KaiTi">培正信息管理系统</font>
            </td>
           <td valign="bottom"  width="10%">   
              <font size="3">&nbsp;<strong>欢迎：</strong>${currentUser.userName }</font>【${currentUser.realName }】
             <br/>
                <font id="today"></font>
            </td>
        </tr>
    </table>
</div>
<div region="center" >
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 150px"><img alt="logo" src="${ctx}/images/002.png" height="120" width="700" ></div>
        </div>
    </div>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <#--<#if userPermissions?seq_contains('10') >-->
        	<div title="功能菜单" data-options="selected:true,iconCls:'icon-yxgl'" style="padding: 10px">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            <#--<#if userPermissions?seq_contains('1010') >-->
                <#--<a href="javascript:openTab('营收日报表','finance/index_center','icon-zzkf')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-zzkf'" style="width: 150px">营收日报表</a>-->
            <#--</#if>-->
            <#--<#if userPermissions?seq_contains('1020') >-->
	            <#--<a href="javascript:openTab('业绩录入 ','report/index','icon-yxjhgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-yxjhgl'" style="width: 150px">业绩录入</a>-->
            <#--</#if>-->
            <#--<#if userPermissions?seq_contains('1030') >-->
                <#--<a href="javascript:openTab('进度管理','progress/index','icon-khkfjh')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">进度管理</a>-->
            <#--</#if>-->

	        </div>
		<#--</#if>-->
            <#--<#if userPermissions?seq_contains('20') >-->
	        <#--<div title="财务管理"  data-options="iconCls:'icon-khgl'" style="padding:10px;">-->
                <#--<#if userPermissions?seq_contains('2010') >-->
                    <#--<a href="javascript:openTab('财务报表管理','finance/index','icon-khxxgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khxxgl'" style="width: 150px;">财务报表管理</a>-->
                    <#--</#if>-->
                <#--<#if userPermissions?seq_contains('2020') >-->
                    <#--<a href="javascript:openTab('课程价格管理','priceClass/index','icon-khlsgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khlsgl'" style="width: 150px;">课程价格管理</a>-->
                <#--</#if>-->
	        <#--</div>-->
          <#--</#if>-->
    <#--<#if userPermissions?seq_contains('30') >-->
        <#--<div title="运营分析" data-options="iconCls:'icon-fwgl'" style="padding:10px">-->
        <#--<#if userPermissions?seq_contains('3010') >-->
            <#--<a href="javascript:openTab('业绩统计','centerTotal/index','icon-fwcj')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fwcj'" style="width: 150px;">业绩统计</a>-->
            <#--</#if>-->
        <#--<#if userPermissions?seq_contains('3020') >-->
            <#--<a href="javascript:openTab('指标录入','target/index','icon-fwfp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fwfp'" style="width: 150px;">指标录入</a>-->
        <#--</#if>-->
        <#--<#if userPermissions?seq_contains('3030') >-->
            <#--<a href="javascript:openTab('区域业绩汇总','report_count/index/1','icon-fwcl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fwcl'" style="width: 150px;">区域业绩汇总</a>-->
        <#--</#if>-->
        <#--<#if userPermissions?seq_contains('3040') >-->
            <#--<a href="javascript:openTab('区域业绩分析','report_count/index/2','icon-fwfk')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fwfk'" style="width: 150px;">区域业绩分析</a>-->
        <#--</#if>-->
            <#--<a href="javascript:openTab('中心收入分析','report_count/index/3','icon-fwgd')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fwgd'" style="width: 150px;">中心收入分析</a>-->
        <#--</div>-->
    <#--</#if>-->
    <#--	<div title="统计报表"  data-options="iconCls:'icon-tjbb'" style="padding:10px">
            <a href="javascript:openTab('客户贡献分析','report/0','icon-khgxfx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khgxfx'" style="width: 150px;">客户贡献分析</a>
            <a href="javascript:openTab('客户构成分析','report/1','icon-khgcfx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khgcfx'" style="width: 150px;">客户构成分析</a>
            <a href="javascript:openTab('客户服务分析','report/2','icon-khfwfx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khfwfx'" style="width: 150px;">客户服务分析</a>
            <a href="javascript:openTab('客户流失分析','report/3','icon-khlsfx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khlsfx'" style="width: 150px;">客户流失分析</a>
        </div>
        <div title="基础数据管理"  data-options="iconCls:'icon-jcsjgl'" style="padding:10px">
            <a href="javascript:openTab('数据字典管理','datadic/index','icon-sjzdgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-sjzdgl'" style="width: 150px;">数据字典管理</a>
            <a href="javascript:openTab('产品信息查询','product/index','icon-cpxxgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cpxxgl'" style="width: 150px;">产品信息查询</a>
        </div> -->
<#--<#if userPermissions?seq_contains('90') >-->
    <div title="系统管理"  data-options="iconCls:'icon-item'" style="padding:10px">
    <#if userPermissions?seq_contains('9010') >
        <a href="javascript:openTab('用户管理','user/index','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">用户管理</a>
        </#if>
    <#if userPermissions?seq_contains('9020') >
        <a href="javascript:openTab('角色管理','role/index','icon-khgcfx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khgcfx'" style="width: 150px;">角色管理</a>
    </#if>
    <#if userPermissions?seq_contains('9030') >
        <a href="javascript:openTab('模块管理','module/index','icon-tjbb')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tjbb'" style="width: 150px;">模块管理</a>
    </#if>
    <#if userPermissions?seq_contains('9040') >
        <a href="javascript:openTab('中心管理','center/index','icon-jcsjgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-jcsjgl'" style="width: 150px;">中心管理</a>
    </#if>
    <#if userPermissions?seq_contains('9050') >
        <a href="javascript:openTab('区域管理','district/index','icon-reset')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reset'" style="width: 150px;">区域管理</a>
    </#if>
        <a href="javascript:openTab('更新日志','up_log/index','icon-cpxxgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cpxxgl'" style="width: 150px;">更新日志</a>
        <a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
            <a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
        </div>
<#--</#if>-->
    </div>
</div>
<div region="south" style="height: 25px;padding: 5px;overflow: hidden;" align="center">
   © 网络信息中心&nbsp;& &nbsp;<a href="http://www.perchingkids.com" target="_blank">www.perchingkids.com</a>2017
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="userName" name="userName" readonly="readonly" value="${currentUser.userName }" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>原密码：</td>
                <td><input type="password" id="oldPassword" name="oldPassword" class="easyui-validatebox" required="true" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td><input type="password" id="newPassword2" name="confirmPassword" class="easyui-validatebox" required="true" style="width: 200px"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
<script type="text/javascript">
    function setDateTime() {
        var date = new Date();
        var day = date.getDay();
        var week;
        switch (day) {
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
        }
        var today = date.getFullYear() + "年" + (date.getMonth() + 1) + "月"
                + date.getDate() + "日  " + week + " " + date.getHours() + ":"
                + date.getMinutes() + ":" + date.getSeconds();
        document.getElementById("today").innerHTML = today;
    }
    window.setInterval("setDateTime()", 1000);
</script>

</html>