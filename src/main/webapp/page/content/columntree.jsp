
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        function selectNode(node){
            $('#tt').tree('select',node.target);
        };

        function expandParent(node){
            var parent = node;
            var t = true;
            do {
                parent = $('#tt').tree('getParent',parent.target); //获取此节点父节点
                if(parent){ //如果存在
                    t=true;
                    $('#tt').tree('expand',parent.target);
                }else{
                    t=false;
                }
            }while (t);
        };

        $(function () {

            $("[name=columnname]").keyup(function (event) {
               if(event.keyCode==13){ //用户按的是Enter键
                   //获取树状结构的9个顶级分类
                   var rootNodes = $("#tt").tree('getRoots');
                   //$(this)：搜索框jquery对象   获取到搜索的关键词
                   var searchCon = $(this).val();
                   var data;
                   for ( var i = 0; i < rootNodes.length; i++) {
                         children = $('#tt').tree('getChildren',rootNodes[i].target);//获取顶级node下所有子节点

                       if ("首页" == $(this).val()) {
                           $('#tt').tree("collapseAll");//折叠所有节点
                           var node = $('#tt').tree('find', rootNodes[i].id);
                           $('#tt').tree('select', node.target);
                           //获取选中的值
                           data=$("#tt").tree('getSelected').text;
                           //给父窗体的控件赋值
                           parent.$("#column").val(data);
                           //先得到当前iframe层的索引
                           var index = parent.layer.getFrameIndex(window.name);
                           parent.layer.close(index); //再执行关闭
                           //  expandParent(rootNodes[i]);
                           return;
                       }

                       if(children){ //如果有子节点
                           for(var j=0;j<children.length;j++){ //循环所有子节点
                               if(children[j].text==$(this).val()){ //判断节点text是否包含搜索文本
                                   var node = $('#tt').tree('find', children[j].id);
                                   $('#tt').tree('select', node.target);
                                   expandParent(children[j]); //设置此节点所有父级展开
                                   data=$("#tt").tree('getSelected').text;
                                   parent.$("#column").val(data);
                                   var index = parent.layer.getFrameIndex(window.name);
                                   parent.layer.close(index); //再执行关闭
                                   return;
                               }
                           }
                       }


                   }
                   //所有内容取消选择
                   $('#tt').find('.tree-node-selected').removeClass('tree-node-selected');
                  // var checkedNode = $('#tt').tree('find', $(this).val());

                  // $('#tt').tree('check', checkedNode.target);
               }
            });

            $("#tt").tree({
                url: '${pageContext.request.contextPath}/con/getAllContent',
                animate: true,
                checkbox:true,
                loadFilter: function (data) {
                    change(data);
                    //图标的设定
                    $.each(data, function (i, v) {
                        v.iconCls = "icon-folder";
                    });

                    return data;
                },
                onLoadSuccess : function(node, data) {
                    $('#tt').tree("collapseAll");
                },
                onClick:function (checkNode) {
                    showcontent(checkNode);
                }
            });
        });
        function change(data) {
            if (!data.length) {
                data.id=data.syscode;
                data.text = data.columnname;
                data.children = data.children;
                if (data.children) {
                    change(data.children);
                }
            } else {
                $.each(data, function (i, v) {
                    change(v);
                });
            }
        }
    </script>
</head>
<body>
关键字:<input class="tree-textbox" name="columnname"/> <a href="javascript:;" class="easyui-linkbutton">搜索</a>
<ul id="tt"></ul>
</body>
</html>
