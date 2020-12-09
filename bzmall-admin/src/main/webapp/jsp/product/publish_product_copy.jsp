<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>商品发布</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/metroStyle/metroStyle.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cascader/cascader.css">
	</head>
	<body>
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">标题</div>
					<div class="layui-card-body">


						<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
							<legend>基本属性</legend>
						</fieldset>

						<form class="layui-form" > <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
							<p>
							<div class="layui-form-item">
								<label class="layui-form-label">商品名称</label>
								<div class="layui-input-block">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
							</p>


							<p>
							<div class="layui-form-item">
								<label class="layui-form-label">商品描述</label>
								<div class="layui-input-block">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
							</p>

							<p>
							<div class="layui-form-item">
								<label class="layui-form-label">选择分类</label>
								<div class="layui-input-block">
									<input type="text" id="a" name="categoryName" class="layui-input" readonly="readonly">
								</div>
							</div>
							</p>

							<p>
							<div class="layui-form-item">
								<label class="layui-form-label">选择品牌</label>
								<div class="layui-input-block">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
							</p>

							<p>
							<div class="layui-form-item">
								<label class="layui-form-label">商品重量</label>
								<div class="layui-input-block">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
							</p>

							<%--批量上传图片--%>
							<p>
							<div class="layui-form-item">
								<label class="layui-form-label">商品介绍</label>
								<div class="layui-input-block">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
							</p>
							<%--批量上传图片--%>
							<p>
							<div class="layui-form-item">
								<label class="layui-form-label">商品图集</label>
								<div class="layui-input-block">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
							</p>

							<%--			基本参数需要根据类型进行动态加载--%>
							<p>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button type="button" class="layui-btn layui-btn-primary">点击开始—设置基本参数</button>
								</div>
							</div>
							</p>

							<div style="">

								<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
									<legend>基本参数</legend>
								</fieldset>

								<p>
									<label class="layui-form-label">主体</label>
								<div class="layui-form-item">
									<div class="layui-inline">
										<label class="layui-form-label">入网型号</label>
										<div class="layui-input-block">
											<input type="text" name="入网型号" class="layui-input">
										</div>
									</div>
									<div class="layui-inline">
										<label class="layui-form-label">屏幕</label>
										<div class="layui-input-inline">
											<input type="text" name="屏幕" class="layui-input">
										</div>
									</div>
								</div>
								</p>

							</div>

							<%--			销售属性需要根据类型进行动态加载--%>
							<p>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button type="button" class="layui-btn layui-btn-primary">点击开始—设置销售属性</button>
								</div>
							</div>
							</p>

							<div>
								<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
									<legend>销售属性</legend>
								</fieldset>
								<div class="layui-form-item">
									<label class="layui-form-label">复选框</label>
									<div class="layui-input-block">
										<input type="checkbox" name="like[write]" title="写作">
										<input type="checkbox" name="like[read]" title="阅读">
										<input type="checkbox" name="like[game]" title="游戏">
									</div>
								</div>
							</div>

							<%--			销售属性需要根据类型进行动态加载--%>
							<p>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button type="button" class="layui-btn layui-btn-primary">点击开始—设置SKU信息</button>
								</div>
							</div>
							</p>

							<p>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button class="layui-btn" lay-submit lay-filter="formDemo">发布商品信息</button>
									<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								</div>
							</div>
							</p>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>




		<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
		<script src="${pageContext.request.contextPath}/ztree/js/jquery-1.4.4.min.js"></script>
		<script src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.all.js"></script>
		<script>
			layui.use('form', function(){
				var form = layui.form;

				//监听提交
				form.on('submit(formDemo)', function(data){
					layer.msg(JSON.stringify(data.field));
					return false;
				});
			});

			// 所属分类
			$.ajax({
				url:"${pageContext.request.contextPath}/category/getCascaderNodeVos",
				success:function (data) {
					layui.config({
						base: "${pageContext.request.contextPath}/layui/lay/mymodules/"
					}).use(['form',"jquery","cascader","form"], function(){
						var $ = layui.jquery;
						var cascader = layui.cascader;
						var cas=cascader({
							elem: "#a",
							data:data,
							showLastLevels: true,
							success: function (valData,labelData) {
								// 获取最后一个节点下标
								console.log(valData[valData.length-1]);
								console.info(labelData)
							}
						});
					});
				}
			})
		</script>
	</body>
</html>