<%--
  Created by IntelliJ IDEA.
  User: Tyler
  Date: 2021/1/18
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<h3>
  <a href="${pageContext.request.contextPath}/user/login">login</a>
</h3>
用户名:<input type="text" id="txtName" onblur="a1()"/>
  $END$

<input type="button" id="btn" value="获取数据"/>
<table width="80%" align="center">
  <tr>
    <td>姓名</td>
    <td>年龄</td>
    <td>性别</td>

  </tr><td>姓名</td>
  <td>年龄</td>
  <td>性别</td>
  <tbody id="content">
  </tbody>
</table>

<input type="button" class="get" value="get请求">
<input type="button" class="post" value="post请求">
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    document.querySelector(".get").onclick= function () {
        axios.get("${pageContext.request.contextPath}/a2")
            .then(function (response) {
                console.log(response)
            })
    }



  <%--$(function () {--%>
  <%--  $("#btn").click(function () {--%>
  <%--    $.post("${pageContext.request.contextPath}/a2",function (data) {--%>
  <%--      console.log(data)--%>
  <%--      var html="";--%>
  <%--      for (var i = 0; i <data.length ; i++) {--%>
  <%--        html+= "<tr>" +--%>
  <%--                "<td>" + data[i].user_id + "</td>" +--%>
  <%--                "<td>" + data[i].user_password + "</td>" +--%>
  <%--                "</tr>"--%>
  <%--      }--%>
  <%--      $("#content").html(html);--%>
  <%--    });--%>
  <%--  })--%>
  <%--})--%>
</script>
  </body>
  <script>
    function a1(){
      $.post({
        url:"${pageContext.request.contextPath}/a1",
        data:{'name':$("#txtName").val()},
        success:function (data,status) {
          alert(data);
          alert(status);
        }
      });
    }
  </script>
</html>
