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

$("#printContainer").jqprint({
    debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
    importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
    printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
    operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
});

function showPrint() {

    // var id = $("#id").val();
    // if(id==null||id.length>1){
    //     $.messager.alert("系统提示", "请选择一条数据打印！");
    // }
    //
    // var selectedRows = $("#dg").datagrid('getSelections');
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
