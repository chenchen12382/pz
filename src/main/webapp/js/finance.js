// 格式化分配状态
//function formatState(value) {
//	if (value == null) {
//		return "未知";
//	}else if (value == 0) {
//		return "未分配";
//	} else {
//		return "已分配";
//	}
//}

// 搜索
function searchProgress() {
	var data = {
			center: $("#xybh").val(),
//			centerName: $("#s_centerName").val(),
			name: $("#s_createMan").val()
//			state:$("#s_state").combobox('getValue')
	}
	$("#dg").datagrid('load', data);
}

// 弹出框弹出
function openReportAddDialog() {
	$("#dlg").dialog('open').dialog('setTitle', "业绩录入");
}

// 弹出修改窗体
function openReportModifyDialog() {
	// 获取选中的行
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length != 1) {
		$.messager.alert("系统提示", "请选择一行进行修改");
		return;
	}
	// 给form表单赋值
	var row = rows[0];
	$("#fm").form('load', row);
	$("#dlg").dialog('open').dialog('setTitle', '修改')
}

// 保存
function saveReport() {
	
	var url = "add";
	var id = $("#id").val();
	if (id != null && $.trim(id).length > 0 && !isNaN(id)) { // 判断是否为数字
		url = "update";
	}
//    var customerName = $('#customerId').combobox('getText');
    var xybh = $('#xybh').val();
    var sjbh = $('#sjbh').val();
    var hybh = $('#hybh').val();
    var name = $('#name').val();
   
    
    if (isEmpty(xybh)) {
    	$.messager.alert("系统提示","请录入协议编号！");
    }
    if (isEmpty(sjbh)) {
    	$.messager.alert("系统提示","请录入收据编号！");
    }
    if (isEmpty(hybh)) {
    	$.messager.alert("系统提示","请录入会员编号！");
    }
    if (isEmpty(name)) {
    	$.messager.alert("系统提示","请录入会员姓名！");
    }
    
    
//    $("#customerName").val(customerName);
    $("#fm").form("submit",{
        url: url, // 相对路径
        onSubmit: function() {
            return $(this).form("validate");
        },
        success:function(result) {
            result = JSON.parse(result);
            if(result.resultCode == 1) {
                $.messager.alert("系统提示", "保存成功！");
                resetValue(); // 置空
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            }else{
                $.messager.alert("系统提示",result.resultMessage);
                return;
            }
        }
    });
}

// 删除
function deleteReport() {
	// 获取选中的行
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("系统提示", "至少选择一行进行删除");
		return;
	}
	// 获取选中行的ID
	var ids = [];
	for(var i =0; i < rows.length; i++) {
		console.log(JSON.stringify(rows[i])); // 将对象转化为json字符串
		ids.push(rows[i].id);
	}
	var content = "您确定要删除这<font color=red>" + rows.length + "</font>条数据吗？";
	1,2,3
	$.messager.confirm("系统提示", content, function(r) {
		if (r) {
			$.post('delete', {ids:ids.join(',')}, function(resp) {
				if(resp.resultCode == 1) { // 删除成功
					$.messager.alert('系统提示', resp.resultMessage);
					$("#dg").datagrid('load'); // 重新刷新数据
				} else if (resp.resultCode == 201){
					$.messager.alert('系统提示', resp.resultMessage);
					window.parent.location.href= '/index';
				} else {
					$.messager.alert('系统提示', resp.resultMessage);
				}
			});
		}
	});
	
}

// 重置
function resetValue(){
	$("#xybh").val("");
    $("#sjbh").val("");
    $("#hybh").val("");
    $("#name").val("");
}

// 关闭弹出框
function closeReportDialog() {
	resetValue();
	$("#dlg").dialog('close');
}
