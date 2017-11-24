<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="顾问业务量" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/report/list" fit="true" toolbar="#tb">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="name" width="100" align="center">顾问</th>
	        <th field="phoneNum" width="100" align="center" >电话量</th>
	        <th field="planNum" width="200" align="center">邀约量</th>
	        <th field="arriveNum" width="200" align="center">到访人数</th>
	        <th field="inNum" width="100" align="center">接待人数</th>
	        <th field="orderNum" width="200" align="center" >下单人数</th>
	        <th field="source" width="100" align="center">来源</th> 
	        <th field="money" width="100" align="center">收入金额</th>
	        <th field="analysis" width="200" align="center" >未报名分析</th>
	       <#--   <th field="createDate" width="100" align="center">日期</th>
	      <th field="updateDate" width="100" align="center">更新日期</th>	-->  
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
	<#--<div>
	           <a href="javascript:openReportAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
	           <a href="javascript:openReportModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	        <a href="javascript:deleteReport()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    </div>-->
	    <div>
	       <#-- &nbsp;中心：&nbsp;<input type="text" id="center" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>-->
	        &nbsp;顾问：&nbsp;<input type="text" id="name" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>	     
	        <a href="javascript:searchProgress()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	    </div>
	</div>
	

	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:saveReport()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closeReportDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>	
	<script src="${ctx}/js/report.js" ></script>
</body>
</html>