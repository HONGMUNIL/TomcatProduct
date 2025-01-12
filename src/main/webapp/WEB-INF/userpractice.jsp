<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-12
  Time: 오후 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> 사용자 등록</h1>
<form action="http://localhost:8080/servlet_study_war/userpractice"></form>
  <table>
    <tr>
      <td>username</td>
      <td><input type="text" name="username" required></td>
      <td><input type="text" name="password" required></td>
    </tr>


    <tr>
      <td>name</td>
      <td><input type="text" name="name" required></td>
      <td>email</td>
      <td><input type="text" name="email" required></td>
    </tr>
  </table>
</body>
</html>
