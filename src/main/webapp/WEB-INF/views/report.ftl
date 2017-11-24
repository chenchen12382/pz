<!doctype html>
<html>
<head>
    <#include "include/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="业绩录入" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/report/center_list" fit="true" toolbar="#tb">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="name" width="100" align="center">顾问</th>
	        <th field="phoneNum" width="100" align="center" >电话量</th>
	       <#-- <th field="center" width="100" align="center" >中心</th>	-->    
	        <th field="planNum" width="200" align="center">邀约量</th>
	        <th field="arriveNum" width="200" align="center">到访人数</th>
	        <th field="inNum" width="100" align="center">接待人数</th>
	        <th field="orderNum" width="200" align="center" >下单人数</th>
	        <th field="source" width="100" align="center">来源</th> 
	        <th field="money" width="100" align="center">收入金额</th>
	        <th field="analysis" width="200" align="center" >未报名分析</th>
	       <#--   <th field="createDate" width="100" align="center">日期</th>
	      <th field="updateDate" width="100" align="center">更新日期</th>	-->  
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
    <#-- <div>
	        &nbsp;中心：&nbsp;<input type="text" id="center" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>
	        &nbsp;顾问：&nbsp;<input type="text" id="name" size="20" onkeydown="if(event.keyCode==13) searchProgress()"/>	     
	        <a href="javascript:searchProgress()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	    </div>-->
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:720px;height:330px;padding: 10px 20px"
         closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post">
            <input type="hidden" name="id" id="id" />
            <table cellspacing="8px">           
              <tr>
                   <td>顾问：</td>
                    <td>
                        <input "text" id="name" name="name" required="true"/>
                    </td>
                     <td>电话量：</td>
                    <td>
                        <input type="text" id="phoneNum" name="phoneNum" class="easyui-numberbox" required="true"/>
                    </td>  
                 </tr>  
             <#--    <tr>
                   <td>中心：</td>
                    <td>
                        <input class="easyui-combobox" id="center" name="center"
                             data-options=" editable:false, valueField:'center',
	                	textField:'center', url:'${ctx}/center/find_all'"/>
                    </td>             
                </tr>  -->
                <tr>
                
                 <td>邀约量：</td>
                    <td>
                        <input type="text" id="planNum" name="planNum" class="easyui-numberbox" required="true"/>
                    </td>
                   <td>到访人数：</td>
                   <td>
                        <input type="text" id="arriveNum" name="arriveNum" class="easyui-numberbox" required="true"/>&nbsp;
                        <font color="red">*</font>
                    </td>
                              
               </tr>
               <tr>
               
                   <td>实际人数：</td>
                    <td>
                        <input type="text" id="inNum" name="inNum" class="easyui-numberbox" required="true"/>&nbsp;
                        <font color="red">*</font>
                    </td>     
                   <td>下单人数</td>
                    <td><input type="text" id="orderNum" name="orderNum" class="easyui-numberbox" required="true"/>
                    <font color="red">*</font>
                    </td>     
             </tr>
             <tr>
                 
               <td>金额：</td>
                    <td>
                        <input type="text" id="money" name="money" class="easyui-numberbox" required="true"/>&nbsp;
                    </td>
                    <td>来源：</td>
                    <td>
                      <select class="easyui-combobox" id="source" name="source"  editable="false" panelHeight="auto" >
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
             </tr>
             <tr>
                    <td>未报名分析：</td>
                    
                     <td colspan="4">
                        <textarea rows="5" cols="50" id="analysis" name="analysis" style="margin: 0px;width: 401px;height: 38px;resize: none;"></textarea>
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