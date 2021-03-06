function openAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","合同录入");
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
    $("#dlg").dialog("open").dialog("setTitle","编辑区域内容");
}


function showPrint() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1) {
        $.messager.alert("系统提示","只能选择一条数据打印！");
        return;
    }
    var row = selectedRows[0];
    // alert(row.fName);
    document.getElementById('fName_p').innerHTML=row.fName;
    document.getElementById('mName_p').innerHTML=row.mName;
    document.getElementById('f_phone').innerHTML=row.fPhone;
    document.getElementById('m_phone').innerHTML=row.mPhone;
    document.getElementById('f_email').innerHTML=row.fMail;
    document.getElementById('m_email').innerHTML=row.mMail;
    document.getElementById('f_addr').innerHTML=row.fWork;
    document.getElementById('m_addr').innerHTML=row.mWork;
    // document.getElementById('m_address').innerHTML=row.mName;
    // document.getElementById('m_address').innerHTML=row.mName;
    document.getElementById('b_name').innerHTML=row.bName;
    document.getElementById('b_nName').innerHTML=row.bNikeName;
    document.getElementById('b_day').innerHTML=row.bBirthday;
    document.getElementById('b_sex').innerHTML=row.bSex;


    // $('#fName_p').val(row.fName);








    $("#ddd").jqprint();

}



function savePrint() {
	
	var id = $("#id").val();
	var url = "add";
	if (id != null && $.trim(id).length >0 && !isNaN(id)) { // 确定id是数字
		url = "update";
	}
    $("#fm").form("submit", {
        url: url,
        onSubmit:function() {
            // if(isEmpty($("#district").val())) {
            //     $.messager.alert("系统提示", "请输入中心信息！");
            //     return false;
            // }
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
    $("#fName").val("");
    $("#fPhone").numberbox("setValue",'');
    $("#fMail").val("");
    $("#fWork").val("");
    $("#mName").val("");
    $("#mPhone").numberbox("setValue",'');
    $("#mMail").val("");
    $("#mWork").val("");
    $("#bName").val("");
    $("#bNikeName").val("");
    $("#bBirthday").datebox('setValue','');
    $("#bSex").combobox('setValue','2');
    $("#classNum").numberbox("setValue",'');
    $("#startDate").datebox('setValue','');
    $("#endDate").datebox('setValue','');
    $("#giveClass").numberbox("setValue",'');
    $("#giveTicket").numberbox("setValue",'');
    $("#id").val('');
}

function closedistrictDialog(){
    $("#dlg").dialog("close");
    resetValue();
}

function deletedistricts() {

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
