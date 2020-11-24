<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>



    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" id="menu_nav"  lay-filter="menu_nav">

            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-tab layui-tab-brief layui-tab-card" lay-allowClose="true"
                 lay-filter="myTab">
                <ul class="layui-tab-title">
                    <li class="layui-this">欢迎页</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">欢迎使用..xxxx</div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script>

    // 打开选项卡
    function addTabs(name,url,menuId){
        layui.use('element', function(){
            var element = layui.element;
            //title 二级菜单的名字
            //content 点击选项卡加载一个jsp页面 通过iframe标签加载页面
            //id 二级菜单的id
            element.tabAdd('myTab', {
                title: name,
                content: '<iframe width="100%" height="100%" src="${pageContext.request.contextPath}'+url+'"></iframe>',
                id: menuId
            });

            // 选中刚打开的页面
            element.tabChange('myTab', menuId);
        });

    }

    // 1.查询后台菜单数据
    $(function () {
        $.ajax({
            url:"${pageContext.request.contextPath}/menu/getMenuList",
            success:function (data) {
                // 2 动态生成菜单展示的html代码
                //     2.1先生成一级菜单的html
                var context = "";
                //参数1 被遍历的数据 参数2 匿名函数 该匿名函数位置1 是下标 位置2是被遍历的数据
                $.each(data,function (index1, menu1) {
                    // 拼接一级菜单前标签
                    context += "<li class=\"layui-nav-item\"><a class=\"\" href=\"javascript:;\">";
                    // 拼接一级菜单名字
                    context += menu1.name+"</a>";
                    //在a标签后拼接二级菜单的数据
                    $.each(menu1.menuList,function (index2,menu2) {
                        // 拼接二级菜单前标签
                        context += "<dl class=\"layui-nav-child\"><dd><a href=\"javascript:;\" " +
                            "onclick='addTabs(\"" + menu2.name+"\",\""+menu2.url+"\","+menu2.menuId+ ")'>";
                        // 拼接二级菜单名字
                        context += menu2.name;
                        // 拼接二级菜单后标签
                        context += "</a></dd></dl>";
                    });
                    // 拼接一级菜单后标签
                    context += "</li>";
                });

                // 3.将拼接好的html写到页面中
                $("#menu_nav").html(context)

                // 4 在菜单html加载完的时候 强制layui重新渲染页面
                layui.use('element', function(){
                    var element = layui.element;

                    element.render('nav', 'menu_nav');
                });
            }
        })
    })

    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>