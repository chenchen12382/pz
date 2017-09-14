<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="课程价格管理" class="easyui-datagrid"
	       fitColumns="true" pagination="false" rownumbers="true"
	       url="${ctx}/priceClass/list" fit="true" toolbar="#tb" singleSelect = "false">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="saleClass" width="200" align="center">销售课程</th>
	        <th field="price" width="300" align="center">标准单价</th>
	        <th field="createDate" width="100" align="center">创建时间</th>
	        <th field="updateDate" width="100" align="center">更新时间</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
	        <a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
	        <a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	        <a href="javascript:deletepriceClasss()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    </div>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
	
	    <form id="fm" method="post">
	        <table cellspacing="8px">
	        	<input type="hidden" id="id" name="id" />
	            <tr>
	                <td>销售课程：</td>
	                <td colspan="4"><input type="text" id="priceClassName" name="saleClass" style="width: 220px"/></td>
	            </tr>
	            <tr>
	                <td>课程价格：</td>
                    <td colspan="4"><input type="text" id="priceClassName" name="price" style="width: 220px"/></td>
	                </td>
	            </tr>
	        </table>
	    </form>
	</div>
	
	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:savepriceClass()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closepriceClassDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/price.class.js?datsdfsdf" ></script>
</body>
</html>