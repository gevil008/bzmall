<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>管理员列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/metroStyle/metroStyle.css" type="text/css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.core.js"></script>

</head>
<body>
<table id="role" lay-filter="test"></table>
<!--展示所有-->
<script type="text/javascript">
    layui.use("table",function(){
        var table = layui.table;
        // 主页展示所有数据
        table.render({
            elem:"#role",
            cols:[[
                {title:"编号",field:"roleId",sort:true},
                {title:"角色名称",field:"roleName"},
                {title:"操作",toolbar:"#operation"}//操作列
            ]],
            url:"/role/roles",
            page:true,          //开启分页
            limits:[5,10,20],   //每页条数的选择项
            limit:5,            //每页显示的条数，默认：10
            toolbar:"#toolbar",  //2 显示删除按钮
        });
    });
</script>
<!--删除选中/添加  按钮-->
<script type="text/html" id="toolbar">
    <button onclick="handleRemoveAny()" class="layui-btn layui-btn-primary">删除选中<i class="layui-icon layui-icon-delete"/> </button>
    <sec:authorize access="@ss.hasRole('admin')">
        <button onclick="openAddForm()" class="layui-btn layui-btn-danger">添加<i class="layui-icon layui-icon-addition"></i></button>
    </sec:authorize>
    <!-- 1 显示搜索表单 -->
    <div class="layui-inline">
        <form action="" class="layui-form">
            <div class="layui-input-inline">
                <input type="text" name="search" id="search" class="layui-input">
            </div>
            <div class="layui-input-inline">
                <button onclick="handleSearch()" type="button" class="layui-btn layui-btn-primary"><i class="layui-icon layui-icon-search"> </i></button>
            </div>
        </form>
    </div>
</script>

<!--删除选中实现-->
<script type="text/javascript">
    function handleRemoveAny() {
        layui.use("table",function () {

            var table = layui.table;
            var $ = layui.jquery;
            //根据表格id值获取所有选中的行
            var status = table.checkStatus("role");
            // console.log(status);

            status.data;//获取到所有选中行的数据
            //获取所有选中行的id
            var ids = [];
            var files = [];
            for(var i = 0; i < status.data.length; i++){
                ids[i] = status.data[i].id;
                files [i] = status.data[i].cover;
            }
            console.log(files);
            // console.log(ids);

            if(ids.length > 0){
                //发起ajax删除请求
                $.ajax({
                    url:"/role/roles",
                    type:"DELETE",
                    contentType:"application/json",
                    data:JSON.stringify(ids),
                    dataType:"json",
                    success:function(result){
                        if(result.status == "success") {
                            table.reload("role");
                        }else{
                            alert("服务器繁忙：删除失败！");
                        }
                    }
                });
            }
        });
    }
</script>


<!--删除/修改  按钮-->
<script type="text/html" id="operation">
    <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</button>
    <button class='layui-btn layui-btn-primary layui-btn-xs' lay-event="edit">更新</button>
</script>


<!--添加页面-->
<script id="addForm" type="text/html">
    <form class="layui-form" action="" lay-filter="addForm">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名</label>
            <div class="layui-input-block" style="width: 400px;">
                <input type="text" name="roleName" required  lay-verify="required" placeholder="请输入角色名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div carousel-item="layui-form-item">
            <label class="layui-form-label">授权</label>
            <div class="layui-input-block">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
        <div class="layui-form-item" >
            <div class="layui-input-block"  align="center" style="width: 400px">
                <button type="submit" class="layui-btn" lay-submit lay-filter="go">添加</button>
                <button type="button" class="layui-btn" id="add-form-cancel">取消</button>
            </div>
        </div>

    </form>
</script>

<script type="text/javascript">
    //执行弹出弹出层的代码
    function openAddForm(){
        layui.use(["layer","form","laydate","upload","table"],function(){

            var layer = layui.layer;
            var $ = layui.jquery;
            var form = layui.form;
            var table = layui.table;

            layer.open({
                type:1,
                content:$("#addForm").html(),
                title:"添加",
                area:['600px','400px'],
                success:function(layerobj,index){
                    //在弹出层弹出成功后调用
                    // 渲染表单中的所有标签
                    form.render();
                    // 添加请求
                    var setting = {	};
                    // 获取什么样的数据
                    // 嵌套封装 菜单名字 name 下一级children 主键id

                    $(document).ready(function(){
                        $.ajax({
                            url:"${pageContext.request.contextPath}/menu/getZTreeNodes",
                            success: function (data) {
                                $.fn.zTree.init($("#treeDemo"), setting, data);
                            }
                        });
                    });

                    form.on("submit(go)",function(data){
                        console.log(data.field);
                        console.log(data.field.roleId)
                        if ($("#password1").val() != $("#password2").val()){
                            alert("两次密码不一致");
                            return false;
                        }
                        $.ajax({
                            url:"/role/roles/",
                            data:JSON.stringify(data.field),
                            type:"POST",
                            contentType:"application/json",
                            dataType:"json",
                            success:function(result){
                                if (result.status == "success") {
                                    layer.close(index);
                                    table.reload("role");
                                }else{
                                    alert("服务器繁忙：添加失败");
                                }
                            }
                        });

                        return false;
                    });

                    // 取消添加按钮
                    $("#add-form-cancel").click(function () {
                        layer.close(index);
                    });

                }

            });

        });
    }
</script>
<!--添加实现代码-->
<!--修改页面-->
<script id="updateForm" type="text/html">
    <form class="layui-form" action="" lay-filter="updateForm">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名</label>
            <div class="layui-input-block" >
                <input type="hidden" name="roleId">
                <input type="text" name="roleName" required  lay-verify="required" placeholder="请输入角色名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit lay-filter="update-go">添加</button>
                <button type="button" class="layui-btn" id="update-form-cancel">取消</button>
            </div>
        </div>
    </form>
</script>

<!--修改/删除实现代码-->
<script type="text/javascript">
    layui.use(["table","layer","form","laydate","upload"],function(){

        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var $ = layui.jquery;

        //监听lay-filter为test的表格的工具按钮被单击的事件
        table.on("tool(test)",function(obj){
            // console.log(obj);
            // 修改操作
            if(obj.event =="edit"){
                layer.open({
                    type:1,
                    content:$("#updateForm").html(),
                    title:"修改",
                    area:"350px",
                    success:function(layerObj,index){

                        //回显表单数据
                        console.log(obj.data);//要更新的那行的原始数据
                        form.val("updateForm",obj.data);

                        //监听更新表单的提交事件
                        form.on("submit(update-go)",function(data){
                            //当更新表单,提交数据的时候,执行当前行数 data.field获取到更新表单最新的数据
                            console.log(data.field);
                            $.ajax({
                                url:"/role/roles",
                                type:"put",
                                data:JSON.stringify(data.field),
                                contentType:"application/json",
                                dataType:"json",
                                success:function(result){
                                    //如果更新成功,关闭弹出层,刷新表格
                                    if (result.status == "success") {
                                        layer.close(index);
                                        table.reload("role");
                                    }else{
                                        alert("服务器繁忙：更新失败");
                                    }
                                }
                            });
                            return false;//禁用表单的默认提交
                        });

                        // 取消添加按钮
                        $("#update-form-cancel").click(function () {
                            layer.close(index);
                        });

                    }
                });

            }
            // 删除操作
            else if(obj.event == "delete"){
                layer.open({
                    type:1,
                    title:"确定删除",
                    btn:["确定","取消"],
                    yes:function (index,layerObj) {
                        // console.log("确定按钮");
                        // 从数据库中删除信息
                        $.ajax({
                            url:"/role/roles/"+obj.data.id,
                            dataType:"json",
                            type:"DELETE",
                            success:function (result) {
                                // console.log(result);
                                layer.close(index);
                                table.reload("role");
                            }
                        });
                    },
                    btn2:function (index,layerObj) {
                        // console.log("取消按钮");
                        layer.close(index);
                    }
                });
            }
        });
    });
</script>
<script type="text/javascript">
    function handleSearch(){
        layui.use("table",function(){
            var table = layui.table;
            var $ = layui.jquery;
            var search = $("#search").val();
            console.log(search);
            //2 根据条件重新加载表格
            table.reload("role",{
                url:"/role/roles",
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