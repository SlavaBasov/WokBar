<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Test Page!!!!</h1>
    Username: ${user.username}
    Password: ${user.password}
    Phone: ${user.phoneNumber}

    Roles:<br>
    <c:forEach var="role" items="${user.roles}">
        <p>${role.name}</p>
    </c:forEach>

    Внести в базу пользователя:
    <form:form action="${pageContext.request.contextPath}/test" method="post" modelAttribute="user">
        Username:<form:input path="username"/><br>
        <form:errors path="username"/>
        <br>

        Password:<form:input path="password"/><br>
        <form:errors path="password"/>
        <br>
        Phone number:<form:input path="phoneNumber"/><br>
        <form:errors path="phoneNumber"/>
        <br>
        <input type="submit" value="ОК">
    </form:form>
</body>
</html>
