
$(document).ready(function() {
	    var date = new Date();  
	    var year = date.getFullYear();  
	    var month = date.getMonth()+1;  
	    var day = date.getDate();  
	    var hour = date.getHours();  
	    var minute = date.getMinutes();  
	    //alert(year+'年'+month+'月'+day+'日 '+hour+':'+minute+':'+second)  
	    presentTime=year+'-'+month+'-'+day;  
	    alert( year+'-'+month+'-'+day+''+hour+':'+minute);  
	    //$("#time").val(presentTime); 
    $("#month").combobox({ // 层级改变
        // 层级改变时触发
        onChange:function(month) { // select的change事件
             if(month == "本月") {
            	 $("#month").val(presentTime);
            }else {	
            	$("#month").val(presentTime);
            } 
        }

    });
})

function openAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加指标");
    $("#id").val('');
}
function openModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1) {
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row = selectedRows[0];
    console.log(row)
    $("#fm").form("load", row);
    $("#dlg").dialog("open").dialog("setTitle","编辑指标内容");
}

function savetarget() {
	var id = $("#id").val();
	var url = "add";
	if (id != null && $.trim(id).length >0 && !isNaN(id)) { // 确定id是数字
		url = "update";
	}
    $("#fm").form("submit", {
        url: url,
        onSubmit:function() {
            if(isEmpty($("#target").val())) {
                $.messager.alert("系统提示", "请输入指标信息！");
                return false;
            }
            return $(this).form("validate");
        },
        success:function(result) {
            var result = JSON.parse(result);
            if(result.resultCode == 1) {
                $.messager.alert("系统提示", "保存成功！");
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            } else {
                $.messager.alert("系统提示", result.resultMessage);
                return;
            }
        }
    });
}

function resetValue() {
    $("#district").val("");
    $("#month").val("");
    $("#target").val("");
    $("#id").val('');
}

function closetargetDialog(){
    $("#dlg").dialog("close");
    resetValue();
}

function deletetargets() {

    var strIds=[];
    var selectedRows = $("#dg").datagrid('getSelections');
    console.log(selectedRows);
    for(var i=0; i<selectedRows.length; i++) {
        strIds.push(selectedRows[i].id);
    }
    var ids=strIds.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？", function(r) {
        if(r) {
            $.post("delete",{ids : ids}, function(result) {
                if(result.resultCode == 1) {
                    $.messager.alert("系统提示","数据已成功删除！");
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
                }
            });
        }
    });
}
