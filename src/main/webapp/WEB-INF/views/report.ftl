<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="业绩录入" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/report/list" fit="true" toolbar="#tb">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="name" width="100" align="center"  >创建人</th>
	        <th field="center" width="100" align="center" >中心</th>
	   <#--   <th field="centerName" width="100" align="center">中心名称</th>  -->
	        <th field="phone" width="100" align="center" >电话</th>
	        <th field="subscribePeople" width="200" align="center">预定人数</th>
	        <th field="arrivePeople" width="100" align="center">实际人数</th>
	        <th field="orderPeople" width="100" align="center">下单人数</th>
	        <th field="newOrder" width="200" align="center" >新增订单</th>
	        <th field="oldOrder" width="100" align="center">续约订单</th>
	        <th field="oneDayMoney" width="200" align="center" >当天收入</th>
	        <th field="hopeMoney" width="200" align="center" >预定收入</th>
	        <th field="marks" width="200" align="center" >备注</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
	            <a href="javascript:openReportAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
	        	<a href="javascript:openReportModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	        	<a href="javascript:deleteReport()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    </div>
	    <div>
	        &nbsp;中心：&nbsp;<input type="text" id="s_center" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>
	     <#--   &nbsp;中心名称：&nbsp;<input type="text" id="s_centerName" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>  -->
	        &nbsp;创建人：&nbsp;<input type="text" id="s_createMan" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>
	     <#--   &nbsp;分配状态：&nbsp;<select class="easyui-combobox" id="s_state" editable="false" panelHeight="auto" >
	        <option value="">请选择...</option>
	        <option value="0">未分配</option>
	        <option value="1">已分配</option>
	    </select>-->
	        <a href="javascript:searchProgress()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	    </div>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:620px;height:450px;padding: 10px 20px"
         closed="true" buttons="#dlg-buttons">

        <form id="fm" method="post">
            <input type="hidden" name="id" id="id" />
            <table cellspacing="8px">
                <tr>
                    <td>中心：</td>
                    <td>
                        <input type="text" id="center" name="center" class="easyui-validatebox" required="true"/>&nbsp;
                        <font color="red">*</font>
                    </td>
                    <td>电话：</td>
                    <td>
                        <input type="text" id="phone" name="phone" />
                    </td>
                </tr>
                <tr>
                    <td>预定人数：</td>
                    <td>
                        <input type="text" id="subscribePeople" name="subscribePeople" class="easyui-numberbox" required="true"/>&nbsp;
                        <font color="red">*</font>
                    </td>
                    <td>实际人数：</td>
                    <td>
                        <input type="text" id="arrivePeople" name="arrivePeople" class="easyui-numberbox" required="true"/>&nbsp;
                        <font color="red">*</font>
                    </td>
                </tr>
                <tr id="parentIdDiv">
                    <td>下单人数</td>
                    <td colspan="4"><input type="text" id="orderPeople" name="orderPeople" class="easyui-numberbox" style="width: 420px"/>
                    <font color="red">*</font>
                    </td>
                    
                </tr>
                <tr>
                    <td>新增订单：</td>
                    <td>
                        <input type="text" id="newOrder" name="newOrder" class="easyui-numberbox" required="true"/>&nbsp;
                        <font color="red">*</font>
                    </td>

                    <td>续约订单：</td>
                    <td>
                        <input type="text" id="oldOrder" name="oldOrder" class="easyui-numberbox" required="true"/>&nbsp;
                    </td>
                </tr>
                <tr>
                    <td>当天收入：</td>
                    <td>
                        <input type="text" id="oneDayMoney" name="oneDayMoney" class="easyui-numberbox" required="true"/>&nbsp;
                        <font color="red">*</font>
                    </td>

                    <td>预定收入：</td>
                    <td>
                        <input type="text" id="hopeMoney" name="hopeMoney" class="easyui-numberbox" required="true"/>&nbsp;
                    </td>
                </tr>
                <tr id="parentIdDiv">
                    <td>备注</td>
                    <td colspan="4">
	                    <textarea rows="5" cols="50" id="marks" name="marks" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
                </tr>
            </table>
        </form>
    </div>
	
	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:saveReport()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closeReportDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/report.js" ></script>
</body>
</html>