<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<html id="myhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教员所在机房设定</title>

  <style type="text/css">
    .left{
      width:120px;
      float:left;
    }
    .left table{
      background:#E0ECFF;
    }
    .left td{
      background:#eee;
    }
    .right{
      float:left;
      width:70%;
    }
    .right table{
      background:#E0ECFF;
      width:90%;
    }
    .right td{
      background:#fafafa;
      text-align:center;
      padding:2px;
    }
    .right td{
      background:#E0ECFF;
    }
    .right td.drop{
      background:#fafafa;
      width:100px;
    }
    .right td.over{
      background:#FBEC88;
    }
    .item{
      text-align:center;
      border:1px solid #499B33;
      background:#fafafa;
      width: 70px;
    }
    .assigned{
      border:1px solid #BC2A4D;
    }
  </style>
</head>
<body style="margin: 11px">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
  $(function () {

      //点击保存按钮
      $("#savedata").click(function () {
          var data= $("#box").html();
          var mytt=$("head").html();
          $.ajax({
              url:"${pageContext.request.contextPath}/labReport/saveLab",
              type:"POST",
              data:{"data":data,"title":mytt},
              success:function (data) {
                  if(data==1){
                      parent.$.messager.alert('温馨提示','设置成功!','info');
                  }else{
                      parent.$.messager.alert('温馨提示','设置失败!','info');
                  }
              }
          });
      });

      //加载机房的信息
      $.ajax({
          url:"${pageContext.request.contextPath}/dic/findLab",
          type:"POST",
          success:function (data) {
              $.each(data, function (i, dom) {
                  $("#tfirst").append('<tr> <td><div><input name="lab_'+dom.dicid+'" class="item" style="width:67px;" value="'+dom.dicname+'"/></div></td> </tr>');
              });
          }
      });

      //2.加载table的数据
      $.ajax({
          url:"${pageContext.request.contextPath}/dic/findTeacher",
          type:"POST",
          success:function (data) {
              $.each(data,function (i,dom){
                  $(".right table").append(' <tr> <td style="width:100px;">'+dom.dicname+'</td> <td class="drop"></td> <td class="drop"></td> <td class="drop"></td> <td class="drop"></td> <td class="drop"></td> <td class="drop"></td> <td class="drop"></td> </tr>');
              });
              $('.left .item').draggable({
                  revert:true,
                  proxy:'clone'
              });
              $('.right td.drop').droppable({
                  onDragEnter:function(){
                      $(this).addClass('over');
                  },
                  onDragLeave:function(){
                      $(this).removeClass('over');
                  },
                  onDrop:function(e,source){
                      $(this).removeClass('over');
                      if ($(source).hasClass('assigned')){
                          $(this).append(source);
                      } else {
                          var c = $(source).clone().addClass('assigned');
                          $(this).empty().append(c);
                          c.draggable({
                              revert:true
                          });
                      }
                  }
              });
          }
      });
  });






     /*$(function () {
     //加载下拉框数据
     $("#tlist").combobox({
     url:"=/dic/findTeacher",
     valueField:'dicid',
     textField:'dicname',
     editable:false//不可编辑
     });

     });*/


</script>
<div id="content">
  <%--教师列表：<input  id="tlist"  class="easyui-combobox" />--%>
  <a class="easyui-linkbutton" iconCls="icon-reload" id="savedata">保存设定</a><br/>
  <div id="box" style="width:100%;">
    <div class="left">
      <table id="tfirst">

      </table>
    </div>
    <form id="form1">
    <div class="right">
      <div style="font-weight: bold">上午排班</div>
      <table id="lastbefore" >
        <tr>
          <td class="blank"></td>
          <td class="title">周一</td>
          <td class="title">周二</td>
          <td class="title">周三</td>
          <td class="title">周四</td>
          <td class="title">周五</td>
          <td class="title">周六</td>
          <td class="title">周日</td>
        </tr>
      </table>
      <div style="font-weight: bold">下午排班</div>
      <table id="lastafter">
        <tr>
          <td class="blank"></td>
          <td class="title">周一</td>
          <td class="title">周二</td>
          <td class="title">周三</td>
          <td class="title">周四</td>
          <td class="title">周五</td>
          <td class="title">周六</td>
          <td class="title">周日</td>
        </tr>
      </table>
    </div>
    </form>
  </div>
</div>


</body>
</html>