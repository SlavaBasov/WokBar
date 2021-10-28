<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>All users</h1>
<table>
    <tr>
        <th>Username</th>
        <th>Phone number</th>
    </tr>
<%--    <tr>--%>
    <c:forEach var="user" items="${allUsers}">
    <tr>
            <td>${user.username}</td>
            <td>${user.phoneNumber}</td>
    </tr>
    </c:forEach>
<%--    </tr>--%>
</table>
</body>
</html>
