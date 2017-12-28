<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<#include "include/common.header.ftl" >
    <script src="http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js"></script>
    <script type="application/javascript" src="${ctx}/js/jquery.jqprint-0.3.js"></script>

</head>
<body style="margin: 1px">
<table id="dg" title="合同录入" class="easyui-datagrid"
       fitColumns="false" pagination="true" rownumbers="true"
       url="${ctx}/print_contract/list" fit="true" toolbar="#tb">

    <thead data-options="frozen:true">
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
    <#--<th field="id" width="30" align="center">编号</th>-->
        <th field="centerName" width="50" align="center">中心</th>
        <th field="contractType" width="100" align="center">合同类型</th>
        <th field="bName" width="100" align="center">宝宝姓名</th>
        <th field="bNikeName" width="100" align="center">别名</th>
        <th field="bBirthday" width="100" align="center">出生日期</th>
        <th field="bSex" width="100" align="center">宝宝性别</th>

    </tr>
    </thead>
<#--滚定-->
    <thead>
    <tr>
        <th field="fName" width="100" align="center">父亲姓名</th>
        <th field="mName" width="80" align="center">母亲姓名</th>
        <th field="classNum" width="60" align="center">课时数</th>
        <th field="startDate" width="80" align="center">有效期开始</th>
        <th field="endDate" width="100" align="center">有效期截止</th>
        <th field="giveClass" width="80" align="center">赠送课程</th>
        <th field="giveTicket" width="80" align="center">赠券</th>

    </tr>
    </thead>
</table>
<#--工具栏-->
<div id="tb">
    <div>
        <a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <#--<a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>-->

    <#--<a href="javascript:openRestMoneyDialog()" class="easyui-linkbutton" iconCls="icon-wk" plain="true">尾款</a>-->

    <#--<a href="javascript:deleteCustomer()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>-->

    <#--<a href="javascript:openAgreement()" class="easyui-linkbutton" iconCls="icon-zfdj" plain="true">作废单据</a>-->
        <a href="javascript:showPrint()" class="easyui-linkbutton" iconCls="icon-http" plain="true">打印预览</a>


    </div>
    <div>
    <#--&nbsp;客户姓名：&nbsp;<input type="text" id="name" size="15" onkeydown="if(event.keyCode==13) searchDayReport()"/>-->
    <#--&nbsp;课程：&nbsp;<input type="text" id="s_class" size="15" onkeydown="if(event.keyCode==13) searchDayReport()"/>-->
    <#--&nbsp;开始时间：&nbsp;<input type="text" id="start" class="easyui-datebox" size="15"  onkeydown="if(event.keyCode==13) searchDayReport()"/>-->
    <#--&nbsp;结束时间：&nbsp;<input type="text" id="over" class="easyui-datebox" size="15" onkeydown="if(event.keyCode==13) searchDayReport()"/>-->
    <#--<br/>-->
    <#--&nbsp;付款性质：&nbsp;<select class="easyui-combobox" id="s_property" name="s_property"  editable="false" panelHeight="auto" style="width:150px" onkeydown="if(event.keyCode==13) searchFinance()" >-->
    <#--<option value="0">请选择...</option>-->
    <#--<option value="全款">全款</option>-->
    <#--<option value="订金">订金</option>-->
    <#--<option value="尾款">尾款</option>-->
    <#--</select>-->
        </select>
    <#--<a href="javascript:searchDayReport()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>-->
    <#--<a href="javascript:fileDownload()" class="easyui-linkbutton" id="download" iconCls="icon-download" plain="true">导出excel</a>-->
    </div>
</div>

<#--弹出框-->
<div id="dlg" class="easyui-dialog" style="width:680px;height:630px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons" enctype="multipart/form-data">

    <form id="fm" method="post">
        <input type="hidden" id="id" name="id"/>
    <#--<input type="hidden" id="src" name="src" />-->


        <table cellspacing="8px">
            <tr>
                <td><h4 style="color: red">父亲资料</h4></td>
            </tr>
            <tr>
                <td>父亲姓名：</td>
                <td>
                    <input type="text" id="fName" name="fName" class="easyui-validatebox" required="true">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>手机号：</td>
                <td>
                    <input type="text" id="fPhone" name="fPhone" class="easyui-numberbox">
                </td>
            </tr>
            <tr>
                <td>邮件：</td>
                <td>
                    <input type="text" id="fMail" name="fMail" class="easyui-validatebox">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>工作单位：</td>
                <td>
                    <input type="text" id="fWork" name="fWork" class="easyui-validatebox"/>
                    &nbsp;<font color="red">*</font>

                </td>
            </tr>

            <tr>
                <td><h4 style="color: red">母亲资料</h4></td>
            </tr>
            <tr>
                <td>母亲姓名：</td>
                <td>
                    <input type="text" id="mName" name="mName" class="easyui-validatebox" required="true">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>手机号：</td>
                <td>
                    <input type="text" id="mPhone" name="mPhone" class="easyui-numberbox">
                </td>
            </tr>
            <tr>
                <td>邮件：</td>
                <td>
                    <input type="text" id="mMail" name="mMail" class="easyui-validatebox">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>工作单位：</td>
                <td>
                    <input type="text" id="mWork" name="mWork" class="easyui-validatebox"/>
                    &nbsp;<font color="red">*</font>

                </td>
            </tr>


            <tr>
                <td><h4 style="color: red">宝宝资料</h4></td>
            </tr>
            <tr>
                <td>宝宝姓名：</td>
                <td>
                    <input type="text" id="bName" name="bName" class="easyui-validatebox" required="true">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>别 名：</td>
                <td>
                    <input type="text" id="bNikeName" name="bNikeName" class="easyui-validatebox">
                </td>
            </tr>
            <tr>
                <td>出生日期：</td>
                <td>
                    <input type="text" id="bBirthday" name="bBirthday" class="easyui-datebox">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>性别：</td>
                <td>
                    <select class="easyui-combobox" id="bSex" name="bSex" editable="false" panelHeight="auto"
                            required="true">
                        <option value="2">请选择..</option>
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select></td>
            </tr>


            <tr>
                <td><h4 style="color: red">课时信息</h4></td>
            </tr>
            <tr>
                <td>课时数：</td>
                <td>
                    <input type="text" id="classNum" name="classNum" class="easyui-numberbox" required="true">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>有效期起：</td>
                <td>
                    <input type="text" id="startDate" name="startDate" class="easyui-datebox">
                </td>
            </tr>
            <tr>
                <td>有效期止：</td>
                <td>
                    <input type="text" id="endDate" name="endDate" class="easyui-datebox">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>赠送课程：</td>
                <td>
                    <input type="text" id="giveClass" name="giveClass" class="easyui-numberbox"/>
                    &nbsp;<font color="red">*</font>

                </td>
            </tr>
            <tr>
                <td>赠券：</td>
                <td>
                    <input type="text" id="giveTicket" name="giveTicket" class="easyui-numberbox">
                </td>

            </tr>
        </table>
    </form>
</div>
<#--打印div-->
<div id="ddd" >
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <table  width="100%"  cellpadding="0" cellspacing="0" style="table-layout:fixed; border-spacing:4px;font-size: 14px;"  >
        <tr>
            <td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="fName_p">cc</span></td>
            <td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="mName_p">cc</span></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="f_phone">cc</span></td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="m_phone">cc</span></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="f_email">cc</span></td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="m_email">cc</span></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="f_addr">cc</span></td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="m_addr">cc</span></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="address">cc</span></td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="m_address">cc</span></td>
        </tr>

    </table >
    <br/>
    <table width="100%"  cellpadding="0" cellspacing="0" style="table-layout:fixed; border-spacing:4px;font-size: 14px;">
        <tr>
            <td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="b_name">cc</span></td>
            <td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="b_nName">cc</span></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="b_day">cc</span></td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="b_sex">cc</span></td>
        </tr>
    </table>
    <br/>
    <br/>
    <table width="100%"  cellpadding="0" cellspacing="0" style="table-layout:fixed;border-spacing:4px;font-size: 14px;">
        <tr>
            <td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="c_num">cc</span></td>
            <td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="c_center">cc</span></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="c_addr">cc</span></td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="c_phone">cc</span></td>
        </tr>
    </table>
    <br/>
    <br/>
    <br/>
    <br/>
    <table width="100%"  cellpadding="0" cellspacing="0" style="table-layout:fixed;border-spacing:4px;font-size: 14px;">
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="test">cc</span></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="test">cc</span></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="test">cc</span></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="test">cc</span></td>
        </tr>
    </table>

</div>

<#--按钮-->
<div id="dlg-buttons">
    <a href="javascript:savePrint()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePrintDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

<script src="${ctx}/js/print.contract.js"></script>
</body>

</html>