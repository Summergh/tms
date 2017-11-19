<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@taglib uri="http://www.springsecurity.org/jsp" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新增文章</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css">
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css">
    <link href="${pageContext.request.contextPath}/shuanchuan/css/common.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/shuanchuan/css/index.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
    <script type="text/javascript">

        function resetValue() {
            var ss = $(".file").attr("id");
            var file = document.getElementById(ss);
            var fileList = file.files; //获取的图片文件*/
            for (var  i=0;i<fileList.length;i++){
                $("[name='imageurl']").val(fileList[i].name);
            }
             $.ajax({
             url:"${pageContext.request.contextPath}/con/addNews",
             type:"post",
             data:$("#fm").serialize(),
             success:function (data) {
             if (data>0){
             alert("ok");
             }else{
             alert("no");
             }
             }
             });

        }
        UE.getEditor("contents");

        //选择




    </script>

    <style type="text/css">
        div {
            width: 100%;
        }
    </style>

</head>
<body style="margin: 1px">

    <form id="fm" method="post">
        <table cellpadding="6px" align="center">
            <tr>
                基本信息
                <hr>
            </tr>
            <tr>
                <td>所属栏目：</td>
                <td>
                    <input class="easyui-textbox"  id="column" name="columnname" placeHolder="点我查询" readonly="readonly"/>
                    <input name="cmSyscode" id="cmSyscode" hidden/>
                    <input type="button" value="栏目选择" class="button border-blue" onclick="selects()">
                    <a class="easyui-linkbutton" plain="true" icon="icon-ok"></a>
                </td>
            </tr>
            <tr>
                <td>文章标题：</td>
                <td>
                    <input name="newtitle"  class="easyui-textbox" required="true" style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td>关键字：</td>
                <td>
                    <input name="tag" class="easyui-textbox" style="width: 130px; vertical-align: middle;"/>
                </td>
            </tr>
            <tr>
                <td> 文章图片：</td>
                <td>
                    <section class=" img-section">
                        <div class="z_photo upimg-div clear">
                            <section class="z_file fl">
                                <img  src="${pageContext.request.contextPath}/shuanchuan/img/a11.png"
                                     class="add-img">
                                <input type="file" id="file" class="file"/>
                                <input name="imageurl" type="hidden"/>
                            </section>
                        </div>
                    </section>
                    <aside class="mask works-mask">
                        <div class="mask-content">
                            <p class="del-p ">您确定要删除作品图片吗？</p>
                            <p class="check-p"><span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span>
                            </p>
                        </div>
                    </aside>
                </td>
            </tr>
            <tr>
                <td>摘要：</td>
                <td><textarea name="abstracts" style="width: 400px;height:200px"></textarea></td>
            </tr>

            <tr>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td></td>
                <td><textarea id="contents"
                              name="contents"
                              style="width: 400px;height:200px">
                 </textarea></td>

            </tr>
            <br/>

            <br/>
            <tr>
                <td></td>
                <td align="center">
                    请选择：
                    <select name="type">
                        <option value="草稿">草稿</option>
                        <option value="发布">发布</option>
                    </select>
                </td>

            </tr>
            <br/>
            <tr>
                <td></td>
                <td>

                    <a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-submit">发布</a>&nbsp;

                </td>
            </tr>

        </table>
    </form>
</div>
    <div id="dlg3" class="easyui-dialog" style="width: 350px;height: 500px;padding: 10px 20px" closed="true" >
        栏目选择：
        <input type="text" id="ss" name="columnname"/> <button onclick="selectss()">查询</button>
        <ul id="tt"></ul>
    </div>


<script src="${pageContext.request.contextPath}/shuanchuan/js/imgUp.js"></script>
<script type="text/javascript">


    $(function () {
        $("#column").click(function () {
            layer.open({
                type: 2,
                title: '栏目选择',
                shadeClose: true,
                shade: 0.6,
                area: ['380px', '90%'],
                content: '${pageContext.request.contextPath}/page/content/columntree.jsp', //iframe的url
                scrollbar: true
            });
        });
    });







    /*
        function selects() {

            $('#dlg3').dialog('open').dialog('center').dialog('setTitle', '选择栏目');
            $("#tt").tree({
                url: '${pageContext.request.contextPath}/con/getAllContent',
            animate: true,
            cascadeCheck: false,
            loadFilter: function (data) {
                change(data);
                //图标的设定
                $.each(data, function (i, v) {
                    v.iconCls = "icon-folder";
                });
                return data;
            },
            onDblClick: function (checkNode) {

                     $("#columnname").val(checkNode.text);
                     $("#cmSyscode").val(checkNode.id);
                $('#dlg3').dialog('close').dialog('center').dialog('setTitle', '选择栏目');
            }
        });
    }
    function selectss() {
        var columnnames=$("#ss").val();
       alert(columnnames);
        $("#tt").tree({
            url: '${pageContext.request.contextPath}/con/getAllContents?columnname='+columnnames,
            animate: true,
            cascadeCheck: false,
            loadFilter: function (data) {
                change(data);
                //图标的设定
                $.each(data, function (i, v) {
                    v.iconCls = "icon-folder";
                });
                return data;
            },
            onDblClick: function (checkNode) {

                $("#columnname").val(checkNode.text);
                $("#cmSyscode").val(checkNode.id);
                $('#dlg3').dialog('close').dialog('center').dialog('setTitle', '选择栏目');
            }
        });
    }
    function change(data) {
        if (!data.length) {
            data.text = data.columnname;
            data.id=data.syscode;
            if (data.children) {
                change(data.children);
            }
        } else {
            $.each(data, function (i, v) {
                change(v);
            });
        }
    }*/
</script>

</body>


</html>