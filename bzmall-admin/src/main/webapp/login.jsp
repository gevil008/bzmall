<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录页面</title>
		<link rel="stylesheet" href="layui/css/layui.css">
		<link rel="stylesheet" href="css/main.css" media="all">
		<link rel="stylesheet" href="css/plugins/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
		<script src="layui/layui.js" type="text/javascript"></script>
		<script src="js/jquery-1.12.4.js" type="text/javascript"></script>
	</head>

	<body class="layui-layout-login">
	<div class="login-bg">
	    <div class="cover"></div>
	</div>
	<div class="login-content" >
	    <h1 class="login-box-title"><i class="fa fa-fw fa-user"></i>登录</h1>
	    <form class="layui-form" method="post">

	        <div class="layui-form-item">
	            <label class="layui-icon layui-icon-username" for="username"></label>
	            <input class="layui-input" type="text" name="username" id="username" placeholder="用户名" lay-verify="required">
	        </div>

	        <div class="layui-form-item">
	            <label class="layui-icon layui-icon-password" for="password"></label>
	            <input class="layui-input" type="password" name="password" id="password" placeholder="密码" lay-verify="required">
	        </div>

	        <div class="layui-form-item captcha-item">
	            <label class="layui-icon layui-icon-vercode"></label>
	            <input class="layui-input" type="text" name="code" autocomplete="off" placeholder="验证码" lay-verify="required">
	            <div onclick="addPic()" >

					<img id="addPic"  class="captcha-img" src="${pageContext.request.contextPath}/getCaptcha" />

				</div>
	        </div>
	        <button  lay-submit lay-filter="login" type="submit" class="layui-btn layui-btn-fluid ajax-login"><i class="fa fa-sign-in fa-lg fa-fw"></i> 登录</button>
	    </form>
	</div>
	</body>

	<script>
		function addPic() {
			// 修改图片的src属性  在url后拼接随机数
			$("#addPic").prop("src","${pageContext.request.contextPath}/getCaptcha?hh="+Math.random())
		}
        layui.use('form',function () {
            // 操作对象
            var form = layui.form;
            var $ = layui.$;

            form.on('submit(login)',function (data) {
                // console.log(data.field);
                $.ajax({
                    url:"/login/"+data.field.code,
                    data:JSON.stringify(data.field),
                    type:"POST",
                    contentType:"application/json",
                    dataType:"json",
                    success:function (result) {
                        if (result.status == 'success'){
							// console.log("登录成功");
							location.href = "main.jsp";
                        }else if(result.Verfystatus == 'Wrong'){
                            layer.msg('验证码错误');
                            addPic(); // 回调验证码函数
							// console.log("用户名或密码错误");
                        }else{
                        	layer.msg("用户名或密码错误");
						}
                    }
                });
                return false;
            });
        });

	</script>
</html>
