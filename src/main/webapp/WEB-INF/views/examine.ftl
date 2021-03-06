<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="报表审核" class="easyui-datagrid"
	       fitColumns="false" pagination="true" rownumbers="true"
	       url="${ctx}/finance/examine_list" fit="true" toolbar="#tb">
	
		    <thead data-options="frozen:true" >
		    	<tr>
			        <th field="cb" checkbox="true" align="center"></th>
                    <#--<th field="id" width="30" align="center">编号</th>-->
                    <th field="name" width="50" align="center">客户姓名</th>
                    <th field="xybh" width="100" align="center"  >协议编号</th>
			        <th field="sjbh" width="100" align="center"  >收据编号</th>
			        <th field="hybh" width="100" align="center">会员编号 </th>
					<th field="saleClass" width="80" align="center">销售课程</th>
			        <th field="saleNum" width="60" align="center">销售数量 </th>
			    </tr>
		    </thead>
		        <#--滚定-->
			<thead>
				<tr>
			        <th field="agreement" width="80" align="center">签订协议类型</th>
                    <th field="contractTime" width="100" align="center">签约时间</th>
			        <th field="price" width="80" align="center">课程单价</th>
			        <th field="shouldMoney" width="80" align="center">标准金额</th>
			        <th field="realMoney" width="100" align="center" >实际金额</th>
			        <th field="discount" width="100" align="center" formatter="formatDiscount" >折扣</th>
			        <th field="payMode" width="100" align="center" >支付方式</th>
                    <th field="property" width="80" align="center">付款性质</th>
			        <th field="cardNum" width="100" align="center" >银行卡号</th>
			        <th field="counselor" width="100" align="center" >顾问/老师</th>
                    <th field="teacher" width="100" align="center" formatter="formatTeacher">职位</th>
			        <th field="promotion" width="100" align="center" formatter="formatPromotion">促销</th>
			        <th field="gift"   width="100"  align="center" >赠送课程</th>
                    <th field="source"   width="100"  align="center" >客戶來源</th>
                    <th field="src"  width="100"  align="center" hidden="hidden" >合同图片</th>
                    <th field="createDate"   width="100"  align="center" >创建时间</th>

			    </tr>
			</thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
			<a href="javascript:examineDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">报表审核</a>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:700px;height:450px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons" enctype="multipart/form-data" >

	    <form id="fm" method="post">
	        <input type="hidden" id="id" name="id" />
            <input type="hidden" id="src" name="src" />
	        <table cellspacing="8px">
	            <tr>
	                <td>协议编号：</td>
	                <td><input type="text" id="xybh" name="xybh" class="easyui-combobox"  required="true" readonly="readonly"/>&nbsp;<font color="red">*</font></td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>收据编号：</td>
                    <td><input type="text" id="sjbh" name="sjbh" class="easyui-numberbox"  required="true" readonly="readonly" />&nbsp;<font color="red">*</font></td>
	                </td>
	            </tr>
	            <tr>
	                <td>会员编号：</td>
	                <td>
	                    <input type="text" id="hybh" name="hybh" class="easyui-validatebox" readonly="readonly" >
	                </td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>客户姓名：</td>
	                <td>
                        <input type="text" id="o_name" name="name" class="easyui-validatebox" required="true" readonly="readonly">
						&nbsp;<font color="red">*</font>

	                </td>
	            </tr>
	            <tr>
	                <td>销售课程：</td>
	                <td>
                        <input class="easyui-combobox" id="saleClass" readonly="readonly" name="saleClass" data-options="panelHeight:'auto',editable:false,valueField:'saleClass',textField:'saleClass',url:'${ctx}/priceClass/find_all'" />&nbsp;<font color="red">*</font>
	                </td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>销售数量：</td>
	                <td>
                        <input type="text" id="saleNum"id="saleNum" name="saleNum" class="easyui-numberbox" required="true" readonly="readonly"/>&nbsp;<span id="sp"></span>
                        &nbsp;<font color="red">*</font>

	                </td>
	            </tr>
	            <tr>
	                <td>签订协议类型：</td>
	                <td>
                        <input type="radio" name="agreement" id="agreement" value="新签" checked="checked" readonly="readonly"/>新签
                        <input name="agreement" type="radio" id="agreement" value="续约" readonly="readonly"/> 续约
	                </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>签约时间：</td>
                    <td><input type="text" id="contractTime" name="contractTime" class="easyui-datebox" required="true" readonly="readonly"/>&nbsp;<font color="red">*</font></td>
                    </td>
                </tr>
	              <tr>
	                <td>实收金额：</td>
	                <td>
						<input type="text" id="realMoney" name="realMoney" class="easyui-numberbox" required="true" readonly="readonly"/>
						&nbsp;<font color="red">*</font>
					</td>
                      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      <td> <select class="easyui-combobox" id="teacher" name="teacher"  editable="false" panelHeight="auto" readonly="readonly" >
                          <option value="0">顾问：</option>
                          <option value="1">老师：</option>
                      </select></td>
                      <td><input type="text" id="counselor" name="counselor" class="easyui-validatebox" required="true" readonly="readonly"/>&nbsp;<font color="red">*</font></td>
                      </td>
				  </tr>
				<tr>

                    <td>支付方式：</td>
                    <td>
                        <select class="easyui-combobox" id="payMode" name="payMode"  editable="false" panelHeight="auto" readonly="readonly" >
                            <option value="0">请选择...</option>
                            <option value="POS机">POS机</option>
                            <option value="支付宝">支付宝</option>
                            <option value="微信">微信</option>
                            <option value="现金">现金</option>
                        </select>
					</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>付款性质：</td>
                    <td>
                        <input type="radio" name="property" id="property" value="全款" checked="checked" readonly="readonly" />全款
                        <input name="property" type="radio" id="property" value="订金" readonly="readonly"/> 订金
                        <input type="radio" name="property" id="property" value="尾款" readonly="readonly"/>尾款
                    </td>

				</tr>

	            <tr id="parentIdDiv" hidden="true" >
                    <td>银行卡号:</td>
                    <td>
                        <input type="text" id="cardNum" name="cardNum" class="easyui-numberbox" readonly="readonly" />
                    </td>
                </tr>
	            <tr> 

	            </tr>
	            <tr>
	                <td>促销/备注：</td>
	                <td colspan="4">
	                    <input type="text" id="promotion" name="promotion" style="width: 400px" class="easyui-validatebox" readonly="readonly"/>
	                </td>
	            </tr>
	            <tr>
	                <td>赠送课程：</td>
	                <td><input type="text" id="gift" name="gift" readonly="readonly"/></td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>用户来源：</td>
                   <td>
                      <select class="easyui-combobox" id="source" name="source"  editable="false" panelHeight="auto" readonly="readonly" >
                           <option value="0">请选择...</option>
                           <option value="walk in">walk in</option>
                           <option value="地推">地推</option>
                           <option value="陌call">陌call</option>
                           <option value="转介绍">转介绍</option>
                           <option value="大众点评">大众点评</option>
                           <option value="肄业">肄业</option>
                           <option value="转中心">转中心</option>
                           <option value="市推">市推</option>
                           <option value="续费">续费</option>
                       </select>&nbsp;<font color="red">*</font>
				   </td>
	            </tr>
				<tr>
                    <td>审核意见：</td>
                    <td>
                        <input name="state" type="radio" id="state" value="1" /> 审核通过
                        <input type="radio" name="state" id="state" value="2" />填写有误
                    </td>

                   <#--<td><</td>-->
				</tr>
	        </table>
	    </form>
	</div>

	<#--按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:saveExamine()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closeExamineDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/finance.js" ></script>
</body>
</html>