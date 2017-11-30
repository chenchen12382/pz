
function deleteSjbhs() {
    var strIds=[];
    var selectedRows = $("#dg").datagrid('getSelections');
    console.log(selectedRows);
    for(var i=0; i<selectedRows.length; i++) {
        strIds.push(selectedRows[i].id);
    }
    var ids=strIds.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？", function(r) {
        if(r) {
            $.post("/protocol/deleteSjbh",{ids : ids}, function(result) {
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

function searchCenter() {
    var center = $("#s_center").val();
    var sjbh = $("#s_sjbh").val();
    // var over = $("#over").datebox('getValue');

    var data = { "center":center,"bh":sjbh};
    $("#dg").datagrid('load', data);
}