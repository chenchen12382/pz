
function formatDiscount(value){
       if(value != null ) {
           return parseInt(value) + '%';
       }else {
           return "无记录"
       }
}




function searchReportCount() {
    var start = $("#s_start").datebox('getValue');
    var over = $("#s_over").datebox('getValue');

    if(start>over && over!=""){

        alert("开始时间不能大于结束时间！")
    }

    var data = { "start":start,"over":over};
    $("#dg").datagrid('load', data);

}
