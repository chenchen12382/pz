<!doctype html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
<table id="dg" title="营收日报表管理" class="easyui-datagrid"
       fitColumns="false" pagination="true" rownumbers="true"
       url="${ctx}/finance/list" fit="true" toolbar="#tb" showFooter="true">

    <thead data-options="frozen:true" >
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="center" width="50" align="center">中心</th>
        <th field="name" width="80" align="center">客户姓名</th>
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
        <th field="price" width="80" align="center">课程单价</th>
        <th field="shouldMoney" width="80" align="center">标准金额</th>
        <th field="realMoney" width="100" align="center" >实际金额</th>
        <th field="discount" width="100" align="center" formatter="formatDiscount">折扣</th>
        <th field="payMode" width="100" align="center" >支付方式</th>
        <th field="property" width="80" align="center">付款性质</th>
        <th field="contractTime" width="100" align="center">签约时间</th>
        <th field="cardNum" width="100" align="center" >银行卡号</th>
        <th field="counselor" width="100" align="center" >顾问/老师</th>
        <th field="teacher" width="100" align="center" formatter="formatTeacher">职位</th>
        <th field="promotion" width="100" align="center" formatter="formatPromotion">促销</th>
        <th field="gift"   width="100"  align="center" >赠送课程</th>
        <th field="source"   width="100"  align="center" >客戶來源</th>
        <th field="createDate"   width="100"  align="center" >创建时间</th>
    </tr>
    </thead>
</table>
<#--工具栏-->
<div id="tb">
    <#--<div>-->
        <#--&lt;#&ndash;<a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>&ndash;&gt;-->
        <#--&lt;#&ndash;<a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>&ndash;&gt;-->
        <#--&lt;#&ndash;<a href="javascript:deleteCustomer()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>&ndash;&gt;-->

    <#--</div>-->


    <div>
        &nbsp;中心：&nbsp;<input type="text" id="center" size="15" onkeydown="if(event.keyCode==13) searchFinance()"/>
        &nbsp;课程：&nbsp;<input type="text" id="s_class" size="15" onkeydown="if(event.keyCode==13) searchFinance()"/>
        &nbsp;支付方式：&nbsp;<select class="easyui-combobox" id="s_payMode" name="s_payMode"  editable="false" panelHeight="auto"  style="width:150px" onkeydown="if(event.keyCode==13) searchFinance()" >
                                <option value="0">请选择...</option>
                                <option value="POS机">POS机</option>
                                <option value="支付宝">支付宝</option>
                                <option value="微信">微信</option>
                                <option value="现金">现金</option>
                            </select>
        &nbsp;付款性质：&nbsp;<select class="easyui-combobox" id="s_property" name="s_property"  editable="false" panelHeight="auto" style="width:150px" onkeydown="if(event.keyCode==13) searchFinance()" >
        <option value="0">请选择...</option>
        <option value="全款">全款</option>
        <option value="订金">订金</option>
        <option value="尾款">尾款</option>
        </select>
        </br>
        &nbsp;录入时间：&nbsp;<input type="text" id="start" class="easyui-datebox" size="15"  onkeydown="if(event.keyCode==13) searchFinance()"/>
        &nbsp;至结束时间：&nbsp;<input type="text" id="over" class="easyui-datebox" size="15" onkeydown="if(event.keyCode==13) searchFinance()"/>
        &nbsp;签约时间：&nbsp;<input type="text" id="qstart" class="easyui-datebox" size="15"  onkeydown="if(event.keyCode==13) searchFinance()"/>
        &nbsp;至结束时间：&nbsp;<input type="text" id="qover" class="easyui-datebox" size="15" onkeydown="if(event.keyCode==13) searchFinance()"/>
        </select>
        <a href="javascript:searchFinance()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
        <a href="javascript:fileDownload()" class="easyui-linkbutton" id="download" iconCls="icon-download" plain="true">导出excel</a>
    </div>
</div>

<#--弹出框-->
<#--<div id="dlg" class="easyui-dialog" style="width:700px;height:400px;padding: 10px 20px"-->
     <#--closed="true" buttons="#dlg-buttons">-->

    <#--<form id="fm" method="post">-->
        <#--<input type="hidden" id="id" name="id" />-->
        <#--<table cellspacing="8px">-->
            <#--<tr>-->
                <#--<td>协议编号：</td>-->
                <#--<td><input type="text" id="xybh" name="xybh" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>-->
                <#--<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
                <#--<td>收据编号：</td>-->
                <#--<td><input type="text" id="sjbh" name="sjbh" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>-->
                <#--</td>-->
            <#--</tr>-->
            <#--<tr>-->
                <#--<td>会员编号：</td>-->
                <#--<td>-->
                    <#--<input type="text" id="hybh" name="hybh" class="easyui-validatebox" >-->
                <#--</td>-->
                <#--<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
                <#--<td>客户姓名：</td>-->
                <#--<td>-->
                    <#--<input type="text" id="o_name" name="name" class="easyui-validatebox" required="true">-->
                    <#--&nbsp;<font color="red">*</font>-->

                <#--</td>-->
            <#--</tr>-->
            <#--<tr>-->
                <#--<td>销售课程：</td>-->
                <#--<td>-->
                    <#--<input class="easyui-combobox" id="saleClass" name="saleClass" data-options="panelHeight:'auto',editable:false,valueField:'saleClass',textField:'saleClass',url:'${ctx}/priceClass/find_all'" />&nbsp;<font color="red">*</font>-->
                <#--</td>-->
                <#--<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
                <#--<td>销售数量：</td>-->
                <#--<td>-->
                    <#--<input type="text" id="saleNum"id="saleNum" name="saleNum" class="easyui-numberbox" required="true" />&nbsp;<span id="sp"></span>-->
                    <#--&nbsp;<font color="red">*</font>-->

                <#--</td>-->
            <#--</tr>-->
            <#--<tr>-->
                <#--<td>签订协议类型：</td>-->
                <#--<td>-->
                    <#--<input type="radio" name="agreement" id="agreement" value="新签" checked="checked"/>新签-->
                    <#--<input name="agreement" type="radio" id="agreement" value="续约"/> 续约-->
                <#--</td>-->
            <#--</tr>-->
            <#--<tr>-->
                <#--<td>实收金额：</td>-->
                <#--<td>-->
                    <#--<input type="text" id="realMoney" name="realMoney" class="easyui-numberbox" required="true" />-->
                    <#--&nbsp;<font color="red">*</font>-->
                <#--</td>-->
                <#--<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
                <#--<td>顾问：</td>-->
                <#--<td><input type="text" id="counselor" name="counselor" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>-->
                <#--</td>-->
            <#--</tr>-->
            <#--<tr>-->

                <#--<td>支付方式：</td>-->
                <#--<td>-->
                    <#--<select class="easyui-combobox" id="payMode" name="payMode"  editable="false" panelHeight="auto" >-->
                        <#--<option value="请选择...">请选择...</option>-->
                        <#--<option value="POS机">POS机</option>-->
                        <#--<option value="支付宝">支付宝</option>-->
                        <#--<option value="微信">微信</option>-->
                        <#--<option value="现金">现金</option>-->
                    <#--</select>-->
                <#--</td>-->
                <#--<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
                <#--<td>付款性质：</td>-->
                <#--<td>-->
                    <#--<input type="radio" name="property" id="property" value="全款" checked="checked"/>全款-->
                    <#--<input name="property" type="radio" id="property" value="订金"/> 订金-->
                <#--</td>-->

            <#--</tr>-->

            <#--<tr id="parentIdDiv" hidden="true" >-->
                <#--<td>银行卡号:</td>-->
                <#--<td>-->
                    <#--<input type="text" id="cardNum" name="cardNum" class="easyui-numberbox"  />-->
                <#--</td>-->
            <#--</tr>-->
            <#--<tr>-->

            <#--</tr>-->
            <#--<tr>-->
                <#--<td>促销/备注：</td>-->
                <#--<td colspan="4">-->
                    <#--<input type="text" id="promotion" name="promotion" style="width: 400px" class="easyui-validatebox" />-->
                <#--</td>-->
            <#--</tr>-->
            <#--<tr>-->
                <#--<td>赠送课程：</td>-->
                <#--<td><input type="text" id="gift" name="gift" /></td>-->
                <#--<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
                <#--<td>用户来源：</td>-->
                <#--<td>-->
                    <#--<select class="easyui-combobox" id="source" name="source"  editable="false" panelHeight="auto" >-->
                        <#--<option value="">请选择...</option>-->
                        <#--<option value="上门">上门</option>-->
                        <#--<option value="电话">电话</option>-->
                        <#--<option value="转介绍">转介绍</option>-->
                        <#--<option value="肄业合作">肄业合作</option>-->
                        <#--<option value="大众点评">大众点评</option>-->
                        <#--<option value="官网">官网</option>-->
                        <#--<option value="微信">微信</option>-->
                        <#--<option value="口碑">口碑</option>-->
                        <#--<option value="其他">其他</option>-->
                    <#--</select>&nbsp;<font color="red">*</font>-->
                <#--</td>-->
            <#--</tr>-->
        <#--</table>-->
    <#--</form>-->
<#--</div>-->
<#--&lt;#&ndash;按钮&ndash;&gt;-->
<#--<div id="dlg-buttons">-->
    <#--<a href="javascript:saveCustomer()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>-->
    <#--<a href="javascript:closeCustomerDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>-->
<#--</div>-->

<script src="${ctx}/js/finance.js" ></script>
</body>
</html>