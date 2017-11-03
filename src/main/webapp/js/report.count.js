
function formatDiscount(value){
       if(value != null ) {

           var val=value.toString();
           var result=val.substr(0,val.length-1)+'.'+val.substr(val.length-1);

           return result + '%';
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
