<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="指标录入" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/target/list" fit="true" toolbar="#tb" singleSelect = "false">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
            <th field="district" width="300" align="center">区域</th>
            <th field="month" width="200" align="center" >月份</th>
	        <th field="target" width="200" align="center">指标</th>
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
	        <a href="javascript:deletetargets()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    </div>
	     <div>
	      &nbsp;区域：&nbsp;<input type="text" id="district" size="20" onkeydown="if(event.keyCode==13) searchTarget()"/>
      		&nbsp;月份：&nbsp;<input type="text" id="month" class="easyui-datebox" size="15"  onkeydown="if(event.keyCode==13) searchTarget()"/>
             &nbsp;&nbsp; <font color="red">日期请随意选择！</font>&nbsp;&nbsp;
	        <a href="javascript:searchTarget()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	    </div>
	</div>
	 
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
	
	    <form id="fm" method="post">
	        <table cellspacing="8px">
	        	<input type="hidden" id="id" name="id" />           
	            <tr>
	                <td>区域信息：</td>
                    <td>
                        <input class="easyui-combobox" id="district" name="district"
                               data-options="panelHeight:'auto', editable:false, valueField:'district',
	                		textField:'district', url:'${ctx}/district/find_all'"/>
                    </td>
	                </td>
	            </tr>
	            <tr>
	                  &nbsp; &nbsp;月份：&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
	                  <select class="easyui-combobox" id="months" name="months"  editable="false" panelHeight="auto" >
                        <option value="请选择...">请选择...</option>
                        <option value="本月">本月</option>
                        <option value="次月">次月</option>  
                   </select>
	            </tr>
	     
	             <tr>
	                <td>指标：</td>
	                <td colspan="4"><input type="text" id="target" name="target" style="width: 220px"/></td>
	            </tr>
	        </table>
	    </form>
	</div>
	
	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:savetarget()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closetargetDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/target.js"></script>
</body>
</html>