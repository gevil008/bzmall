<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>系统日志</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
    <table id="log" lay-filter="test"></table>
    <!--展示所有-->
    <script type="text/javascript">
        layui.use("table",function(){
            var table = layui.table;
            // 主页展示所有数据
            table.render({
                elem:"#log",
                cols:[[
                    {title:"ID",field:"logId",sort:true},
                    {title:"用户名",field:"username"},
                    {title:"用户操作",field:"logType"},
                    {title:"请求方法",field:"logMethod"},
                    {title:"执行时长(毫秒)",field:"logTime"},
                    {title:"IP地址",field:"logIp"},
                    {title:"创建时间",field:"logDate"},
                ]],
                url:"/log/logs",
                page:true,          //开启分页
                limits:[5,10,20],   //每页条数的选择项
                limit:10,            //每页显示的条数，默认：10
                toolbar:"#toolbar",  //2 显示删除按钮
            });
        });
    </script>

    <!--删除选中/添加  按钮-->
    <script type="text/html" id="toolbar">
        <!-- 1 显示搜索表单 -->
        <div class="layui-inline">
            <form action="" class="layui-form">
                <div class="layui-input-inline">
                    <input type="text" name="search" id="search" class="layui-input" placeholder="用户名/用户操作">
                </div>
                <div class="layui-input-inline">
                    <button onclick="handleSearch()" type="button" class="layui-btn layui-btn-primary"><i class="layui-icon layui-icon-search">查询</i></button>
                </div>
            </form>
        </div>
    </script>
    <script type="text/javascript">
        function handleSearch(){
            layui.use("table",function(){
                var table = layui.table;
                var $ = layui.jquery;
                var search = $("#search").val();
                console.log(search);
                //2 根据条件重新加载表格
                table.reload("log",{
                    url:"/log/logs",
                    where:{
                        "search":search,
                        "page":1
                    }
                })
            })
        }
    </script>

</body>
</html>