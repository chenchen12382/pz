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


$(document).ready(function() {
    $("#saleClass").combobox({ // 层级改变
        // 层级改变时触发
        onSelect:function(saleClass) { // select的change事件
            var v=saleClass.saleClass;
            var i=document.getElementById("sp").innerHTML;
            console.log(v);
            console.log(i);
            if(v == "亲子课" || v=="幼小衔接") {
                document.getElementById('sp').innerHTML='节';

            } else {
                document.getElementById('sp').innerHTML='月';
            }

        }

    });
})

function uploadFiles() {
    $("#fm").ajaxSubmit({
        type: "POST",
        url:"uploadImg",
        dataType: "json",
        success: function(result){
            // result = JSON.parse(result);
            if(result.resultCode == 1) {
                 $.messager.alert("系统提示", "文件上传成功！");
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

function formatImg(value) {
    if (value != null) {
        alert("<img src='"+value+"' />");
        return "<img src='"+value+"' />"
    }
    return '';

}

// //图片添加路径
// function imgFormatter(value,row,index){
//     if('' != value && null != value){
//         var strs = new Array(); //定义一数组
//         if(value.substr(value.length-1,1)==","){
//             value=value.substr(0,value.length-1)
//         }
//         strs = value.split(","); //字符分割
//         var rvalue ="";
//         for (var i=0;i<strs.length ;i++ ){
//             rvalue += "<img onclick=download(\""+strs[i]+"\") style='width:66px; height:60px;margin-left:3px;' src='<%=path%>" + strs[i] + "' title='点击查看图片'/>";
//         }
//         return  rvalue;
//     }
// }
//
// //这里需要自己定义一个div   来创建一个easyui的弹窗展示图片
// function download(img){
//     var simg =  "http://....com/"+ img;
//     $('#dlg').dialog({
//         title: '预览',
//         width: 600,
//         height:750,
//         resizable:true,
//         closed: false,
//         cache: false,
//         modal: true
//     });
//     $("#simg").attr("src",simg);
//
// }

function formatDiscount(value) {
    if(value!=null){
        return value+'%' ;
    }else{
        return null ;
    }
}

function formatTeacher(value) {
    if(value==0){
        return '顾问' ;
    }else if(value == 1){
        return '老师' ;
    }else{
        return '';
    }
}

function formatPromotion(value) {
    if (value != null) {
        return "<span title='" + value + "'>" + value + "</span>";
    }
    return '';

}

function resetValue() {
    
    // $("#dlg").combobox('setValue', 0);
    $("#xybh").numberbox('setValue',"");
    $("#sjbh").numberbox('setValue',"");
    $("#hybh").val("");
    $("#o_name").val('');
    // $("#saleClass").combobox('setValue',0);
    $("#gift").val("");
    $("#saleNum").numberbox('setValue',"");
    $("#realMoney").numberbox('setValue',"");
    $("#unfinishMoney").numberbox('setValue',"");
    $("#teacher").combobox('setValue',"0");
    $("#payMode").combobox('setValue',"0");
    $("#promotion").val('');
    $("#cardNum").numberbox('setValue',"");
    $("#source").combobox('setValue',"0");
    $("#counselor").val("");
    $("#id").val("");
    $("#contractTime").datebox('setValue',"");
    $("input[name='agreement'][value='新签']").attr("checked",true);
    $("input[name='property'][value='全款']").attr("checked",true);
    $("#src").val("");

}

function searchDayReport() {
    var name = $("#name").val();
    var saleClass = $("#s_class").val();
    var start = $("#start").datebox('getValue');
    var over = $("#over").datebox('getValue');

    var data = {'name': name, "saleClass": saleClass,"start":start,"over":over}

    $("#dg").datagrid('load', data);

}

// 搜索
function searchFinance() {
    var saleClass = $("#s_class").val();
    var start = $("#start").datebox('getValue');
    var sPayMode = $("#s_payMode").combobox('getValue');
    var sProperty = $("#s_property").combobox('getValue');
    var over = $("#over").datebox('getValue');
    var center = $("#center").val();
    if(start>over){

        alert("开始时间不能大于结束时间！")
    }
    if(sPayMode == 0){
        sPayMode = null ;
    }

    if(sProperty == 0){
        sProperty = null;
    }


    var data = {"saleClass": saleClass,"start":start,"over":over,"center":center,"sPayMode":sPayMode,"sProperty":sProperty};
    $("#dg").datagrid('load', data);

}

function post(URL, PARAMS)
{
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in PARAMS)
    {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
// alert(opt.name)
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}


// 搜索
function fileDownload() {
    var saleClass = $("#s_class").val();
    var start = $("#start").datebox('getValue');
    var sPayMode = $("#s_payMode").combobox('getValue');
    var sProperty = $("#s_property").combobox('getValue');
    var over = $("#over").datebox('getValue');
    var center = $("#center").val();
    if(start>over){

        alert("开始时间不能大于结束时间！");
        return;
    }
    if(sPayMode == 0){
        sPayMode = null ;
    }

    if(sProperty == 0){
        sProperty = null;
    }


    var data = {"saleClass": saleClass,"start":start,"over":over,"center":center,"sPayMode":sPayMode,"sProperty":sProperty};
    $("#dg").datagrid('load', data);
    post('excel', data);
    // location.reload();

}


function openAddDialog() {
    var selectedRows = $("#dg").datagrid('getSelections');
    if(selectedRows.length != 0){
        $.messager.alert("系统提示","新增时不能选择记录");
        return;
    }
    $("#dlg").dialog('open').dialog('setTitle', "添加营收日报  ");
}


function openAgreement(){
    var selectedRows = $("#dg").datagrid('getSelections');
    if(selectedRows.length != 0){
        $.messager.alert("系统提示","新增时不能选择记录");
        return;
    }
    $("#agreementdlg").dialog('open').dialog('setTitle', "作废单据  ");
}


function showImg() {

    var selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows == null || selectedRows.length < 1) {
        $.messager.alert("系统提示", "请选择一列查看图片");
        return;
    }
    if (selectedRows.length>1){
        $.messager.alert("系统提示", "只能选择一列");
    }
    var src=selectedRows[0].src;

    return "<img src='"+src+"' />";


}


function openModifyDialog() {
    // form 表单赋值 获取选中行
    var selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows == null || selectedRows.length != 1) {
        $.messager.alert("系统提示", "只能选择一条进行修改");
        return;
    }

    var row = selectedRows[0];
    if (row.saleClass == null){
        $("#a_fm").form('load', row); // form 赋值
        $("#agreementdlg").dialog('open').dialog('setTitle', "作废单据修改");
    }else{
        $("#fm").form('load', row); // form 赋值
        $("#dlg").dialog('open').dialog('setTitle', "修改营收日报");
    }

}


// 保存
function saveCustomer() {

    var url = "add";
    var id = $("#id").val();
    var payMode = $("#payMode").combobox('getValue');
    var source = $("#source").combobox('getValue');
    // var xybh = $("#xybh").numberbox('getValue');
    // var sjbh = $("#sjbh").numberbox('getValue');
    //
    // if(xybh.trim().length != 10){
    //     $.messager.alert("系统提示",'请填写完整的协议编号,为10位数字！');
    //     return;
    // }
    //
    // if(sjbh.trim().length != 6){
    //     $.messager.alert("系统提示",'请填写完整的收据编号,为6位数字！');
    //     return;
    // }


    if (payMode == 0){
        $.messager.alert("系统提示",'请选择支付方式');
        return;
    }
    if (source == 0){
        $.messager.alert("系统提示",'请选择用户来源');
        return;
    }

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

//关闭方法
$("#dlg").dialog({
    onClose: function () {
        resetValue();
    }
});


// 关闭
function closeCustomerDialog() {
    resetValue();
    $("#dlg").dialog('close');
}



// 保存
function saveAgreement() {

    var url = "add_agreement";

    var id = $("#a_id").val();
    if (id != null && $.trim(id).length > 0 && !isNaN(id)) { // 判断是否为数字
        url = "update_agreement";
    }

    $("#a_fm").form("submit",{
        url: url, // 相对路径
        onSubmit: function() {
            return $(this).form("validate");
        },
        success:function(result) {
            result = JSON.parse(result);
            if(result.resultCode == 1) {
                $.messager.alert("系统提示", "保存成功！");
                closeAgreementDialog();
                $("#dg").datagrid("reload");
            }else{
                $.messager.alert("系统提示",result.resultMessage);
                return;
            }
        }
    });
}


function closeAgreementDialog() {
    resetValue();
    $("#agreementdlg").dialog('close');
}