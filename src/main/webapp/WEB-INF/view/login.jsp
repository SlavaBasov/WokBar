<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <h2>Login Form</h2>

    <form:form action="${pageContext.request.contextPath}/login" method="post" modelAttribute="user">
        Username: <form:input path="username"/>
        <br><br>
        Password: <form:input path="password"/>
        <br><br>
        <input type="submit" value="Log in">
    </form:form>
    <br/>
</div>

</body>
</html>