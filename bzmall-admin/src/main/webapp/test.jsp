<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>layout 后台大布局 - Layui</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cascader/cascader.css">

</head>
<body class="layui-layout-body">

	<div class="layui-form-item">
		<label class="layui-form-label">选择框</label>
		<div class="layui-input-block">
			<input type="text" id="a" class="layui-input" readonly="readonly">
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
	<script>

		$(function () {
			$.ajax({
				url:"${pageContext.request.contextPath}/category/getCascaderNodeVos",
				success: function (data) {
					layui.config({
						base: "${pageContext.request.contextPath}/layui/lay/mymodules/"
					}).use(['form',"jquery","cascader","form"], function(){
						var $ = layui.jquery;
						var cascader = layui.cascader;

						var cas=cascader({
							elem: "#a",
							data: data,
							showLastLevels: true,
							success: function (valData,labelData) {
								console.log(valData);
								console.log(labelData);
							}
						});

						// cas.reload({})  // 重载

					});

				}
			})
		})



	</script>
</body>
</html>