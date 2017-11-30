<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="收据编号管理" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/protocol/listSjbh" fit="true" toolbar="#tb" singleSelect = "false">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center" hidden="hidden">编号</th>
	        <th field="center" width="100" align="center">中心</th>
	        <th field="sjbh" width="100" align="center">收据编号</th>
	        <th field="createDate" width="100" align="center">创建时间</th>
	        <th field="updateDate" width="100" align="center">更新时间</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
        <div>
            &nbsp;中心：&nbsp;<input type="text" id="s_center"  size="15" onkeydown="if(event.keyCode==13) searchCenter()"/>
            &nbsp;收据编号：&nbsp;<input type="text" id="s_sjbh"  size="15" onkeydown="if(event.keyCode==13) searchCenter()"/>
            <a href="javascript:searchCenter()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
        </div>

        <div>
            <a href="javascript:deleteXybhs()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </div>
	</div>




	<script src="${ctx}/js/sjbh.js" ></script>
</body>
</html>