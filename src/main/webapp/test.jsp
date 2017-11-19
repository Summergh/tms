<%--
  Created by IntelliJ IDEA.
  User: guo
  Date: 2017/11/7
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>

</head>
<body>
<form action="/con/fileImage" method="post" id="form1">
 <%--   编码：<input name="code" id="code"/>--%>
    <input type="file" name="image"/>
    <input type="submit" value="添加" id="btn"/>
</form>
<img src="${url}"/>

<input id="co" type="text"/>
</body>
<script src="/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="/js/validate/jquery.validate.messages.cn.js" type="text/javascript"></script>
<link href="/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
    $(function () {

        $("#btn").click(function () {
            if (!$("#form1").valid()) {
                return;
            }
            $.ajax({
                url: "/con/fileImage",
                type: "post",
                dataType: "JSON",
                data: {"code":$("#code").val()},
                success: function (data) {
                   // $("#form1")[0].reset();
                    alert("Success");
                    alert(data);
                }
            })

        });


        $("#form1").validate({
            rules: {
                code: {
                    required: true,
                    rangelength: [1, 10]
                },
                superscript: {rangelength: [1, 3]},
                disapperTime: {date: true}
            }
        });
    })
</script>
</html>
