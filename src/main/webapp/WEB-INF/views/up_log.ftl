<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="日志管理" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/up_log/list" fit="true" toolbar="#tb" singleSelect = "false">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="title" width="200" align="center">标题</th>
	        <th field="log" width="300" align="center">日志信息</th>
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
	        <a href="javascript:deletecenters()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    </div>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:600px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

        <form id="fm" method="post">
            <table cellspacing="8px">
                <input type="hidden" id="id" name="id" />
                <tr>
                    <td>标题：</td>
                    <td colspan="4"><input type="text" id="title" name="title" style="width: 350px"/></td>
                </tr>
                <tr>
                    <td>日志信息：</td>
                    <td colspan="4">
                        <textarea rows="5" cols="50" id="log" name="log" style="margin: 0px;width: 350px;height: 75px;resize: none;"></textarea>
                    </td>
                </tr>
            </table>
        </form>
	</div>	
	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:saveUpLog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closecenterDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/uplog.js" ></script>
</body>
</html>