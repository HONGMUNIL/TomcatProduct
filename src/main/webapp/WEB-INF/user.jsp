<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-06
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/servlet_study_war/user" method="post">
    <table>
        <tr>
            <td>username</td>
            <td><input type="text" name="userName" required></td>
            <td>password</td>
            <td><input type="password" name="userPassword" required></td>
            <td>name</td>
            <td><input type="text" name="name" required></td>
            <td>email</td>
            <td><input type="email" name="email" required></td>
        </tr>
        <c:forEach varStatus="status" var="user" items="${users}">
            <tr>
                <td>${user}.username</td>
                <td>${user}.password</td>
                <td>${user}.name</td>
                <td>${user}.email</td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit">저장</button>
</form>
</body>
</html>
