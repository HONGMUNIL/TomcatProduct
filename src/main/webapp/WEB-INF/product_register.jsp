<%@ page import="org.example.servlet_study.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.servlet_study.datas.DataList" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-06
  Time: 오전 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>${serverName}</title>
</head>
<body>
<h1>상품등록</h1>
<form action="http://localhost:8080/servlet_study_war/product/register" method="post">    <%--   --%>
    <table>
        <tr>
            <td>카테고리</td>
            <td>
                <select name="category" id="">
                    <%--
                            [EL] 표현식:
                            el 표현식은 라이브러리(JSTL) 필요, 저장소에 저장된 데이터를 불러올때 사용, 결국 getAttribute를 하는것
                            사용 방식: ${}
                            EL 표현식을 사용할 수 있는 경우
                            1. Attribute에 포함되어있는 값(Application, Session, Request)
                            2. JSP의 기본 설정 값

                            Servlet
                            저장소 : Application(서버실행될때 만들어짐), Session(브라우저에서 서버로 최초로 요청이 들어오면 만들어짐)
                                    Request(매번 요청시 만들어짐)
                            저장소에 데이터를 넣기위해선 SetAttribute("키값", 값(object))로 저장

                            Application:
                            전역, 헬스장 공개된 전화번호,정보들
                            프로세스가 꺼지면 종료.

                            Session:
                            전역이지만 개인저장소,헬스장라커룸같은것(시간제한이있다. 시간지나면 라커룸반납)
                            브라우저가 꺼지면 키값이 날라간다. 그러면 안에 있던 정보들은 다 날아감

                            Request:
                            매번 호출시에. 기능들 요청하고 완료했을떄 사라짐
                         --%>
                    <%-- 밑의 foreach가 for(Category category : categories) 와 같다
                    --%>
                    <c:forEach var="category" items="${categories}">
                        <option value=${category.id}>${category.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>


        <tr>
            <td>상품명</td>
            <td><input type="text" name="productName"></td>
        </tr>


        <tr>
            <td>가격</td>
            <td><input type="text" name="price"></td>
        </tr>


        <tr>
            <td>등록일자</td>
            <td><input type="date" name="registerDate"></td>
        </tr>

    </table>
    <button type="submit">저장</button>
</form>
</body>
</html>
