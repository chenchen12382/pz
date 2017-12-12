<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="中心顾问业绩统计" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/analyzeTotal/list" fit="true" toolbar="#tb">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="center" width="100" align="center">中心</th>
	        <th field="totalPhoneNum" width="100" align="center" >电话总量</th>
	        <th field="totalPlanNum" width="100" align="center">邀约总量</th>
	        <th field="totalArriveNum" width="100" align="center">到访总人数</th>
	        <th field="totalInNum" width="100" align="center">接待总人数</th>
	        <th field="totalOrderNum" width="100" align="center" >下单总人数</th>
	        <th field="totalMoney" width="200" align="center" >收益总额</th>
	      
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
	    <div>
	         &nbsp;中心：&nbsp;<input type="text" id="center" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>
             &nbsp;开始时间：&nbsp;<input type="text" id="start" class="easyui-datebox" size="15" onkeydown="if(event.keyCode==13) searchProgress()"/>
             &nbsp;结束时间：&nbsp;<input type="text" id="over" class="easyui-datebox" size="15" onkeydown="if(event.keyCode==13) searchProgress()"/>
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