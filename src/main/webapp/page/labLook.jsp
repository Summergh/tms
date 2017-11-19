<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<html id="myhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript">
     window.onload=function () {
         //加载机房的信息
         $.ajax({
             url:"${pageContext.request.contextPath}/labReport/getReport",
             type:"POST",
             success:function (data) {
                   $("body").html(data.content);
                   $("title").html(data.title);
             }
         });
     }
  </script>
<title>

</title>
<body>

</body>
</html>