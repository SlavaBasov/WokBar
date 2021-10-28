<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h2><spring:message code="registration.title"/></h2>
    <form:form action="${pageContext.request.contextPath}/registration" method="post" modelAttribute="userRegistration">
        Username:<form:input path="username"/><br>
        <form:errors path="username"/>
        ${usernameError}
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
