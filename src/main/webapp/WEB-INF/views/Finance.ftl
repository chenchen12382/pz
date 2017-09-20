<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="财务报表管理" class="easyui-datagrid"
	       fitColumns="false" pagination="true" rownumbers="true"
	       url="${ctx}/finance/list" fit="true" toolbar="#tb">
	
		    <thead data-options="frozen:true" >
		    	<tr>
			        <th field="cb" checkbox="true" align="center"></th>
                    <th field="id" width="30" align="center">编号</th>
                    <th field="xybh" width="120" align="center"  >协议编号</th>
			        <th field="sjbh" width="120" align="center"  >收据编号</th>
			        <th field="hybh" width="120" align="center">会员编号 </th>
			        <th field="name" width="50" align="center">客户姓名</th>
			        <th field="saleClass" width="50" align="center">销售课程</th>
			        <th field="saleNum" width="50" align="center">销售数量 </th>
			    </tr>
		    </thead>
		        <#--滚定-->
			<thead>
				<tr>
			        <th field="agreement" width="80" align="center">签订协议类型</th>
			        <th field="price" width="80" align="center">价格</th>
			        <th field="shouldMoney" width="80" align="center">应收金额</th>
			        <th field="realMoney" width="200" align="center" >实际金额</th>
			        <th field="discount" width="100" align="center" >折扣</th>
			        <th field="payMode" width="100" align="center" >支付方式</th>
			        <th field="cardNum" width="100" align="center" >银行卡号</th>
			        <th field="counselor" width="100" align="center" >顾问</th>
			        <th field="promotion" width="100" align="center" >促销</th>
			        <th field="gift"   width="100"  align="center" >赠送课程</th>
			    </tr>
			</thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
			<a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
        	<a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        	<a href="javascript:deleteCustomer()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>

	    </div>
	    <div>
	    	&nbsp;客户编号：&nbsp;<input type="text" id="s_customerNo" size="20" onkeydown="if(event.keyCode==13) searchCustomer()"/>
	        &nbsp;客户名称：&nbsp;<input type="text" id="s_customerName" size="20" onkeydown="if(event.keyCode==13) searchCustomer()"/>
	    </select>
	        <a href="javascript:searchCustomer()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	    </div>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:700px;height:400px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

	    <form id="fm" method="post">
	        <input type="hidden" id="id" name="id" />
	        <table cellspacing="8px">
	            <tr>
	                <td>协议编号：</td>
	                <td><input type="text" id="xybh" name="xybh" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>收据编号：</td>
                    <td><input type="text" id="sjbh" name="sjbh" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
	                </td>
	            </tr>
	            <tr>
	                <td>会员编号：</td>
	                <td>
	                    <input type="text" id="hybh" name="hybh" class="easyui-validatebox" >
	                </td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>客户姓名：</td>
	                <td>
                        <input type="text" id="name" name="name" class="easyui-validatebox" >
	                </td>
	            </tr>
	            <tr>
	                <td>销售课程：</td>
	                <td>
                        <input  class="easyui-combobox"   id="saleClass" name="saleClass" data-options="panelHeight:'auto',editable:false,valueField:'saleClass',textField:'saleClass',url:'${ctx}/priceClass/find_all'"/>&nbsp;<font color="red">*</font>
	                </td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>销售数量：</td>
	                <td>
                        <input type="text" id="saleNum" name="saleNum" class="easyui-numberbox" />
	                </td>
	            </tr>
	            <tr>
	                <td>签订协议类型：</td>
	                <td>
                        <select class="easyui-combobox" id="agreement" agreement="name" panelHeight="auto" class="easyui-validatebox" >
                            <option value="">请选择...</option>
                             <option value="新签约">新签约</option>
                             <option value="续约">续约</option>
                             <option value="赠送">赠送</option>
                      </select>
	                </td>
	              <tr>  
	                <td>实收金额：</td>
	                <td>
						<input type="text" id="realMoney" name="realMoney" class="easyui-numberbox" />
					</td>
	            </tr>
	            <tr>
	                <td>支付方式：</td>
	                <td>
                        <select class="easyui-combobox" id="s_state" name="s_state" editable="false" panelHeight="auto" >                         
                           
                            <option value="0">POS机</option>
                            <option value="1">支付宝</option>
                            <option value="2">微信</option>
                            <option value="3">现金</option>
                        </select>
				    </td>

				</tr>
	            <tr id="parentIdDiv" >	                
                    <td>银行卡号:</td>
                    <td>
                        <input type="text" id="parentId" name="parentId" class="easyui-numberbox" />
                    </td>
                </tr>
	            <tr> 
	            	<td>顾问：</td>
	                <td><input type="text" id="counselor" name="counselor" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
	            </tr>
	            <tr>
	                <td>促销/备注：</td>
	                <td colspan="4">
	                    <input type="text" id="promotion" name="promotion" style="width: 400px" class="easyui-validatebox" />
	                </td>
	            </tr>
	            <tr>
	                <td>赠送课程：</td>
	                <td><input type="text" id="gift" name="gift" /></td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>用户来源：</td>
                   <td>
                      <select class="easyui-combobox" id="s_state" editable="false" panelHeight="auto" >
                           <option value="">请选择...</option>
                           <option value="上门">上门</option>
                           <option value="电话">电话</option>
                           <option value="转介绍">转介绍</option>
                           <option value="肄业合作">肄业合作</option>
                           <option value="大众点评">大众点评</option>
                           <option value="官网">官网</option>
                           <option value="微信">微信</option>
                           <option value="口碑">口碑</option>
                           <option value="其他">其他</option>
                       </select>&nbsp;<font color="red">*</font>
				   </td>
	            </tr> 
	        </table>
	    </form>
	</div>
	<#--按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:saveCustomer()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closeCustomerDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/finance.js" ></script>
</body>
</html>