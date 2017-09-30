
function formatDiscount(value){
       if(value != null ) {
           return parseInt(value) + '%';
       }else {
           return "无记录"
       }
}

function formartOrderTotal(value) {
    if(value == 1){
        return 0 ;
    }else {
        return value;
    }
    
}


function searchCustomer() {
    var start = $("#start").datebox('getValue');
    var over = $("#over").datebox('getValue');

    if(start>over && over!=""){

        alert("开始时间不能大于结束时间！")
    }

    var data = { "start":start,"over":over};
    $("#dg").datagrid('load', data);

}
