<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>分类展示</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
	</head>
	<body class="layui-layout-body">

		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			<legend>基本演示</legend>
		</fieldset>
		<div class="layui-btn-container">
			<button type="button" class="layui-btn layui-btn-sm" lay-demo="getChecked">获取选中节点数据</button>
			<button type="button" class="layui-btn layui-btn-sm" lay-demo="setChecked">勾选指定节点</button>
			<button type="button" class="layui-btn layui-btn-sm" lay-demo="reload">重载实例</button>
		</div>

		<div id="test12" class="demo-tree-more"></div>


		<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
		<script>
            $(function () {
				$.ajax({
					url:"${pageContext.request.contextPath}/category/getLayuiTreeNodes",
					success: function (data) {
                        layui.use(['tree', 'util'], function() {
                            var tree = layui.tree
                                , layer = layui.layer
                                , util = layui.util
                            //基本演示
                            tree.render({
                                elem: '#test12',
                                data: data
                                ,showCheckbox: true  //是否显示复选框
                                ,id: 'demoId1'
                                ,isJump: true //是否允许点击节点时弹出新窗口跳转
                                ,click: function(obj){
                                    var data = obj.data;  //获取当前点击的节点数据
                                    layer.msg('状态：'+ obj.state + '<br>节点数据：' + JSON.stringify(data));
                                }
                            });

                            //按钮事件
                            util.event('lay-demo', {
                                getChecked: function(othis){
                                    var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据

                                    layer.alert(JSON.stringify(checkedData), {shade:0});
                                    console.log(checkedData);
                                }
                                ,setChecked: function(){
                                    tree.setChecked('demoId1', [12, 16]); //勾选指定节点
                                }
                                ,reload: function(){
                                    //重载实例
                                    tree.reload('demoId1', {

                                    });

                                }
                            });
                        })
                    }
				})
            })
		</script>
	</body>
</html>