$(document).ready(function() {
    $("#payMode").combobox({ // 层级改变
        // 层级改变时触发
        onChange:function(payMode) { // select的change事件
             if(payMode == "POS机") {
            	 $("#parentIdDiv").show();
            } else {	
            	 $("#parentIdDiv").hide();
            }
          
        }
    });
})





function resetValue() {
    
    $("#s_state").combobox('setValue', 0);
}


// 搜索
function searchCustomer() {
    var customerNo = $("#s_customerNo").val();
    var customerName = $("#s_customerName").val();
    var data = {'customerNo': customerNo, "customerName": customerName};
    $("#dg").datagrid('load', data);
}

function openAddDialog() {
    $("#dlg").dialog('open').dialog('setTitle', "添加客户信息");
}

function openModifyDialog() {
    // form 表单赋值 获取选中行
    var selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows == null || selectedRows.length != 1) {
        $.messager.alert("系统提示", "只能选择一条进行修改");
        return;
    }
    var row = selectedRows[0];
    $("#fm").form('load', row); // form 赋值
    $("#dlg").dialog('open').dialog('setTitle', "修改客户信息");
}

// 保存
function saveCustomer() {

    var url = "add";
    var id = $("#id").val();
    if (id != null && $.trim(id).length > 0 && !isNaN(id)) { // 判断是否为数字
        url = "update";
    }
    $("#fm").form("submit",{
        url: url, // 相对路径
        onSubmit: function() {
            return $(this).form("validate");
        },
        success:function(result) {
            result = JSON.parse(result);
            if(result.resultCode == 1) {
                $.messager.alert("系统提示", "保存成功！");
                closeCustomerDialog();
                $("#dg").datagrid("reload");
            }else{
                $.messager.alert("系统提示",result.resultMessage);
                return;
            }
        }
    });
}

function deleteCustomer() {
    var selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows == null || selectedRows.length < 1) {
        $.messager.alert("系统提示", "至少选择一条进行删除");
        return;
    }
    var ids = [];
    for (var i = 0; i < selectedRows.length; i++) {
        ids.push(selectedRows[i].id);
    }
    var tips = "您确定要删除<font color='red'>"+ ids.length +"</font>条记录吗？";
    $.messager.confirm("系统提示", tips, function(r){
        if (r) {
            $.post('delete', {"ids": ids.join(",")}, function(resp) {
                if (resp.resultCode == 1) {
                    $.messager.alert('系统提示', resp.resultMessage);
                    closeCustomerDialog();
                    $("#dg").datagrid("reload");
                } else {
                    alert(resp.resultMessage);
                }
            });
        }
    });
}

// 关闭
function closeCustomerDialog() {
    resetValue();
    $("#dlg").dialog('close');
}



