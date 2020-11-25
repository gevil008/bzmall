<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>分类维护</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/metroStyle/metroStyle.css" type="text/css">

</head>
<body>
<%--	ztree--%>
<ul id="treeDemo" class="ztree"></ul>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.core.js"></script>
<script>
    var setting = {	};

    // 获取什么样的数据
    // 嵌套封装 菜单名字 name 下一级children 主键id

    $(document).ready(function(){
        $.ajax({
            url:"${pageContext.request.contextPath}/cate/getZTreeNodes",
            success: function (data) {
                $.fn.zTree.init($("#treeDemo"), setting, data);
            }
        });
    });

</script>
</body>
</html>