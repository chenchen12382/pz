<!DOCTYPE html>
<HTML>
<HEAD>
    <TITLE> ZTREE DEMO - checkbox</TITLE>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${ctx}/ztree/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx}/ztree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${ctx}/ztree/js/jquery.ztree.excheck.js"></script>
    <SCRIPT type="text/javascript">
        var setting = {
            check: {
                enable: true,
                chkboxType: { "Y" : "ps", "N" : "ps" }
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: onCheck
            }
        };

        function onCheck(e, treeId, treeNode) {
            console.log(treeNode.id + "--" + treeNode.checked)
            var data = {userId:${centerId?c}, centerId:treeNode.id,
            			checked:treeNode.checked};
            $.post('dorelate', data, function (resp) {
            	if (resp.resultCode != 1) {
            		alert(resp.resultMessage);
            	}
            });
        }
		
		var zNodes = [];
		<#if centers?has_content >
			<#list centers as center >
				zNodes[${center_index}] = {
					id:${center.id?c},
					pId:${center.parentId?default(0)},
					name:"${center.center}",
					open:true,
					checked:${center.checked?string('true', 'false')}
				};
			</#list>
		</#if>
        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
    </SCRIPT>
</HEAD>

<BODY>
<div class="content_wrap">
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>

</div>
</BODY>
</HTML>