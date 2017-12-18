
// 搜索
function searchReport() {
    var start = $("#start").datebox('getValue');
    var over = $("#over").datebox('getValue');
    var center = $("#center").val();
    if(start>over){
        alert("开始时间不能大于结束时间！")
    }
    var data = {"start":start,"over":over,"center":center};
    $("#dg").datagrid('load', data);

}



//搜索
function searchProgress() {
	var data = {
			center: $("#center").val(),
			name: $("#name").val()

	}
	$("#dg").datagrid('load', data);
}

// 弹出框弹出
function openReportAddDialog() {
	$("#dlg").dialog('open').dialog('setTitle', "业绩录入");
}

// 弹出修改窗体
function openReportModifyDialog() {
	 var selectedRows = $("#dg").datagrid("getSelections");
	    if(selectedRows.length != 1) {
	        $.messager.alert("系统提示","请选择一条要编辑的数据！");
	        return;
	    }
	    var row = selectedRows[0];
	    console.log(row)
	    $("#fm").form("load", row);
	    $("#dlg").dialog("open").dialog("setTitle","编辑报表信息");}

// 保存
function saveReport() {
	
	var id = $("#id").val();
	var url = "add";
	if (id != null && $.trim(id).length > 0 && !isNaN(id)) { // 判断是否为数字
		url = "update";
	}
    var center = $('#center').val();
    var plan_num = $('#planNum').val();
    var arrive_num = $('#arriveNum').val();
    var in_num = $('#inNum').val();
    var order_num = $('#orderNum').val();
    if (isEmpty(planNum)) {
    	$.messager.alert("系统提示","请填写邀约人数！");
    }
    if (isEmpty(arriveNum)) {
    	$.messager.alert("系统提示","请填写实际人数！");
    }
    if (isEmpty(orderNum)) {
    	$.messager.alert("系统提示","请填写下单人数！");
    }
    if (isEmpty(inNum)) {
    	$.messager.alert("系统提示","请填写到访人数！");
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
	$("#center").val("");
	$("#name").val("");
	$("#phoneNum").numberbox('setValue',"");
    $("#phoneNum").numberbox('setValue',"");
    $("#planNum").numberbox('setValue',"");
    $("#arriveNum").numberbox('setValue',"");
    $("#inNum").numberbox('setValue',"");
    $("#orderNum").numberbox('setValue',"");
    $("#money").numberbox('setValue',"");
    $("#analysis").val("");
    
}

// 关闭弹出框
function closeReportDialog() {
	$("#dlg").dialog('close');
	// 置空
	resetValue();
	
}


