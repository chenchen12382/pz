<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="区域管理" class="easyui-datagrid"
	       fitColumns="true" pagination="false" rownumbers="true"
	       url="${ctx}/district/list" fit="true" toolbar="#tb" singleSelect = "false">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="district"></th>
	        <th field="id" width="50" align="district" hidden="true">编号</th>
	        <th field="district" width="200" align="center">区域</th>
	        <th field="remark" width="200" align="center">备注信息</th>
	        <th field="createDate" width="150" align="district">创建时间</th>
	        <th field="updateDate" width="150" align="district">更新时间</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
	        <a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
	        <a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	        <a href="javascript:deletedistricts()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    </div>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
	
	    <form id="fm" method="post">
	        <table cellspacing="8px">
	        	<input type="hidden" id="id" name="id" />
	            <tr>
	                <td>区域名称：</td>
	                <td colspan="4"><input type="text" id="district" name="district" style="width: 220px"/></td>
	            </tr>
	            <tr>
	                <td>备注信息：</td>
                    <td colspan="4"><input type="text" id="remark" name="remark" style="width: 220px"/></td>
	                </td>
	            </tr>
	        </table>
	    </form>
	</div>
	
	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:savedistrict()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closedistrictDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/district.js?datsdfsdf" ></script>
</body>
</html>