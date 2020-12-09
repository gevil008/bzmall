<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>商品发布</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/metroStyle/metroStyle.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cascader/cascader.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/step-lay/step.css">

	</head>
	<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-body" style="padding-top: 40px;height: 800px">
				<div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;height: 800px">
					<div carousel-item>
<%--						基本属性spu的表单--%>
						<div>
							<form class="layui-form" style="margin: 0 auto;max-width: 460px;padding-top: 40px;height: 800px">
								<div class="layui-form-item">
									<label class="layui-form-label">商品名称</label>
									<div class="layui-input-block">
										<input type="text" name="spuName" class="layui-input">
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">商品描述</label>
									<div class="layui-input-block">
										<input type="text" name="spuDescription" class="layui-input">
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">选择分类</label>
									<div class="layui-input-block">
										<input type="text" id="a" name="categoryName" class="layui-input" readonly="readonly">
									</div>
								</div>
<%--								根据分类 展示分类下的所有品牌--%>
								<div class="layui-form-item">
									<label class="layui-form-label">选择品牌</label>
									<div class="layui-input-block">
										<select name="brandId" id="brandId" lay-filter="brandId">
											<option value=""></option>
										</select>
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">商品重量</label>
									<div class="layui-input-block">
										<input type="text" name="" class="layui-input">
									</div>
								</div>

								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="formStep">
											&emsp;下一步&emsp;
										</button>
									</div>
								</div>
							</form>
						</div>
	<%--						商品图片--%>
					<div>
						<div style="margin-top: 90px;padding-left: 30%;padding-right: 30%">
							<div class="layui-upload">
								<button type="button" class="layui-btn" id="test2">商品介绍</button>
								<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
									预览图：
									<div class="layui-upload-list" id="demo2"></div>
								</blockquote>
							</div>

							<div class="layui-upload">
								<button type="button" class="layui-btn" id="test3">商品图集</button>
								<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
									预览图：
									<div class="layui-upload-list" id="demo3"></div>
								</blockquote>
							</div>
						</div>
						<div style="text-align: center;margin-top: 50px;">
							<button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
							<button class="layui-btn" lay-submit lay-filter="formStep2">
								&emsp;下一步
							</button>
						</div>
					</div>

<%--						基本参数
						根据分类查询到当前分类下的所有 属性分组 和 分组的属性
						生成html代码
		--%>
						<div>
							<form class="layui-form layui-form-pane" style="margin: 0 auto;max-width: 460px;padding-top: 40px;">
<%--								动态生成表单内容--%>
								<div id="baseAttrs">


								</div>

								<div class="layui-form-item">
									<div class="layui-input-block">
										<button type="button" class="layui-btn layui-btn-primary">点击选择销售属性</button>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
										<button class="layui-btn" lay-submit lay-filter="formStep3">
											&emsp;下一步
										</button>
									</div>
								</div>
							</form>
						</div>
<%--					销售属性 sku生成 表格的形式--%>
						<div>
							<form class="layui-form" id="saleAttrs">


							</form>
						</div>
						<div>
							<div style="text-align: center;margin-top: 90px;">
								<i class="layui-icon layui-circle"
								   style="color: white;font-size:30px;font-weight:bold;background: #52C41A;padding: 20px;line-height: 80px;">&#xe605;</i>
								<div style="font-size: 24px;color: #333;font-weight: 500;margin-top: 30px;">
									添加成功
								</div>
							</div>
							<div style="text-align: center;margin-top: 50px;">
								<button class="layui-btn next">添加一个新商品</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
	<script src="${pageContext.request.contextPath}/ztree/js/jquery-1.4.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.all.js"></script>
	<script src="${pageContext.request.contextPath}/step-lay/step.js"></script>

	<script>
<%--
	对象转json json转对象
	所有的数据都应该通过一个js对象保存
	图片数据通过数组属性保存

	最后保存的时候 发送整个对象（转json）给后台
--%>
		var fieldData = {};
		var categoryId = 0;
		layui.config({
			base:'${pageContext.request.contextPath}/step-lay/'
		}).use([ 'form', 'step'], function () {
			var $ = layui.$
					, form = layui.form
					, step = layui.step;

			step.render({
				elem: '#stepForm',
				filter: 'stepForm',
				width: '100%', //设置容器宽度
				stepWidth: '750px',
				height: '500px',
				stepItems: [{
					title: '基本属性'
				},{
					title: '商品图片'
				}, {
					title: '基本参数'
				}, {
					title: 'SKU生成'
				}, {
					title: '完成'
				}]
			});

			form.on('submit(formStep)', function (data) {
				fieldData = {};
				step.next('#stepForm');
				fieldData = data.field;
				console.info(JSON.stringify(fieldData))
				return false;
			});

			form.on('submit(formStep2)', function (data) {
				step.next('#stepForm');
				console.info("----开始查询基本属性----")
				// 查询所有的属性分组和属性信息 生成表单
				$.ajax({
					url:"${pageContext.request.contextPath}/attr-group/getGroupVosByCategoryId?categoryId="+categoryId,
					success:function (data) {
						var content = "";
						$.each(data.data,function (index1,item1) {
							if(item1.attrs != null && item1.attrs.length >0){
								content += "<label class=\"layui-form-label\">";
								content += item1.attrGroupName+"</label><div class=\"layui-form-item\">";
								$.each(item1.attrs,function (index2, item2) {
									content += "<div class=\"layui-inline\"><label class=\"layui-form-label\">";
									content += item2.attrName+"</label><div class=\"layui-input-block\">";
									content += "<input type=\"text\" name=\"attrValues_"+item2.attrId+"\" class=\"layui-input\"></div></div>";
								})
								content += "</div>";
							}
						})
						$("#baseAttrs").html(content)

					}
				})
				return false;
			});

			form.on('submit(formStep3)', function (data) {
				step.next('#stepForm');
				// 获取基本属性的信息
				fieldData.baseAttrs = data.field;
				console.info(JSON.stringify(fieldData))
				// 根据分类id 查询所有销售属性的 选择销售属性 生成复选框
				// 销售属性 应该采用弹出框 在基本属性页面添加弹出框  下一步直接跳转sku生成页面（表格形式）
				<%--$.ajax({--%>
				<%--	url:"${pageContext.request.contextPath}/attr/getSaleAttrByCateId?categoryId="+categoryId,--%>
				<%--	success:function (data) {--%>
				<%--		// 遍历生成数据--%>
				<%--		var content = "";--%>
				<%--		$.each(data.data,function (index1, item1) {--%>
				<%--			content += "<div class=\"layui-form-item\"><label class=\"layui-form-label\">";--%>
				<%--			content += item1.attrName+"</label><div class=\"layui-input-block\">";--%>
				<%--			$.each(item1.valueSelects,function (index2, item2) {--%>
				<%--				content +=--%>
				<%--						"<input type=\"checkbox\"  lay-skin=\"primary\" name=\""+item1.attrId+"["+item2+"]\" title=\""+item2+"\">";--%>
				<%--			})--%>
				<%--			content += "</div></div>";--%>
				<%--		})--%>

				<%--		$("#saleAttrs").html(content)--%>

				<%--		form.render();--%>
				<%--	}--%>
				<%--});--%>
				return false;
			});
			form.on('submit(formStep4)', function (data) {
				step.next('#stepForm');
				return false;
			});
			$('.pre').click(function () {
				step.pre('#stepForm');
			});

			$('.next').click(function () {
				step.next('#stepForm');
			});
		})




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
							// // 获取最后一个节点下标
							categoryId = valData[valData.length-1];

							// 选择分类后 查询分类下的品牌数据
							// 更新所属分组的数据
							$.ajax({
								url:"${pageContext.request.contextPath}/brand/getBrandByCateId?categoryId="+categoryId,
								success:function (data) {
									var content = "<option value=\"\"></option>";
									$.each(data,function (index, group) {
										content += "<option value=\""+group.value+"\">";
										content += group.label;
										content += "</option>";
									});
									$("#brandId").html(content);

									layui.use('form', function(){
										var form = layui.form;
										form.render('select');
									});
								}
							})
						}
					});
				});
			}
		})
	</script>

	</body>
</html>