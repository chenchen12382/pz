<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="进度管理" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/progress/list" fit="true" toolbar="#tb">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="progressId" width="50" align="center">编号</th>
	        <th field="createman" width="200" align="center"  >创建人</th>
	        <th field="center" width="50" align="center" >中心</th>
	        <th field="centerName" width="100" align="center">中心名称</th>
	        <th field="phone" width="50" align="center" >电话</th>
	        <th field="hopeMoney" width="200" align="center">预计收入</th>
	        <th field="finishMoney" width="100" align="center">达成收入</th>
	        <th field="unfinishMoney" width="100" align="center">未完成收入</th>
	        <th field="marks" width="200" align="center" >备注</th>
	        <th field="createDate" width="100" align="center">创建时间</th>
	        <th field="updateDate" width="200" align="center" hidden="true">更新时间</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
			
				<a href="javascript:openSaleChanceAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
			
	        
	        	<a href="javascript:openSaleChanceModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	      
	        	<a href="javascript:deleteSaleChance()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    	
	    </div>
	    <div>
	        &nbsp;中心：&nbsp;<input type="text" id="s_center" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>
	        &nbsp;中心名称：&nbsp;<input type="text" id="s_centerName" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>
	        &nbsp;创建人：&nbsp;<input type="text" id="s_createMan" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>
	     <#--   &nbsp;分配状态：&nbsp;<select class="easyui-combobox" id="s_state" editable="false" panelHeight="auto" >
	        <option value="">请选择...</option>
	        <option value="0">未分配</option>
	        <option value="1">已分配</option>-->
	    </select>
	        <a href="javascript:searchProgress()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	    </div>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:700px;height:450px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
	
	    <form id="fm" method="post">
	        <table cellspacing="8px">
	            <tr>
	                <td>客户名称：</td>
	                <td>
	                	<input type="hidden" id="customerName" name="customerName" />
	                	<input class="easyui-combobox" id="customerId" name="customerId" 
	                	data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name', url:'${ctx}/customer/find_all'" 
	                	/> &nbsp;<font color="red">*</font>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>机会来源</td>
	                <td><input type="text" id="chanceSource" name="chanceSource" /></td>
	            </tr>
	            <tr>
	                <td>联系人：</td>
	                <td><input type="text" id="linkMan" name="linkMan" /></td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>联系电话：</td>
	                <td><input type="text" id="linkPhone" name="linkPhone" /></td>
	            </tr>
	            <tr>
	                <td>成功几率(%)：</td>
	                <td><input type="text" id="cgjl" name="cgjl" class="easyui-numberbox" data-options="min:0,max:100" required="true"/>&nbsp;<font color="red">*</font></td>
	                <td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            </tr>
	            <tr>
	                <td>概要：</td>
	                <td colspan="4"><input type="text" id="overview" name="overview" style="width: 420px"/></td>
	            </tr>
	            <tr>
	                <td>机会描述：</td>
	                <td colspan="4">
	                    <textarea rows="5" cols="50" id="description" name="description" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	            </tr>
	            <input type="hidden" id="id" name="id" />
	            <tr>
	                <td>指派给：</td>
	                <td><input class="easyui-combobox" id="assignMan" name="assignMan" 
	                	data-options="panelHeight:'auto',editable:false,valueField:'trueName',textField:'trueName',url:'${ctx}/user/find_customer_manager'"/></td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            </tr>
	        </table>
	    </form>
	</div>
	
	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:saveSaleChance()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closeSaleChanceDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/sale.chance.js" ></script>
</body>
</html>