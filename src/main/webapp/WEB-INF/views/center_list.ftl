<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="中心管理" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/center/list" fit="true" toolbar="#tb" singleSelect = "false">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="center" width="200" align="center">中心</th>
	        <th field="district" width="300" align="center">区域</th>
	        <th field="createDate" width="100" align="center">创建时间</th>
	        <th field="updateDate" width="100" align="center">更新时间</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
            <div>
                &nbsp;中心：&nbsp;<input type="text" id="s_center"  size="15" onkeydown="if(event.keyCode==13) searchCenter()"/>
                <a href="javascript:searchCenter()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
            </div>
	<#if userPermissions?seq_contains('904001') >
	        <a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
		</#if>
<#if userPermissions?seq_contains('904002') >
	        <a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
</#if>
<#if userPermissions?seq_contains('904003') >
	        <a href="javascript:deletecenters()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</#if>
<#if userPermissions?seq_contains('904004') >
            <a href="javascript:openExcelDialog()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">批量导入</a>
</#if>
	    </div>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
	
	    <form id="fm" method="post">
	        <table cellspacing="8px">
	        	<input type="hidden" id="id" name="id" />
	            <tr>
	                <td>中心名称：</td>
	                <td colspan="4"><input type="text" id="center" name="center" style="width: 220px"/></td>
	            </tr>
	            <tr>
	                <td>区域信息：</td>
                    <td>
                        <input class="easyui-combobox" id="district" name="district"
                               data-options="panelHeight:'auto', editable:false, valueField:'district',
	                		textField:'district', url:'${ctx}/district/find_all'"/>

                    </td>
	                </td>
	            </tr>
	        </table>
	    </form>
	</div>

	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:savecenter()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closecenterDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>

	<#--弹出框-->
    <div id="edlg" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
         closed="true" buttons="#rex-buttons" >

        <form id="exfm" method="post" enctype="multipart/form-data">
            <table cellspacing="8px">
                <input type="hidden" id="centerId" name="id" />
                <tr>
                    <td>会员协议编号导入：</td>
                    <td> <input type="file" id="upExl" name="upExl" style="width:170px;" ></td>
                </tr>
                <tr>
                    <td>乐博士议编号导入：</td>
                    <td> <input type="file" id="upLbs" name="upLbs" style="width:170px;" ></td>
                </tr>
                <tr>
                    <td>收据编号导入：</td>
                    <td> <input type="file" id="upSjbh" name="upSjbh" style="width:170px;" ></td>
                </tr>

            </table>
        </form>
    </div>

	<#--弹出框按钮-->
    <div id="rex-buttons">
        <a href="javascript:saveExcel()" class="easyui-linkbutton" iconCls="icon-ok">批量导入</a>
        <a href="javascript:closeUploadDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
    </div>

	
	<script src="${ctx}/js/center.list.js?datsdfsdf" ></script>
</body>
</html>