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
	        <th field="id" width="50" align="center">编号</th>
	        <th field="centerId" width="200" align="center">中心ID</th>
	        <th field="sjbh" width="300" align="center">收据编号</th>
	        <th field="createDate" width="100" align="center">创建时间</th>
	        <th field="updateDate" width="100" align="center">更新时间</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
            <a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">批量导入</a>
	        <a href="javascript:deleteSjbhs()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    </div>
	</div>

	<#--弹出框-->
    <div id="edlg" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
         closed="true" buttons="#rex-buttons" >

        <form id="exfm" method="post" enctype="multipart/form-data">
            <table cellspacing="8px">
                <input type="hidden" id="centerId" name="id" />
                <tr>
                    <td>协议编号导入：</td>
                    <td> <input type="file" id="upExl" name="upExl" style="width:170px;" ></td>
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


	<script src="${ctx}/js/sjbh.js" ></script>
</body>
</html>