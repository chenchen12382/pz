function openAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加中心");
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
    $("#dlg").dialog("open").dialog("setTitle","编辑中心内容");
}

function openExcelDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1) {
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row = selectedRows[0];
    console.log(row)
    $("#exfm").form("load", row);
    $("#edlg").dialog("open").dialog("setTitle","上传EXCEL");
}

function  saveExcel() {
    var centerId = $("#centerId").val();
    var data = {"centerId":centerId};
    var xybh = $("#upExl").val();
    var sjbh = $("#upSjbh").val();
    var sjbh_lbs = $("#upLbs").val();
    if((xybh!=""&&sjbh!="")||(xybh!=""&&sjbh_lbs!="")||(sjbh!=""&&sjbh_lbs!="") ){

        $.messager.alert("系统提示", "只能选择一个文件上传！");
        return;
    }
    if(xybh == "" && sjbh == ""&&sjbh_lbs==""){
        $.messager.alert("系统提示", "请选择文件上传！");
        return;
    }



    $("#exfm").ajaxSubmit({
        type: "POST",
        url:"readExcel",
        dataType: "json",
        data:data,
        success: function(result){
            // result = JSON.parse(result);
            if(result.resultCode == 1) {
                $.messager.alert("系统提示", result.resultMessage);
                // closeCustomerDialog();
                // $("#dg").datagrid("reload");
                $("#src").val(result.result);
            }else{
                $.messager.alert("系统提示",result.resultMessage);
                return;
            }
        }
    });


}




function savecenter() {
	
	var id = $("#id").val();
	var url = "add";
	if (id != null && $.trim(id).length >0 && !isNaN(id)) { // 确定id是数字
		url = "update";
	}
    $("#fm").form("submit", {
        url: url,
        onSubmit:function() {
            if(isEmpty($("#center").val())) {
                $.messager.alert("系统提示", "请输入中心信息！");
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
    $("#centerName").val("");
    $("#centerRemark").val("");
    $("#id").val('');
}

function closecenterDialog(){
    $("#dlg").dialog("close");
    resetValue();
}

function closeUploadDialog() {
    $("#edlg").dialog("close");
    $("#upExl").val("");
    $("#upSjbh").val("");

}


function deletecenters() {

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
