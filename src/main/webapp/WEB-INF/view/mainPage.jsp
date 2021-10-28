<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wok Bar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <h2>WOK-BAR Main page</h2>
    <hr />
    <h4>${message}</h4>
    <a href='<spring:url value="/signout"/>'>Logout</a>

    <div>
        <ul>
            <li><a href="${request.contextPath}?lang=en"><spring:message code="language.en"/> </a> </li>
            <li><a href="${request.contextPath}?lang=ru"><spring:message code="language.ru"/></a> </li>
        </ul>
    </div>


    <a href="/registration"><spring:message code="main.registration"/></a><br>
    <a href="/login">Log in</a><br>
    <a href="/admin">Admin</a><br>
    <a href="/user">User</a><br>
    <a href="/test">Test</a><br>
</div>
</body>
</html>
