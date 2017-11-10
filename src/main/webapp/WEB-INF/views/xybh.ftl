<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="协议编号管理" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/protocol/listXybh" fit="true" toolbar="#tb" singleSelect = "false">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="centerId" width="200" align="center">中心ID</th>
	        <th field="xybh" width="300" align="center">协议编号</th>
	        <th field="createDate" width="100" align="center">创建时间</th>
	        <th field="updateDate" width="100" align="center">更新时间</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
	       <a href="javascript:deleteXybhs()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    </div>
	</div>
	<script src="${ctx}/js/xybh.js" ></script>
</body>
</html>