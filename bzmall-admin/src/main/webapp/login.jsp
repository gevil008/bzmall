<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录页面</title>
		<link rel="stylesheet" href="layui/css/layui.css">
		 <link rel="stylesheet" href="css/main.css" media="all">
		 <link rel="stylesheet" href="css/plugins/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
	</head>
	
	<body class="layui-layout-login">
	<div class="login-bg">
	    <div class="cover"></div>
	</div>
	<div class="login-content" >
	    <h1 class="login-box-title"><i class="fa fa-fw fa-user"></i>登录</h1>
	    <form class="layui-form" action="${pageContext.request.contextPath}/login" method="post">
			
	        <div class="layui-form-item">
	            <label class="layui-icon layui-icon-username" for="username"></label>
	            <input class="layui-input" type="text" name="username" id="username" placeholder="用户名">
	        </div>
			
	        <div class="layui-form-item">
	            <label class="layui-icon layui-icon-password" for="password"></label>
	            <input class="layui-input" type="password" name="password" id="password" placeholder="密码">
	        </div>
			
	        <div class="layui-form-item captcha-item">
	            <label class="layui-icon layui-icon-vercode"></label>
	            <input class="layui-input" type="text" name="code" autocomplete="off" placeholder="验证码">
	            <div onclick="addPic()" >

					<img id="addPic"  class="captcha-img" src="${pageContext.request.contextPath}/admin/getCaptcha" />

				</div>
	        </div>
	        <button type="submit" class="layui-btn layui-btn-fluid ajax-login"><i class="fa fa-sign-in fa-lg fa-fw"></i> 登录</button>
	    </form>
	</div>
	</body>
	<script src="layui/layui.js" type="text/javascript"></script>
	<script src="js/jquery-1.12.4.js" type="text/javascript"></script>
	<script>
		function addPic() {
			// 修改图片的src属性  在url后拼接随机数
			$("#addPic").prop("src","${pageContext.request.contextPath}/admin/getCaptcha?hh="+Math.random())
		}

	</script>
</html>
