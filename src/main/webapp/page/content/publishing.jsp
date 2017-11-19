<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springsecurity.org/jsp" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>内容发布</title>
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
    <script type="text/javascript">
        var cmsyscode;
        var titles;
        Date.prototype.Format = function (fmt) { //author: meizz
            var o = {
                "M+": this.getMonth() + 1,                 //月份
                "d+": this.getDate(),                    //日
                "h+": this.getHours(),                   //小时
                "m+": this.getMinutes(),                 //分
                "s+": this.getSeconds(),                 //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds()             //毫秒
            };
            if (/(y+)/.test(fmt))
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt))
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
        $(function () {

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
                    cmsyscode = checkNode.id;
                    titles = checkNode.text;
                    showcontent(cmsyscode, titles);
                }
            });

        });


        function change(data) {
            if (!data.length) {
                data.text = data.columnname;
                data.id = data.syscode;
                if (data.children) {
                    change(data.children);
                }
            } else {
                $.each(data, function (i, v) {
                    change(v);
                });
            }
        }
        function showcontent(cmsyscode, titles) {
            $("#tb").datagrid({
                url: '${pageContext.request.contextPath}/con/getNewsBySyscode?newtitle=' + newtitle + '&type=' + type + '&cmsyscode=' + cmsyscode + '&titles=' + titles,
                pagination: true,
                pageList: [2, 3, 5, 10],
                columns: [[
                    {field: 'id', title: '编号', width: 120},
                    {field: 'newtitle', title: '标题', width: 120},
                    {
                        field: 'content',
                        title: '栏目',
                        width: 120,
                        align: 'right',
                        formatter: function (value, row, index) {
                            return value.columnname;
                        }
                    },
                    {field: 'clickcount', title: '点击数', width: 120, align: 'right'},
                    {field: 'type', title: '状态', width: 120, align: 'right'},
                    {
                        field: 'userInfo',
                        title: '创建者',
                        width: 120,
                        align: 'right',
                        formatter: function (value, row, index) {
                            return value.username;
                        }
                    },
                    {
                        field: 'updateTime',
                        title: '更新时间',
                        width: 140,
                        align: 'right',
                        formatter: function (value, row, index) {
                            return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                        }
                    },
                    {
                        field: '1', title: '操作', width: 120, formatter: function (value, row, index) {

                        return "<a href='javascript:update()'>修改</a>";
                    }
                    }
                ]]
            });
        }


        function update() {
            alert("123213312");
            var rows = $("#tb").datagrid('getSelected');//获取当前选中行的对象
            openTab("修改新闻","/page/content/updColumnDetail.jsp?id="+rows.id);
        }
        function openTab(text,url,iconCls){
            if(parent.$("#tabs").tabs("exists",text)){
                parent.$("#parent.tabs").tabs("select",text);
            }else{
                var content="<iframe frameborder=0 scrolling='scroll' style='width:100%;height:100%' src='${pageContext.request.contextPath}"+url+"'></iframe>";
                parent.$("#tabs").tabs("add",{
                    title:text,
                    iconCls:iconCls,
                    closable:true,
                    content:content
                });
            }
        }

        //多条件查询
        function muchselect() {
            var type = $("#type").val();
            var newtitle = $("#newtitle").val();
            $("#tb").datagrid({
                url: '${pageContext.request.contextPath}/con/getMuchNews?newtitle=' + newtitle + '&type=' + type,
                pagination: true,
                pageList: [2, 3, 5, 10],
                columns: [[
                    {field: 'id', title: '编号', width: 120},
                    {field: 'newtitle', title: '标题', width: 120},
                    {
                        field: 'content',
                        title: '栏目',
                        width: 120,
                        align: 'right',
                        formatter: function (value, row, index) {
                            return value.columnname;
                        }
                    },
                    {field: 'clickcount', title: '点击数', width: 120, align: 'right'},
                    {field: 'type', title: '状态', width: 120, align: 'right'},
                    {
                        field: 'userInfo',
                        title: '创建者',
                        width: 120,
                        align: 'right',
                        formatter: function (value, row, index) {
                            return value.username;
                        }
                    },
                    {
                        field: 'updateTime',
                        title: '更新时间',
                        width: 140,
                        align: 'right',
                        formatter: function (value, row, index) {
                            return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                        }
                    },
                    {
                        field: '1', title: '操作', width: 120, formatter: function (value, row, index) {
                        return "<a href='#'>修改</a>";
                    }
                    }
                ]]
            });
        }
    </script>
    <html>
    <head>
        <title>Title</title>
    </head>
<body>
<div class="easyui-layout" style="width:1200px;height:800px;">
    <div region="west" split="true" title="默认站点" style="width:150px;">


        <ul id="tt">
        </ul>
    </div>
    <div id="content" region="center" title="${titles}" style="padding:5px;">
        <div>
            状态:
            <select id="type">
                <option value="发布">发布</option>
                <option value="审核">审核</option>
                <option value="驳回">驳回</option>
                <option value="删除">删除</option>
                <option value="草稿">草稿</option>
                <option></option>
            </select>
            标题:
            <input type="text" id="newtitle"/>
            <button id="btn" onclick="muchselect()">查询</button>

            <a href="/con/addSet">
                <button>添加内容</button>
            </a>

        </div>
        <table id="tb" align="center"></table>

    </div>
</div>
</body>
</html>
