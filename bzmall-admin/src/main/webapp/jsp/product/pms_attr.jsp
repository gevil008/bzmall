<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>基本属性添加页面</title>
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
                        <button class="layui-btn layui-btn-sm" lay-event="addData" onclick="addLayer()">添加</button>
                        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">删除选中</button>
                    </div>
                </script>

                <script type="text/html" id="barDemo">
                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                </script>

                <script type="text/html" id="checkboxTpl">
                    <input type="checkbox" name="showStatus" value="{{d.showStatus}}" title="显示" lay-filter="lockDemo" {{ d.showStatus ==1 ? 'checked' : '' }}>
                </script>

                <table class="layui-hide" id="test"></table>

                <!--            添加表单-->
                    <form lay-filter="addForm" id="addForm" class="layui-form"
                          style="display:none;padding:20px 20px 0 0" method="post">
                        <div class="layui-form-item">
                            <lable class="layui-form-label">属性名</lable>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="attrName">
                            </div>

                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">属性类型</label>
                            <div class="layui-input-block">
                                <select name="attrType" lay-filter="attrType">
                                    <option value="1">销售属性</option>
                                    <option value="0" selected>基本属性</option>
                                    <option value="2">既是销售属性又是基本属性</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <lable class="layui-form-label">属性图标</lable>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="icon">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <lable class="layui-form-label">可选值列表</lable>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="valueSelect" placeholder="多个属性值逗号分隔">
                            </div>
                        </div>


                        <div class="layui-form-item">
                            <label class="layui-form-label">所属分类</label>
                            <div class="layui-input-block">
                                <input type="text" id="a" name="categoryName" class="layui-input" readonly="readonly">
                            </div>
                        </div>


                        <div class="layui-form-item">
                            <label class="layui-form-label">所属分组</label>
                            <div class="layui-input-block">
                                <select name="attrGroupId" id="attrGroupName" lay-filter="attrGroupName">
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">是否要检索</label>
                            <div class="layui-input-block">
                                <input type="radio" name="searchType" value="1" title="需要" checked>
                                <input type="radio" name="searchType" value="0" title="不需要">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">启用状态</label>
                            <div class="layui-input-block">
                                <input type="radio" name="enable" value="1" title="启用" checked>
                                <input type="radio" name="enable" value="0" title="禁用">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">快速展示</label>
                            <div class="layui-input-block">
                                <input type="radio" name="showDesc" value="1" title="是">
                                <input type="radio" name="showDesc" value="0" title="否" checked>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="addSubmit">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
        <script src="${pageContext.request.contextPath}/ztree/js/jquery-1.4.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.all.js"></script>

        <script>
            // 打开添加对话框
            //执行弹出弹出层的代码
            function addLayer(){
                layui.use(["layer","form",'table'],function(){
                    layui.use(["layer", "form"], function () {
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
                                url:'${pageContext.request.contextPath}/attr/addAttr',
                                data:$("#addForm").serialize(),
                                type:'POST',
                                dataType:'json',
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

                    });
                })
            };
            //table
            layui.use('table', function(){
                var table = layui.table;

                table.render({
                    elem: '#test',
                    url: '${pageContext.request.contextPath}/attr/base/selectAttrBySize?categoryId=',
                    cellMinWidth: 80,
                    toolbar: '#toolbarDemo',
                    cols: [[
                        {type:'checkbox'},
                        {field:'attrId', title: 'id', sort: true},
                        {field:'attrName', title: '属性名'},
                        {field:'searchType', title: '是否需要检索'},
                        {field:'icon', title: '属性图标'},
                        {field:'valueSelect', title: '可选值列表'},
                        {field:'attrType', title: '属性类型'},
                        {field:'enable', title: '启用状态'},
                        {field:'categoryName', title: '所属分类'},
                        {field:'attrGroupName', title: '所属分组'},
                        {field:'showDesc', title: '快速展示'},
                        {fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
                    ]],
                    page: true
                });

            });
            // ztree
            $(function () {
                var settings = {
                    data: {
                        simpleData: {
                            enable: true,  //true , false 分别表示 使用  不使用 简单数据模式
                        }
                    },
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
                function beforeClick(treeId, treeNode, clickFlag) {
                    if (treeNode.children.length == 0){
                        return true;
                    }
                    return false;
                };
                function onClick(event, treeId, treeNode, clickFlag) {
                    $("#tableName").html(treeNode.name);
                    // 查询数据 刷新表中数据
                    layui.use('table', function() {
                        var table = layui.table;
                        table.reload('test', {
                            url: '${pageContext.request.contextPath}/attr/base/selectAttrBySize',
                            where: {
                                categoryId: treeNode.id
                            },
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                    });

                    // 更新所属分组的数据
                    $.ajax({
                        url:"${pageContext.request.contextPath}/attr/base/getAttrGroupCascaderNodeVo?categoryId="+treeNode.id,
                        success:function (data) {
                            var content = "<option value=\"\"></option>";
                            $.each(data,function (index, group) {
                                content += "<option value=\""+group.value+"\">";
                                content += group.label;
                                content += "</option>";
                            });
                            $("#attrGroupName").html(content);

                            layui.use('form', function(){
                                var form = layui.form;
                                form.render('select', 'addForm');
                            });
                        }
                    })
                };

                // 所属分类
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
