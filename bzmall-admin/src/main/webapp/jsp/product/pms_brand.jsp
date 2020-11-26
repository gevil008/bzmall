<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>品牌管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<table id="brand" lay-filter="test"></table>

<!--展示所有-->
<script type="text/javascript">
    layui.use("table",function(){
        var table = layui.table;
        // 主页展示所有数据
        table.render({
            elem:"#brand",
            cols:[[
                {type:"checkbox"},//1 显示复选框
                {title:"品牌ID",field:"brandId",sort:true},
                {title:"名称",field:"name"},
                {title:"首字母",field:"firstLetter"},
                {title:"排名",field:"sort"},
                {title:"品牌logo",field:"logo",templet:function (d) {
                        return "<img src='${pageContext.request.contextPath}/img/"+d.logo+"' width='70px'/>";
                    }},
                {title:"品牌描述",field:"brandStory"},
                {title:"操作",toolbar:"#operation"}//操作列
            ]],
            url:"/brand/brands",
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
    <button onclick="openAddForm()" class="layui-btn layui-btn-danger">添加<i class="layui-icon layui-icon-addition"></i></button>
    <button onclick="openBatchAddForm()" class="layui-btn layui-btn-danger">批量添加<i class="layui-icon layui-icon-addition"></i></button>
    <button onclick="batchDownload()" class="layui-btn layui-btn-danger">批量下载<i class="layui-icon layui-icon-addition"></i></button>
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
            var status = table.checkStatus("book");
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
                    url:"/brand/brands",
                    type:"DELETE",
                    contentType:"application/json",
                    data:JSON.stringify(ids),
                    dataType:"json",
                    success:function(result){
                        if(result.status == "success") {
                            table.reload("book");
                        }else{
                            alert("服务器繁忙：删除失败！");
                        }
                    }
                });
            }
        });
    }
</script>

<!--添加页面-->
<script id="addForm" type="text/html">
    <form class="layui-form" action="" lay-filter="addForm" style="z-index: 100">
        <div class="layui-form-item">
            <label class="layui-form-label">品牌名称</label>
            <div class="layui-input-block" style="width:200px">
                <input type="text" name="name" required  lay-verify="required" placeholder="品牌名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">品牌排名</label>
            <div class="layui-input-block" style="width:200px">
                <input type="text" name="sort" required  lay-verify="required" placeholder="请输入名次" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">品牌logo</label>
            <input type="hidden" name="logo" id="cover" lay-verify="file">
            <div class="layui-input-block" style="width:200px">
                <button type="button" class="layui-btn uploadfile">
                    <i class="layui-icon layui-icon-upload"></i>上传图片
                </button>
            </div>
        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block" style="width:200px">
                    <textarea name="brandStory" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="left: 130px;width: 200px">
                <button type="submit" class="layui-btn" lay-submit lay-filter="go">添加</button>
                <button type="button" class="layui-btn" id="add-form-cancel">取消</button>
            </div>
        </div>
        <div id="coverimgdiv">
            <img src="" id="coverimg">
            <div id="coverimg2" class="uploadfile">上传图片</div>
        </div>
    </form>
</script>

<script id="BatchAddForm" type="text/html">
    <form action="" class="layui-form">
        <div class="layui-form-item" align="center">
            <button type="button" class="layui-btn" id="test1">
                <i class="layui-icon layui-icon-upload"></i>批量上传
            </button>
            <span id="xlsxFile"></span>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" lay-submit lay-filter="go" class="layui-btn" id="batchAdd-submit-btn">添加</button>
                <button type="button" class="layui-btn" id="batchAdd-form-cancel">取消</button>
            </div>
        </div>
    </form>
</script>

<script type="text/javascript">
    function openBatchAddForm() {
        layui.use(["layer","form","upload","table"],function () {
            var layer = layui.layer;
            var $ = layui.jquery;
            var form = layui.form;
            var upload = layui.upload;
            var table = layui.table;

            layer.open({
                type:1,
                content:$("#BatchAddForm").html(),
                area:"300px",
                title:"批量添加",
                success:function (layerobj,index) {
                    upload.render({

                        elem:'#test1',
                        url:'/file/batchUpload',
                        field:'xlsxfile',
                        accept: "file",
                        exts:"xlsx",
                        auto: false,
                        bindAction:"#batchAdd-submit-btn",
                        done:function (res) {
                            alert("上传成功");
                            console.log(res);
                            layer.close(index);
                            table.reload("brand");
                        },
                        error:function () {
                            alert("上传文件类型不支持")
                        }
                    })
                    // 取消添加按钮
                    $("#batchAdd-form-cancel").click(function () {
                        layer.close(index);
                    });
                }

            })
        })
    }
</script>

<!--添加实现代码-->
<script type="text/javascript">
    //执行弹出弹出层的代码
    function openAddForm(){
        layui.use(["layer","form","laydate","upload","table"],function(){

            var layer = layui.layer;
            var $ = layui.jquery;
            var laydate = layui.laydate;
            var form = layui.form;
            var upload = layui.upload;
            var table = layui.table;

            layer.open({
                type:1,
                content:$("#addForm").html(),
                title:"添加",
                area:"600px",
                success:function(layerobj,index){
                    //在弹出层弹出成功后调用

                    // 上传图片的展示设置
                    $("#coverimgdiv").css({"width":"250px","height":"240px","position":"absolute","z-index":"103","right":"20px","top":"20px","border":"solid 1px red"});
                    $("#coverimg2").css({"margin-top":"40%","margin-left":"35%","position":"absolute","z-index":"101","font-size":"20px","color":"red"});

                    /*// 手动渲染单选按钮
                    // form.render("radio","addForm");

                    // 手动渲染日期组件
                    laydate.render({
                        elem:"#publishedDate",
                        type:"date",//控件选择类型 year month date time datetime
                        format:"yyyy-MM-dd",//日期格式
                        value:new Date(),//初始值
                        trigger: 'click'//触发方式
                    });*/

                    // 文件手动校验
                    form.verify({
                        file:function(value){
                            if(value.trim().length <= 0){
                                return "未选择文件";
                            }
                        }
                    });

                    // 上传文件
                    upload.render({
                        elem: '.uploadfile', //绑定元素
                        url: '/file/upload', //上传接口
                        field: "pic",//设置上传的参数名
                        done: function(res){
                            //上传完毕回调
                            $("#cover").val(res.path);
                            $("#coverimg").prop("src","${pageContext.request.contextPath}/img/"+res.path);
                            $("#coverimg").css({"width":"250px","height":"240px","position":"absolute","z-index":"102"})
                        },
                        // 上传文件异常
                        error: function(){
                            //请求异常回调
                            alert("上传文件类型不支持")
                        }
                    });

                    // 添加请求
                    form.on("submit(go)",function(data){
                        $.ajax({
                            url:"/brand/brands",
                            data:JSON.stringify(data.field),
                            type:"post",
                            contentType:"application/json",
                            dataType:"json",
                            success:function(result){
                                if (result.status == "success") {
                                    layer.close(index);
                                    table.reload("brand");
                                }else{
                                    alert("服务器繁忙：添加失败");
                                }
                            }
                        });

                        return false;
                    });

                    // 取消添加按钮
                    $("#add-form-cancel").click(function () {
                        var file = $("#cover").val();
                        if (file.length > 0){
                            $.ajax({
                                url:"/file/remove",
                                data:"file="+file,
                                type:"post",
                                dataType:"json",
                            });
                        }
                        layer.close(index);
                    });

                }

            });

        });
    }
</script>

<!--删除/修改  按钮-->
<script type="text/html" id="operation">
    <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</button>
    <button class='layui-btn layui-btn-primary layui-btn-xs' lay-event="edit">更新</button>
</script>

<!--修改页面-->
<script id="updateForm" type="text/html">
    <form class="layui-form" action="" lay-filter="updateForm" style="z-index: 100">
        <div class="layui-form-item">
            <label class="layui-form-label">品牌名称</label>
            <div class="layui-input-block" style="width:200px">
                <input type="hidden" name="brandId">
                <input type="text" name="name" required  lay-verify="required" placeholder="品牌名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">品牌排名</label>
            <div class="layui-input-block" style="width:200px">
                <input type="text" name="sort" required  lay-verify="required" placeholder="请输入名次" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">品牌logo</label>
            <input type="hidden" name="logo" id="cover2" lay-verify="file">
            <div class="layui-input-block" style="width:200px">
                <button type="button" class="layui-btn uploadfile">
                    <i class="layui-icon layui-icon-upload"></i>上传图片
                </button>
            </div>
        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block" style="width:200px">
                <textarea name="brandStory" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="left: 130px;width: 200px">
                <button type="submit" class="layui-btn" lay-submit lay-filter="update-go">添加</button>
                <button type="button" class="layui-btn" id="update-form-cancel">取消</button>
            </div>
        </div>
        <div id="coverimgdiv2">
            <img src="" id="coverimg3">
        </div>
    </form>
</script>

<!--修改/删除实现代码-->
<script type="text/javascript">
    layui.use(["table","layer","form","laydate","upload"],function(){

        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var laydate = layui.laydate;
        var upload = layui.upload;
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
                    area:"600px",
                    success:function(layerObj,index){

                        // 上传图片的展示位置样式设置
                        $("#coverimgdiv2").css({"width":"250px","height":"250px","position":"absolute","z-index":"103","right":"20px","top":"10px","border":"solid 1px red"});

                        //手动渲染更新表单中单选按钮和日期组件
                        // form.render("radio","updateForm");
                       /* laydate.render({
                            elem:"#publishedDate2",
                            type:"date",//控件选择类型 year month date time datetime
                            format:"yyyy-MM-dd",//日期格式
                            value:new Date(),//初始值
                            trigger: 'click'//触发方式
                        })*/

                        //回显表单数据
                        console.log(obj.data);//要更新的那行的原始数据
                        form.val("updateForm",obj.data);

                        // 图片回显设置
                        $("#coverimg3").prop("src","${pageContext.request.contextPath}/img/"+$("#cover2").val());
                        $("#coverimg3").css({"width":"250px","height":"250px","position":"absolute","z-index":"102"})


                        // 文件手动校验
                        form.verify({
                            file:function(value){
                                if(value.trim().length <= 0){
                                    return "未选择文件";
                                }
                            }
                        });

                        // 用于判断是否上传文件
                        var file;
                        // 上传文件
                        upload.render({
                            elem: '.uploadfile', //绑定元素
                            url: '/file/upload', //上传接口
                            field: "pic",//设置上传的参数名
                            done: function(res){
                                //上传完毕回调
                                $("#cover2").val(res.path);
                                file = $("#cover2").val();
                                $("#coverimg3").prop("src","${pageContext.request.contextPath}/img/"+res.path);
                            },
                            // 上传文件异常
                            error: function(){
                                //请求异常回调
                                alert("上传文件类型不支持")
                            }
                        });

                        //监听更新表单的提交事件
                        form.on("submit(update-go)",function(data){
                            //当更新表单,提交数据的时候,执行当前行数 data.field获取到更新表单最新的数据
                            console.log(data.field);
                            $.ajax({
                                url:"/brand/brands",
                                type:"put",
                                data:JSON.stringify(data.field),
                                contentType:"application/json",
                                dataType:"json",
                                success:function(result){
                                    //如果更新成功,关闭弹出层,刷新表格
                                    if (result.status == "success") {
                                        layer.close(index);
                                        table.reload("brand");
                                    }else{
                                        alert("服务器繁忙：更新失败");
                                    }
                                }
                            });
                            // 同时从磁盘中删除照片
                            console.log(obj.data.cover);
                            $.ajax({
                                url:"/file/remove",
                                data:"file="+obj.data.cover,
                                dataType:"json",
                                type:"post"
                            });

                            return false;//禁用表单的默认提交
                        });

                        // 取消添加按钮
                        $("#update-form-cancel").click(function () {
                            // 如果选择过图片后取消则删除选择的图片
                            if (file!=null){
                                $.ajax({
                                    url:"/file/remove",
                                    data:"file="+file,
                                    type:"post",
                                    dataType:"json"
                                });
                            }
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
                            url:"/brand/brands/"+obj.data.brandId,
                            dataType:"json",
                            type:"DELETE",
                            success:function (result) {
                                // console.log(result);
                                layer.close(index);
                                table.reload("brand");
                            }
                        });
                        // 同时从磁盘中删除照片
                        $.ajax({
                            url:"/file/remove",
                            data:"file="+obj.data.cover,
                            dataType:"json",
                            type:"post"
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
            //2 根据条件重新加载表格
            table.reload("brand",{
                url:"/brand/brands",
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