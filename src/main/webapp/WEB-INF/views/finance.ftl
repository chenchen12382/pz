<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px" >
	<table id="dg" title="财务管理"  class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/finance/list" fit="true" toolbar="#tb">
	    <thead >
	    <tr >
	        <th field="xybh" width="50" align="center">协议编号</th>
	        <th field="sjbh" width="100" align="center">收据编号</th>
	        <th field="hybh" width="100" align="center" >会员编号</th>
	        <th field="name" width="100" align="center">会员姓名</th> 
	        <th field="saleClass" width="100" align="center" >销售课程</th>
	        <th field="saleNum" width="200" align="center">销售数量</th>
	        <th field="agreement" width="100" align="center">协议签订类型</th>
	        <th field="shouldMoney" width="200" align="center" >应收金额</th>
	        <th field="realMoney" width="200" align="center" >实收金额</th>
	        <th field="discount" width="100" align="center">折扣</th>
	        <th field="payMode" width="100" align="center">付款方式</th>
	        <th field="counselor" width="100" align="center">顾问</th>
	        <th field="promotion" width="100" align="center">促销政策</th>
	        <th field="gift" width="100" align="center">赠送课程</th>
	        <th field="source" width="100" align="center">客户来源</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
				<a href="javascript:openProgressAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
            	<a href="javascript:openProgressModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	        	<a href="javascript:deleteProgress()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>	    	
	   </div>
	    <div>
	        &nbsp;协议编号：&nbsp;<input type="text" id="xybh" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>	        <a href="javascript:searchProgress()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	    </div>
	</div>
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:700px;height:450px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
	
	    <form id="fm" method="post">
	        <table cellspacing="8px">
	            <tr>
	                <td>协议编号：</td>
	                <td>
	                	<input type="text" id="xybh" name="xybh" />
	                	<input id="center" name="center" required="true"/> &nbsp;
	                	<font color="red">*</font>
	                </tr> 
	            <tr> 
	               <td>收据编号:</td>             
	               <td>
	                   <input type="text" name="sjbh" id="sjbh" /> 
	                   <input id="center" name="center" required="true"/> &nbsp;
	                   <font color="red">*</font>
	            </tr>
	            <tr>
	                   <td>会员编号：</td>
	                    <td>
	                    <input type="text" id="hybh" name="hybh" class="easyui-numberbox" />
	                    <input id="center" name="center" required="true"/> &nbsp;
	                    <font color="red">*</font>
	            </tr>
	            <tr>
	                <td>会员姓名：</td>
	                <td>
	                <input type="text" id="name" name="name"   required="true"/>&nbsp;<font color="red">*</font></td>
	                <td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            </tr>
	            <tr>
	                <td>销售课程：</td>
	                 <td><input type="text"  id="saleClass" name="saleClass" />  
	                   <select class="easyui-combobox" id="s_state" editable="false" panelHeight="auto" >
	                   <option value="">请选择...</option>
	                   <option value="0">乐博士</option>
	                   <option value="1">亲子</option>
	                   <option value="2">启稚</option>
	                   <option value="3">幼小衔接</option>
	                   <option value="4">其他</option> 
	                 </select>
	            </tr>	            
	            <tr>
	                <td>销售数量：</td>
	                <td><input type="text"  id="saleNum" name="saleNum" class="easyui-numberbox"  />
	               <select class="easyui-combobox" id="s_state" editable="false" panelHeight="auto" >
	                   <option value="">请选择...</option>
	                   <option value="0">POS机</option>
	                   <option value="1">支付宝</option>
	                   <option value="2">微信</option>
	                   <option value="3">现金</option>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <td>协议签订类型：</td>
	                <td colspan="4">
	                   <textarea rows="5" cols="50" id="agreement" name="agreement" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                    <select class="easyui-combobox" id="s_state" editable="false" panelHeight="auto" >
	                   <option value="">请选择...</option>
	                   <option value="0">新签</option>
	                   <option value="1">续费</option>
	                </td>
	                </select>
	            </tr>
	            
	             <tr>
	                <td>应收金额：</td>
	                <td colspan="4">
	                   <textarea rows="5" cols="50" id="shouldMoney" name="shouldMoney" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	                <tr>
	                <td>实收金额：</td>
	                <td colspan="4">
	                   <textarea rows="5" cols="50" id="realMoney" name="realMoney" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	            </tr>	
	             <tr>
	                <td>折扣：</td>
	                <td colspan="4">
	                   <textarea rows="5" cols="50" id="discount" name="discount" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	            </tr>	
	             <tr>
	                <td>付款方式：</td>
	                <td colspan="4">
	                   <textarea rows="5" cols="50" id="payMode" name="payMode" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	            </tr>	
	             <tr>
	                <td>顾问：</td>
	                <td colspan="4">
	                   <textarea rows="5" cols="50" id="counselor" name="counselor" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	            </tr>	
	             <tr>
	                <td>促销政策：</td>
	                <td colspan="4">
	                   <textarea rows="5" cols="50" id="promotion" name="promotion" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	            </tr>	
	             <tr>
	                <td>赠送课程：</td>
	                <td colspan="4">
	                   <textarea rows="5" cols="50" id="gift" name="gift" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	            </tr>	
	             <tr>
	                <td>客户来源：</td>
	                <td colspan="4">
	                   <textarea rows="5" cols="50" id="source" name="source" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	            </tr>
	        </table>
	    </form>
	</div>
	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:saveProgress()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closeProgressDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<script src="${ctx}/js/finance.js" ></script>
</body>
</html>