<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-03
  Time: 오후 5:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <%
    String name = request.getParameter("name");
    int age = Integer.parseInt(request.getParameter("age"));

  %>  <!-- 스크립틀릿이라하고 안에 자바 사용가능,  -->
  <h1>Hello Servlet</h1>
  <h2>이름 : <%=name %>%></h2><!-- <>안에 자바 변수사용가능  -->
  <h2>나이 : <%=age %>%></h2>
  </body>
</html>
