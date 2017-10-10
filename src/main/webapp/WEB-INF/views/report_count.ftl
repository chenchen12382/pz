<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="区域业绩汇总" class="easyui-datagrid"
	       fitColumns="true" pagination="false" rownumbers="true"
	       url="${ctx}/report_count/list" fit="true" toolbar="#tb" singleSelect = "false" showFooter="true">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <#--<th field="id" width="50" align="center">编号</th>-->
            <th field="district" width="100" align="center">区域</th>
	        <th field="income" width="150" align="center">完成业绩</th>

            <th field="target" width="100" align="center" >指标</th>
	        <th field="discount" width="100" align="center" formatter="formatDiscount">完成率</th>
	    </tr>
	    </thead>
	</table>

    <div id="tb">
        <div>
            &nbsp;开始时间：&nbsp;<input type="text" id="start" class="easyui-datebox" size="15" onkeydown="if(event.keyCode==13) searchCustomer()"/>
            &nbsp;结束时间：&nbsp;<input type="text" id="over" class="easyui-datebox" size="15" onkeydown="if(event.keyCode==13) searchCustomer()"/>

            <a href="javascript:searchCustomer()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<script src="${ctx}/js/report.count.js" ></script>
</body>
</html>