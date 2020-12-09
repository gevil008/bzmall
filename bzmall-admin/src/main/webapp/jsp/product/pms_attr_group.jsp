<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>分类页面</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/metroStyle/metroStyle.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cascader/cascader.css">
    </head>

    <body class="layui-layout-login">


        <div class="layui-row" style="padding-top: 20px;padding-left: 20px">
            <div class="layui-col-md3">
                <ul id="treeDemo" class="ztree"></ul>
            </div>

            <div class="layui-col-md9">

                <h2 id="tableName">XXX</h2>

                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-sm" lay-event="addData" onclick="addLayer()">添加
                        </button>
                        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">删除选中</button>
                    </div>
                </script>

                <script type="text/html" id="barDemo">
                    <a class="layui-btn layui-btn-xs" lay-event="relation" onclick="openRelationLayer({{d.attrGroupId}})">关联</a>
                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                </script>

                <script type="text/html" id="checkboxTpl">
                    <input type="checkbox" name="showStatus" value="{{d.showStatus}}" title="显示" lay-filter="lockDemo" {{ d.showStatus ==1 ? 'checked' : '' }}>
                </script>

                <table class="layui-hide" id="test"></table>

                <!--            添加表单-->
                    <form action="hhh" lay-filter="addForm" id="addForm" class="layui-form"
                          style="display:none;padding:20px 20px 0 0" method="post">
                        <div class="layui-form-item">
                            <lable class="layui-form-label">组名</lable>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="attrGroupName">
                            </div>

                        </div>
                        <div class="layui-form-item">
                            <lable class="layui-form-label">排序</lable>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="sort">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <lable class="layui-form-label">描述</lable>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="descript">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <lable class="layui-form-label">组图标</lable>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="icon">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">所属分类</label>
                            <div class="layui-input-block">
                                <input type="text" id="a" name="categoryName" class="layui-input" readonly="readonly">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="addSubmit">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>


<%--                relation表格--%>
                <div id="relationTableDiv" style="display: none">
                    <input type="hidden" id="attrGroupId">
                    <script type="text/html" id="toolbarRelation">
                        <div class="layui-btn-container">
                            <button class="layui-btn layui-btn-sm" onclick="openNoRelationLayer()">新建关联</button>
                            <button class="layui-btn layui-btn-sm">批量删除</button>
                        </div>
                    </script>

                    <script type="text/html" id="barRelation">
                        <a class="layui-btn layui-btn-danger layui-btn-xs">移除</a>
                    </script>

                    <table class="layui-hide" id="relationTable" style="display: none"></table>
                </div>

                <%--                Norelation表格--%>
                <div id="noRelationTableDiv" style="display: none">

                    <table class="layui-hide" id="noRelationTable" style="display: none"></table>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
        <script src="${pageContext.request.contextPath}/ztree/js/jquery-1.4.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.all.js"></script>

        <script>
            // 打开没有关联数据的table
            function openNoRelationLayer() {
                var attrGroupId = $("#attrGroupId").val()
                layui.use(["layer","form",'table'],function(){
                    var form = layui.form;
                    var layer = layui.layer;
                    var table = layui.table;
                    var $ = layui.jquery;
                    layer.open({
                        type:1,
                        area: ['600px','400px'],
                        btn: ['添加关联'],
                        maxmin: true,
                        content:$("#noRelationTableDiv"),
                        success: function(layero, index){
                            table.render({
                                elem: '#noRelationTable',
                                url: '${pageContext.request.contextPath}/attr/getNoRelationAttr?attrGroupId='+attrGroupId,
                                cellMinWidth: 80,
                                toolbar: '#toolbarRelation',
                                cols: [[
                                    {type:'checkbox'},
                                    {field:'attrId', title: 'id', sort: true},
                                    {field:'attrName', title: '属性名'},
                                    {field:'valueSelect', title: '可选值', sort: true},
                                    {fixed: 'right', title:'操作', toolbar: '#barRelation', width:150}
                                ]],
                            });
                        },
                        cancel: function(index, layero){
                            layer.close(index)
                        }
                    });

                })
            }

            function openRelationLayer(attrGroupId) {
                $("#attrGroupId").val(attrGroupId)
                layui.use(["layer","form",'table'],function(){
                    var form = layui.form;
                    var layer = layui.layer;
                    var table = layui.table;
                    var $ = layui.jquery;
                    layer.open({
                        type:1,
                        area: ['600px','400px'],
                        maxmin: true,
                        content:$("#relationTableDiv"),
                        success: function(layero, index){
                            table.render({
                                elem: '#relationTable',
                                url: '${pageContext.request.contextPath}/attr/getRelationAttr?attrGroupId='+attrGroupId,
                                cellMinWidth: 80,
                                toolbar: '#toolbarRelation',
                                cols: [[
                                    {type:'checkbox'},
                                    {field:'attrId', title: 'id', sort: true},
                                    {field:'attrName', title: '属性名'},
                                    {field:'valueSelect', title: '可选值', sort: true},
                                    {fixed: 'right', title:'操作', toolbar: '#barRelation', width:150}
                                ]],
                            });
                        },
                        cancel: function(index, layero){
                            layer.close(index)
                        }
                    });

                })
            }
            
            // 打开添加对话框
            //执行弹出弹出层的代码
            function addLayer(){
                layui.use(["layer","form",'table'],function(){
                        var form = layui.form;
                        var layer = layui.layer;
                        var table = layui.table;
                        var $ = layui.jquery;
                        layer.open({
                            type:1,
                            area: ['600px','700px'],
                            maxmin: true,
                            content:$("#addForm")
                        });
                        //------------ 添加功能 start--------------------------
                        form.on('submit(addSubmit)',function () {
                            $.ajax({
                                url:'${pageContext.request.contextPath}/attr-group/addAttrGroup',
                                //通过jquery直接获取表单数据
                                data:$("#addForm").serialize(),
                                dataType:'json',
                                type: 'POST',
                                success:function (result) {
                                    if(result.code == 0){
                                        layer.closeAll();//添加成功关闭弹框
                                        table.reload('test');
                                        $("#addForm")[0].reset();  //清空输入框
                                    }
                                }
                            })
                            return false;//阻止表单提交
                        })
                        //------------ 添加功能 end  --------------------------

                })
            };
            //table
            layui.use('table', function(){
                var table = layui.table;

                table.render({
                    elem: '#test',
                    url: '${pageContext.request.contextPath}/attr-group/selectAttrGroupBySizeAndCateId',
                    cellMinWidth: 80,
                    toolbar: '#toolbarDemo',
                    cols: [[
                        {type:'checkbox'},
                        {field:'attrGroupId', title: '分组id', sort: true},
                        {field:'attrGroupName', title: '组名'},
                        {field:'sort', title: '排序', sort: true},
                        {field:'categoryId', title: '分类id'},
                        {field:'descript', title: '描述'},
                        {fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
                    ]],
                    page: true
                });

            });
            // ztree
            $(function () {
                var settings = {
                    data: {},
                    callback: {
                        beforeClick: beforeClick,
                        onClick: onClick
                    }
                };
                $.ajax({
                    url:"${pageContext.request.contextPath}/cate/getZTreeNodes",
                    success: function (data) {
                        zTreeObj = $.fn.zTree.init($("#treeDemo"), settings, data); //初始化树
                    }
                });
                // 点击树的节点会触发的方法 beforeClick执行时间早于 onClick
                function beforeClick(treeId, treeNode, clickFlag) {
                    // 判断点击的是不是三级分类 如果是 才能够触发表格刷新
                    if (treeNode.children.length == 0){
                        // 返回true会触发树的点击事件 onClick
                        return true;
                    }
                    return false;
                };
                //表格刷新
                function onClick(event, treeId, treeNode, clickFlag) {
                    $("#tableName").html(treeNode.name);
                    // 查询数据 刷新表中数据
                    layui.use('table', function() {
                        var table = layui.table;
                        table.reload('test', {
                            url: '${pageContext.request.contextPath}/attr-group/selectAttrGroupBySizeAndCateId',
                            where: {
                                categoryId: treeNode.id
                            },
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                    });
                };

                $.ajax({
                    url:"${pageContext.request.contextPath}/cate/getCascaderNodeVos",
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
            })


        </script>
    </body>
</html>
